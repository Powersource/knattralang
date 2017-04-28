package com.jayway.knattra.domain.scope;

import com.jayway.knattra.domain.ReferenceExpression;
import com.jayway.knattra.domain.VariableDeclaration;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private final Map<String, ReferenceExpression> scope = new HashMap<>();

    public void addLocalVariable(String id, VariableDeclaration variable) {
        variable.setIndex(scope.size());
        scope.put(id, new ReferenceExpression(variable.getValue().getType(), variable.getIndex()));
    }

    public ReferenceExpression getLocalVariable(String id) {
        return scope.get(id);
    }
}
