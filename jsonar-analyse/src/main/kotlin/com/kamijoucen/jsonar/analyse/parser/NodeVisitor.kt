package com.kamijoucen.jsonar.analyse.parser

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.visitor.GenericVisitorAdapter

object NodeVisitor : GenericVisitorAdapter<Void?, ParserContext>() {

    override fun visit(n: ClassOrInterfaceDeclaration, arg: ParserContext): Void? {

        val curClassNode = arg.currentClassNode

        val extendedTypes = n.extendedTypes
        if (!extendedTypes.isEmpty()) {
            extendedTypes.forEach {
                it.resolve().typeDeclaration
            }
        }

        return super.visit(n, arg)
    }

    override fun visit(n: FieldAccessExpr, arg: ParserContext): Void? {

        return super.visit(n, arg)
    }

    override fun visit(n: FieldDeclaration, arg: ParserContext): Void? {
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