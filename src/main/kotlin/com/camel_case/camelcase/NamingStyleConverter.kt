package com.camel_case.camelcase

/**
 * 命名风格转换工具类
 * 支持多种命名风格之间的相互转换
 */
object NamingStyleConverter {
    
    /**
     * 命名风格枚举
     */
    enum class NamingStyle {
        CAMEL_CASE,     // camelCase
        PASCAL_CASE,    // PascalCase
        SNAKE_CASE,     // snake_case
        KEBAB_CASE      // kebab-case
    }
    
    /**
     * 将字符串转换为指定的命名风格
     */
    fun convert(input: String, targetStyle: NamingStyle): String {
        if (input.isBlank()) return input
        
        // 首先将输入字符串分解为单词列表
        val words = splitIntoWords(input)
        if (words.isEmpty()) return input
        
        return when (targetStyle) {
            NamingStyle.CAMEL_CASE -> toCamelCase(words)
            NamingStyle.PASCAL_CASE -> toPascalCase(words)
            NamingStyle.SNAKE_CASE -> toSnakeCase(words)
            NamingStyle.KEBAB_CASE -> toKebabCase(words)
        }
    }
    
    /**
     * 将输入字符串分解为单词列表
     * 支持识别各种命名风格的分隔符
     */
    private fun splitIntoWords(input: String): List<String> {
        val words = mutableListOf<String>()
        val currentWord = StringBuilder()
        
        for (i in input.indices) {
            val char = input[i]
            
            when {
                // 遇到下划线或短横线，作为分隔符
                char == '_' || char == '-' -> {
                    if (currentWord.isNotEmpty()) {
                        words.add(currentWord.toString())
                        currentWord.clear()
                    }
                }
                // 遇到大写字母，可能是新单词的开始（驼峰命名）
                char.isUpperCase() -> {
                    if (currentWord.isNotEmpty() && i > 0 && input[i-1].isLowerCase()) {
                        words.add(currentWord.toString())
                        currentWord.clear()
                    }
                    currentWord.append(char)
                }
                // 普通字符
                char.isLetterOrDigit() -> {
                    currentWord.append(char)
                }
                // 其他字符（空格等）作为分隔符
                else -> {
                    if (currentWord.isNotEmpty()) {
                        words.add(currentWord.toString())
                        currentWord.clear()
                    }
                }
            }
        }
        
        // 添加最后一个单词
        if (currentWord.isNotEmpty()) {
            words.add(currentWord.toString())
        }
        
        return words.filter { it.isNotBlank() }
    }
    
    /**
     * 转换为驼峰命名 (camelCase)
     */
    private fun toCamelCase(words: List<String>): String {
        if (words.isEmpty()) return ""
        
        val result = StringBuilder()
        words.forEachIndexed { index, word ->
            if (index == 0) {
                result.append(word.lowercase())
            } else {
                result.append(word.lowercase().replaceFirstChar { it.uppercase() })
            }
        }
        return result.toString()
    }
    
    /**
     * 转换为帕斯卡命名 (PascalCase)
     */
    private fun toPascalCase(words: List<String>): String {
        if (words.isEmpty()) return ""
        
        return words.joinToString("") { word ->
            word.lowercase().replaceFirstChar { it.uppercase() }
        }
    }
    
    /**
     * 转换为下划线命名 (snake_case)
     */
    private fun toSnakeCase(words: List<String>): String {
        return words.joinToString("_") { it.lowercase() }
    }
    
    /**
     * 转换为短横线命名 (kebab-case)
     */
    private fun toKebabCase(words: List<String>): String {
        return words.joinToString("-") { it.lowercase() }
    }
    
    /**
     * 自动检测输入字符串的命名风格
     */
    fun detectNamingStyle(input: String): NamingStyle? {
        if (input.isBlank()) return null
        
        return when {
            input.contains('_') -> NamingStyle.SNAKE_CASE
            input.contains('-') -> NamingStyle.KEBAB_CASE
            input.first().isUpperCase() -> NamingStyle.PASCAL_CASE
            input.any { it.isUpperCase() } -> NamingStyle.CAMEL_CASE
            else -> null
        }
    }
}