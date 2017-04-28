package com.jayway.knattra.parsing.visitors;

import com.jayway.knattra.antlr.KnattraBaseVisitor;
import com.jayway.knattra.antlr.KnattraParser;
import com.jayway.knattra.domain.*;
import com.jayway.knattra.domain.scope.Scope;

import java.util.stream.Collectors;

public class ExpressionVisitor extends KnattraBaseVisitor<Expression> {
    private final Scope scope;

    public ExpressionVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Expression visitVAL(KnattraParser.VALContext ctx) {
        return ctx.value().accept(new ValueVisitor());
    }

    @Override
    public Expression visitADD(KnattraParser.ADDContext ctx) {
        return new AddExpression(ctx.expression().stream().map(a -> a.accept(this)).collect(Collectors.toList()));
    }

    @Override
    public Expression visitMINUS(KnattraParser.MINUSContext ctx) {
        return new MinusExpression(ctx.expression().stream().map(a -> a.accept(this)).collect(Collectors.toList()));
    }

    @Override
    public Expression visitDIVIDE(KnattraParser.DIVIDEContext ctx) {
        return new DivideExpression(ctx.expression().stream().map(a -> a.accept(this)).collect(Collectors.toList()));
    }

    @Override
    public Expression visitMULTIPLY(KnattraParser.MULTIPLYContext ctx) {
        return new MultiplyExpression(ctx.expression().stream().map(a -> a.accept(this)).collect(Collectors.toList()));
    }

    @Override
    public Expression visitREF(KnattraParser.REFContext ctx) {
        return scope.getLocalVariable(ctx.reference().getText());
    }
}
