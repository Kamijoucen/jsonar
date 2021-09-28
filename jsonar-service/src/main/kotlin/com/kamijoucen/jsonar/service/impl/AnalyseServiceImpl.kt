package com.kamijoucen.jsonar.service.impl

import com.kamijoucen.jsonar.analyse.parser.CodeParser
import com.kamijoucen.jsonar.service.IAnalyseService
import org.springframework.stereotype.Service

@Service
class AnalyseServiceImpl(
    val parser: CodeParser
) : IAnalyseService {

    override fun parser() {
        val nodes = parser.parse(listOf("parser"))
        println(nodes)
    }

}