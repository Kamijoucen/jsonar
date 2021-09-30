package com.kamijoucen.jsonar.service.impl

import com.kamijoucen.jsonar.analyse.parser.JavaCodeParser
import com.kamijoucen.jsonar.service.IAnalyseService
import org.springframework.stereotype.Service

@Service
class AnalyseServiceImpl(
    val parser: JavaCodeParser
) : IAnalyseService {

    override fun parser() {
        val nodes = parser.parse(listOf("D:\\code\\demo-target\\src\\main\\java"))
        println(nodes)
    }
}