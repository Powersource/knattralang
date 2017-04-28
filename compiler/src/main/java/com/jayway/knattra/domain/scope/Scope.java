package com.jayway.knattra.domain.scope;

import com.jayway.knattra.domain.ReferenceExpression;
import com.jayway.knattra.domain.VariableAssignment;
import com.jayway.knattra.domain.VariableDeclaration;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private final Map<String, ReferenceExpression> scope = new HashMap<>();

    public void addLocalVariable(String id, VariableDeclaration variable) {
        variable.setIndex(scope.size());
        if (scope.put(id, new ReferenceExpression(variable.getValue().getType(), variable.getIndex())) != null)
            throw new RuntimeException("double declaration");
    }

    public ReferenceExpression getLocalVariable(String id) {
        return scope.get(id);
    }

    public void updateLocalVariable(String id, VariableAssignment variableAssignment) {

        if (scope.get(id).getType().equals(variableAssignment.getValue().getType()))
            variableAssignment.setIndex(scope.get(id).index);
        else throw new RuntimeException("bad assignment");
    }
}
