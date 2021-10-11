package com.kamijoucen.jsonar.analyse.node

import com.kamijoucen.jsonar.common.util.NodeIdGenerator

object NodeFactory {

    fun createClassNode(name: ClassName) =
        ClassDefinition(NodeIdGenerator.nextId(NodeType.CLASS_DEFINITION.code), name)
}