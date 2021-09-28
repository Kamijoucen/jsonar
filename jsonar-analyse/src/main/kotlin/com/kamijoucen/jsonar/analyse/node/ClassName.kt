package com.kamijoucen.jsonar.analyse.node

import org.apache.commons.lang3.StringUtils

data class ClassName(
    val name: String,
    val packageName: String = "",
)

fun ClassName.fullName() = if (StringUtils.isBlank(packageName)) {
    name
} else {
    packageName + name
}
