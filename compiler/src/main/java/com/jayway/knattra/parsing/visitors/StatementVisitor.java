package com.jayway.knattra.parsing.visitors;

import com.jayway.knattra.antlr.KnattraBaseVisitor;
import com.jayway.knattra.antlr.KnattraParser;
import com.jayway.knattra.domain.*;
import com.jayway.knattra.domain.scope.Scope;

public class StatementVisitor extends KnattraBaseVisitor<Statement> {
    private final Scope scope;
    private final ExpressionVisitor expressionVisitor;

    public StatementVisitor() {
        scope = new Scope();
        expressionVisitor = new ExpressionVisitor(scope);
    }

    @Override
    public Print visitPrint(KnattraParser.PrintContext ctx) {
        return new Print(ctx.expression().accept(expressionVisitor));
    }

    @Override
    public VariableDeclaration visitVariableDeclaration(KnattraParser.VariableDeclarationContext ctx) {
        Expression expression = ctx.expression().accept(expressionVisitor);
        VariableDeclaration variableDeclaration = new VariableDeclaration(expression);
        scope.addLocalVariable(ctx.name().getText(), variableDeclaration);
        return variableDeclaration;
    }

}
