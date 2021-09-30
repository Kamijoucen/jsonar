package com.kamijoucen.jsonar.analyse.parser

import com.google.common.collect.Sets
import com.kamijoucen.jsonar.analyse.node.ClassDefinition

data class ParserContext(
    val nodes: Set<ClassDefinition> = Sets.newHashSet()
)