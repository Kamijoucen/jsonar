package com.kamijoucen.jsonar.analyse.parser

import com.github.javaparser.ast.body.FieldDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.visitor.GenericVisitorAdapter
import org.springframework.stereotype.Component

@Component
class NodeVisitor : GenericVisitorAdapter<Void, Void>() {

    override fun visit(n: FieldAccessExpr, arg: Void): Void {
        return super.visit(n, arg)
    }

    override fun visit(n: FieldDeclaration, arg: Void): Void {
        return super.visit(n, arg)
    }

    override fun visit(n: MethodCallExpr, arg: Void): Void {
        return super.visit(n, arg)
    }

    override fun visit(n: MethodDeclaration, arg: Void): Void {
        return super.visit(n, arg)
    }
}