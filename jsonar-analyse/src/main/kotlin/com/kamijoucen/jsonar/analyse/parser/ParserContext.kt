package com.kamijoucen.jsonar.analyse.parser

import com.kamijoucen.jsonar.analyse.node.ClassDefinition
import com.kamijoucen.jsonar.analyse.node.FieldDefinition
import com.kamijoucen.jsonar.analyse.node.MethodCall
import com.kamijoucen.jsonar.analyse.node.MethodDefinition

class ParserContext {

    /**
     * 当前处理中的节点
     */
    lateinit var currentClassNode: ClassDefinition

    /**
     * 所有的节点
     */
    val classDefinitions = mutableSetOf<ClassDefinition>()

    /**
     * 所有成员节点
     */
    val fieldDefinitions = mutableSetOf<FieldDefinition>()

    /**
     * 所有函数定义
     */
    val methodDefinitions = mutableSetOf<MethodDefinition>()

    /**
     * 所有函数调用
     */
    val methodCalls = mutableSetOf<MethodCall>()
}