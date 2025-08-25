package com.camel_case.camelcase

/**
 * 转换为下划线命名的Action
 */
class ConvertToSnakeCaseAction : BaseConvertAction() {
    
    override fun getTargetNamingStyle(): NamingStyleConverter.NamingStyle {
        return NamingStyleConverter.NamingStyle.SNAKE_CASE
    }
    
    override fun getActionName(): String {
        return "转换为下划线命名"
    }
}