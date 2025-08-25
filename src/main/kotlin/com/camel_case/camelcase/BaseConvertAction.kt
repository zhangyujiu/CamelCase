package com.camel_case.camelcase

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

/**
 * 命名风格转换Action的基类
 * 提供通用的文本选择和替换逻辑
 */
abstract class BaseConvertAction : AnAction() {
    
    /**
     * 获取目标命名风格
     */
    abstract fun getTargetNamingStyle(): NamingStyleConverter.NamingStyle
    
    /**
     * 获取Action的显示名称
     */
    abstract fun getActionName(): String
    
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        
        val selectedText = getSelectedText(editor)
        if (selectedText.isNullOrBlank()) {
            Messages.showWarningDialog(
                project,
                "请先选择要转换的文本",
                "${getActionName()} - 警告"
            )
            return
        }
        
        try {
            val convertedText = NamingStyleConverter.convert(selectedText, getTargetNamingStyle())
            if (convertedText != selectedText) {
                replaceSelectedText(project, editor, convertedText)
            }
        } catch (e: Exception) {
            Messages.showErrorDialog(
                project,
                "转换过程中发生错误: ${e.message}",
                "${getActionName()} - 错误"
            )
        }
    }
    
    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        
        // 只有在有项目和编辑器的情况下才启用Action
        e.presentation.isEnabledAndVisible = project != null && editor != null
    }
    
    /**
     * 获取编辑器中选中的文本
     */
    private fun getSelectedText(editor: Editor): String? {
        val selectionModel = editor.selectionModel
        return if (selectionModel.hasSelection()) {
            selectionModel.selectedText
        } else {
            // 如果没有选中文本，尝试选择当前光标位置的单词
            selectWordAtCaret(editor)
        }
    }
    
    /**
     * 选择光标位置的单词
     */
    private fun selectWordAtCaret(editor: Editor): String? {
        val caretModel = editor.caretModel
        val document = editor.document
        val caretOffset = caretModel.offset
        
        if (caretOffset >= document.textLength) return null
        
        val text = document.text
        var start = caretOffset
        var end = caretOffset
        
        // 向前查找单词边界
        while (start > 0 && isWordChar(text[start - 1])) {
            start--
        }
        
        // 向后查找单词边界
        while (end < text.length && isWordChar(text[end])) {
            end++
        }
        
        if (start < end) {
            val selectionModel = editor.selectionModel
            selectionModel.setSelection(start, end)
            return text.substring(start, end)
        }
        
        return null
    }
    
    /**
     * 判断字符是否为单词字符
     */
    private fun isWordChar(char: Char): Boolean {
        return char.isLetterOrDigit() || char == '_' || char == '-'
    }
    
    /**
     * 替换选中的文本
     */
    private fun replaceSelectedText(project: Project, editor: Editor, newText: String) {
        WriteCommandAction.runWriteCommandAction(project) {
            val selectionModel = editor.selectionModel
            val document = editor.document
            
            if (selectionModel.hasSelection()) {
                document.replaceString(
                    selectionModel.selectionStart,
                    selectionModel.selectionEnd,
                    newText
                )
                
                // 选中替换后的文本
                selectionModel.setSelection(
                    selectionModel.selectionStart,
                    selectionModel.selectionStart + newText.length
                )
            }
        }
    }
}