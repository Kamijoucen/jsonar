package com.kamijoucen.jsonar.analyse.parser

import com.github.javaparser.ParserConfiguration
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.symbolsolver.JavaSymbolSolver
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver
import com.github.javaparser.utils.SourceRoot
import com.kamijoucen.jsonar.analyse.node.ClassDefinition
import com.kamijoucen.jsonar.analyse.node.ClassName
import com.kamijoucen.jsonar.analyse.node.NodeFactory
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
            buildOne(basePath, config, context)
        }
        return context.classDefinitions
    }

    /**
     * 构建一个单元类
     */
    private fun buildOne(
        basePath: String,
        config: ParserConfiguration,
        context: ParserContext
    ) {
        val results = SourceRoot(Paths.get(basePath), config).tryToParse()
        results.forEach {
            if (it.isSuccessful && it.result.isPresent) {
                it.result.get().apply {
                    context.currentClassNode =
                        NodeFactory.createClassNode(buildClassName(this))
                    this.accept(NodeVisitor, context)
                }
            }
        }
    }

    /**
     * 构建单元类名称
     */
    private fun buildClassName(unit: CompilationUnit): ClassName {
        if (unit.primaryTypeName.isEmpty) {
            throw Exception("unit class not found!")
        }
        val name = unit.primaryTypeName.get()
        if (unit.packageDeclaration.isEmpty) {
            return ClassName(name)
        }
        val packageDeclaration = unit.packageDeclaration.get()
        return ClassName(name, packageDeclaration.nameAsString)
    }

    private fun createTypeSolver(paths: List<String>): CombinedTypeSolver {
        return CombinedTypeSolver().apply {
            paths.forEach { this.add(JavaParserTypeSolver(it)) }
            this.add(ReflectionTypeSolver())
        }
    }
}