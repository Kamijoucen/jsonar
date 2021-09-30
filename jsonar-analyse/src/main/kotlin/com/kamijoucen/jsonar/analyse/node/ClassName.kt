package com.kamijoucen.jsonar.analyse.node

import org.apache.commons.lang3.StringUtils

data class ClassName(
    val name: String,
    val packageName: String = "",
)

/**
 * 类是否有全名
 */
fun ClassName.hasFullName() = StringUtils.isNotBlank(packageName)

/**
 * 获取类的全名
 */
fun ClassName.fullName() = if (this.hasFullName()) {
    "$packageName.$name"
} else {
    name
}