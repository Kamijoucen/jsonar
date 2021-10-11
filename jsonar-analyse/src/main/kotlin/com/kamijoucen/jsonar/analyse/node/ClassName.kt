package com.kamijoucen.jsonar.analyse.node

import org.apache.commons.lang3.StringUtils

data class ClassName(
    val name: String,
    val packageName: String = "",
) {

    /**
     * 类是否有全名
     */
    fun hasFullName(): Boolean {
        return StringUtils.isNotBlank(packageName)
    }

    /**
     * 获取类的全名
     */
    fun fullName(): String {
        return if (hasFullName()) {
            "$packageName.$name"
        } else {
            name
        }
    }

}