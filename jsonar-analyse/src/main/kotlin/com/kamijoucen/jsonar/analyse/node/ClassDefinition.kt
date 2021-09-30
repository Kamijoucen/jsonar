package com.kamijoucen.jsonar.analyse.node


data class ClassDefinition(
    val className: ClassName,
    val fields: List<FieldDefinition>,
    val methods: List<MethodDefinition>,
    val calls: List<MethodCall>,
)
