package com.jayway.knattra.domain;

import java.util.List;
import java.util.stream.Stream;

public class CompilationUnit {
    private String className;
    private List<Statement> statements;

    public CompilationUnit(List<Statement> statements) {

        this.statements = statements;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Stream<Statement> getStatements(){
        return statements.stream();
    }
}
