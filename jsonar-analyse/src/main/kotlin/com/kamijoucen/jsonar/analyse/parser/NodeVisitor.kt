package com.kamijoucen.jsonar.analyse.parser

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.visitor.GenericVisitorAdapter
import com.kamijoucen.jsonar.analyse.node.ClassName

object NodeVisitor : GenericVisitorAdapter<Void?, ParserContext>() {

    override fun visit(n: ClassOrInterfaceDeclaration, arg: ParserContext): Void? {
        val currentNode = arg.currentClassNode
        // 解析继承
        n.extendedTypes.forEach { type ->
            val typeOptional = type.resolve().typeDeclaration
            if (typeOptional.isEmpty) {
                currentNode.extendedTypes.add(ClassName(type.nameWithScope))
            } else {
                val typeDeclaration = typeOptional.get()
                currentNode.extendedTypes.add(
                    ClassName(typeDeclaration.name, typeDeclaration.packageName)
                )
            }
        }
        // 解析实现
        n.implementedTypes.forEach { type ->
            val typeOptional = type.resolve().typeDeclaration
            if (typeOptional.isEmpty) {
                currentNode.implementedTypes.add(ClassName(type.nameWithScope))
            } else {
                val typeDeclaration = typeOptional.get()
                currentNode.implementedTypes.add(
                    ClassName(typeDeclaration.name, typeDeclaration.packageName)
                )
            }
        }
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