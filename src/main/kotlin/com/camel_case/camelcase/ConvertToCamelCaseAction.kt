package com.camel_case.camelcase

/**
 * 转换为驼峰命名的Action
 */
class ConvertToCamelCaseAction : BaseConvertAction() {
    
    override fun getTargetNamingStyle(): NamingStyleConverter.NamingStyle {
        return NamingStyleConverter.NamingStyle.CAMEL_CASE
    }
    
    override fun getActionName(): String {
        return "转换为驼峰命名"
    }
}