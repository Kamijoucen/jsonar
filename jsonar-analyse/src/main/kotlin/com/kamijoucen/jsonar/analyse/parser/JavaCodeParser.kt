package com.kamijoucen.jsonar.analyse.parser

import com.github.javaparser.ParserConfiguration
import com.github.javaparser.symbolsolver.JavaSymbolSolver
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver
import com.github.javaparser.utils.SourceRoot
import com.kamijoucen.jsonar.analyse.node.ClassDefinition
import org.springframework.stereotype.Component
import java.nio.file.Paths

@Component
class JavaCodeParser : CodeParser {

    /**
     * 解析java代码
     */
    override fun parse(paths: List<String>): Set<ClassDefinition> {
        if (paths.isEmpty()) {
            return emptySet()
        }
        val typeSolver = createTypeSolver(paths)
        val symbolSolver = JavaSymbolSolver(typeSolver)
        val config = ParserConfiguration().setSymbolResolver(symbolSolver)
        // 解析上下文
        val context = ParserContext()
        for (basePath in paths) {
            val results = SourceRoot(Paths.get(basePath), config).tryToParse()
            results.forEach {
                if (it.isSuccessful && it.result.isPresent) {
                    it.result.get().accept(NodeVisitor, context)
                }
            }
        }
        return context.nodes
    }

    private fun createTypeSolver(paths: List<String>): CombinedTypeSolver {
        return CombinedTypeSolver().apply {
            paths.forEach { this.add(JavaParserTypeSolver(it)) }
            this.add(ReflectionTypeSolver())
        }
    }
}