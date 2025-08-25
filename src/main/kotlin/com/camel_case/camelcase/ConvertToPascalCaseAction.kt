package com.camel_case.camelcase

/**
 * 转换为帕斯卡命名的Action
 */
class ConvertToPascalCaseAction : BaseConvertAction() {
    
    override fun getTargetNamingStyle(): NamingStyleConverter.NamingStyle {
        return NamingStyleConverter.NamingStyle.PASCAL_CASE
    }
    
    override fun getActionName(): String {
        return "转换为帕斯卡命名"
    }
}