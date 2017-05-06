package com.jayway.knattra.parsing.visitors;

import com.jayway.knattra.antlr.KnattraBaseVisitor;
import com.jayway.knattra.antlr.KnattraParser;
import com.jayway.knattra.domain.AddExpression;
import com.jayway.knattra.domain.SubExpression;
import com.jayway.knattra.domain.Expression;
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
    public Expression visitSUB(KnattraParser.SUBContext ctx) {
        return new SubExpression(ctx.expression().stream().map(a -> a.accept(this)).collect(Collectors.toList()));
    }

    @Override
    public Expression visitREF(KnattraParser.REFContext ctx) {
        return scope.getLocalVariable(ctx.reference().getText());
    }
}
