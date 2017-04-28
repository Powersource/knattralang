package com.jayway.knattra.parsing.visitors;

import com.jayway.knattra.antlr.KnattraBaseVisitor;
import com.jayway.knattra.antlr.KnattraParser;
import com.jayway.knattra.domain.CompilationUnit;

import java.util.stream.Collectors;

public class CompilationUnitVisitor extends KnattraBaseVisitor<CompilationUnit> {
    StatementVisitor statementVisitor = new StatementVisitor();

    @Override
    public CompilationUnit visitCompilationUnit(KnattraParser.CompilationUnitContext ctx) {
        return new CompilationUnit(ctx.statement()
                .stream()
                .map(s -> s.accept(statementVisitor))
                .collect(Collectors.toList()));
    }
}
