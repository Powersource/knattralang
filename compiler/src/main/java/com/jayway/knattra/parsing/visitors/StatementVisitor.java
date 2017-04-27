package com.jayway.knattra.parsing.visitors;

import com.jayway.knattra.antlr.KnattraBaseVisitor;
import com.jayway.knattra.antlr.KnattraParser;
import com.jayway.knattra.domain.Print;
import com.jayway.knattra.domain.Statement;
import com.jayway.knattra.domain.Value;
import com.jayway.knattra.domain.VariableDeclaration;
import com.jayway.knattra.domain.scope.Scope;

public class StatementVisitor extends KnattraBaseVisitor<Statement> {
    private Scope scope = new Scope();

    @Override
    public Print visitPrint(KnattraParser.PrintContext ctx) {

        return new Print(scope.getLocalVariable(ctx.ID().getText()));
    }

    @Override
    public VariableDeclaration visitVariable(KnattraParser.VariableContext ctx) {
        Value variable = ctx.value().accept(new ValueVisitor());
        VariableDeclaration variableDeclaration = new VariableDeclaration(variable);
        scope.addLocalVariable(ctx.ID().getText(), variableDeclaration);
        return variableDeclaration;
    }
}
