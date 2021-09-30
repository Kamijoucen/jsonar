package com.kamijoucen.jsonar.analyse.parser

import com.kamijoucen.jsonar.analyse.node.ClassDefinition

class ParserContext {

    /**
     * 当前处理中的节点
     */
    var currentClassNode: ClassDefinition? = null

    /**
     * 所有的节点
     */
    val nodes: Set<ClassDefinition> = setOf()

}