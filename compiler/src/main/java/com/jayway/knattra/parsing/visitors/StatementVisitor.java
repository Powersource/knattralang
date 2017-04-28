package com.jayway.knattra.parsing.visitors;

import com.jayway.knattra.antlr.KnattraBaseVisitor;
import com.jayway.knattra.antlr.KnattraParser;
import com.jayway.knattra.domain.*;
import com.jayway.knattra.domain.scope.Scope;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class StatementVisitor extends KnattraBaseVisitor<Statement> {
    private final Scope scope;
    private final ExpressionVisitor expressionVisitor;

    public StatementVisitor(Scope scope) {
        this.scope = scope;
        expressionVisitor = new ExpressionVisitor(scope);
    }

    @Override
    public Print visitPrint(KnattraParser.PrintContext ctx) {
        return new Print(ctx.expression().accept(expressionVisitor));
    }

    @Override
    public Statement visitLoop(KnattraParser.LoopContext ctx) {
        Expression condition = ctx.expression().accept(expressionVisitor);
        StatementVisitor statementVisitor = new StatementVisitor(new Scope(scope));
        List<Statement> statements = ctx.statement().stream().map(s -> s.accept(statementVisitor)).collect(toList());
        return new WhileStatement(condition, statements);
    }

    @Override
    public VariableDeclaration visitVariableDeclaration(KnattraParser.VariableDeclarationContext ctx) {
        Expression expression = ctx.expression().accept(expressionVisitor);
        VariableDeclaration variableDeclaration = new VariableDeclaration(expression);
        scope.addLocalVariable(ctx.name().getText(), variableDeclaration);
        return variableDeclaration;
    }

    @Override
    public IfStatement visitIff(KnattraParser.IffContext ctx) {
        StatementVisitor trueVisitor = new StatementVisitor(new Scope(scope));
        StatementVisitor falseVisitor = new StatementVisitor(new Scope(scope));
        return new IfStatement(ctx.expression().accept(expressionVisitor),
                ctx.pos().statement().stream().map(s -> s.accept(trueVisitor)).collect(toList()),
                ctx.neg().statement().stream().map(s -> s.accept(falseVisitor)).collect(toList()));
    }

    @Override
    public VariableAssignment visitVariableAssignment(KnattraParser.VariableAssignmentContext ctx) {
        Expression expression = ctx.expression().accept(expressionVisitor);
        VariableAssignment variableAssignment = new VariableAssignment(expression);
        scope.updateLocalVariable(ctx.name().getText(), variableAssignment);
        return variableAssignment;

    }
}
