package com.kamijoucen.jsonar.analyse.node


data class ClassNode(
    val className: ClassName,
    val fields: List<FieldDefinitionNode>,
    val methods: List<MethodDefinitionNode>,
    val calls: List<MethodCallNode>,
)
