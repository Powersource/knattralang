package com.jayway.knattra.domain.scope;

import com.jayway.knattra.domain.VariableDeclaration;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Map<String, VariableDeclaration> scope = new HashMap<>();

    public void addLocalVariable(String id, VariableDeclaration variable) {
        variable.setIndex(scope.size());
        scope.put(id, variable);
    }

    public VariableDeclaration getLocalVariable(String id) {
        return scope.get(id);
    }
}
