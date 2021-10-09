package com.kamijoucen.jsonar.analyse.parser

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.github.javaparser.ast.visitor.GenericVisitorAdapter
import com.kamijoucen.jsonar.analyse.node.ClassName

object NodeVisitor : GenericVisitorAdapter<Void?, ParserContext>() {

    /**
     * 解析类的关联类型
     */
    private fun parserRelatedType(
        relatedTypes: Collection<ClassOrInterfaceType>,
        appendSet: MutableSet<ClassName>
    ) {
        relatedTypes.forEach { type ->
            val typeOptional = type.resolve().typeDeclaration
            if (typeOptional.isEmpty) {
                appendSet.add(ClassName(type.nameWithScope))
            } else {
                val typeDeclaration = typeOptional.get()
                appendSet.add(
                    ClassName(typeDeclaration.name, typeDeclaration.packageName)
                )
            }
        }
    }

    override fun visit(n: ClassOrInterfaceDeclaration, arg: ParserContext): Void? {
        val currentNode = arg.currentClassNode
        // 解析继承
        parserRelatedType(n.extendedTypes, currentNode.extendedTypes)
        // 解析实现
        parserRelatedType(n.implementedTypes, currentNode.implementedTypes)
        return super.visit(n, arg)
    }

    override fun visit(n: FieldAccessExpr, arg: ParserContext): Void? {

        return super.visit(n, arg)
    }

    override fun visit(n: FieldDeclaration, arg: ParserContext): Void? {
        val currentClassNode = arg.currentClassNode
        println(n)
        return super.visit(n, arg)
    }

    override fun visit(n: MethodCallExpr, arg: ParserContext): Void? {
        println(n)
        return super.visit(n, arg)
    }

    override fun visit(n: MethodDeclaration, arg: ParserContext): Void? {
        return super.visit(n, arg)
    }
}