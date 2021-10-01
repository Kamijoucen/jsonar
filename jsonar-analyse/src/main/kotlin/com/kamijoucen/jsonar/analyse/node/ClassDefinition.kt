package com.kamijoucen.jsonar.analyse.node

import com.google.common.collect.Sets

data class ClassDefinition(val className: ClassName) {

    /**
     * 所有成员定义
     */
    val fields: List<FieldDefinition> = listOf()

    /**
     * 所有方法定义
     */
    val methods: List<MethodDefinition> = listOf()

    /**
     * 所有函数调用
     */
    val calls: List<MethodCall> = listOf()

    /**
     * 类继承的类型
     */
    val extendedTypes = mutableSetOf<ClassName>()

    /**
     * 类实现的类型
     */
    val implementedTypes = mutableSetOf<ClassName>()
}

