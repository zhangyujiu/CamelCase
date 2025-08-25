package com.camel_case.camelcase

/**
 * 转换为短横线命名的Action
 */
class ConvertToKebabCaseAction : BaseConvertAction() {
    
    override fun getTargetNamingStyle(): NamingStyleConverter.NamingStyle {
        return NamingStyleConverter.NamingStyle.KEBAB_CASE
    }
    
    override fun getActionName(): String {
        return "转换为短横线命名"
    }
}