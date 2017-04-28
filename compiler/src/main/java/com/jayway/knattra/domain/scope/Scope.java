package com.jayway.knattra.domain.scope;

import com.jayway.knattra.domain.ReferenceExpression;
import com.jayway.knattra.domain.VariableAssignment;
import com.jayway.knattra.domain.VariableDeclaration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Scope {

    private AtomicInteger counter;
    private Scope parentScope;

    private final Map<String, ReferenceExpression> scope = new HashMap<>();

    public Scope() {
        counter = new AtomicInteger(0);
    }

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        this.counter = parentScope.counter;
    }

    public void addLocalVariable(String id, VariableDeclaration variable) {
        variable.setIndex(counter.getAndIncrement());
        if (scope.put(id, new ReferenceExpression(variable.getValue().getType(), variable.getIndex())) != null)
            throw new RuntimeException("double declaration");
    }

    public ReferenceExpression getLocalVariable(String id) {
        return scope.getOrDefault(id, parentScope != null ? parentScope.getLocalVariable(id) : null);
    }

    public void updateLocalVariable(String id, VariableAssignment variableAssignment) {
        if (scope.get(id) != null && scope.get(id).getType().equals(variableAssignment.getValue().getType()))
            variableAssignment.setIndex(scope.get(id).index);
        else if (parentScope != null && parentScope.getLocalVariable(id) != null)
            variableAssignment.setIndex(parentScope.getLocalVariable(id).index);
        else throw new RuntimeException("bad assignment");
    }
}
