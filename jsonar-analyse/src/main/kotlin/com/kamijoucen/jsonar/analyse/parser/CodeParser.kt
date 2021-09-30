package com.kamijoucen.jsonar.analyse.parser

import com.kamijoucen.jsonar.analyse.node.ClassDefinition

interface CodeParser {

    /**
     * 解析源码文件，并转换为中间对象
     * 后期会实现golang等静态类型语言的解析
     */
    fun parse(paths: List<String>): Set<ClassDefinition>

}