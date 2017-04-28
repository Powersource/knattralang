package com.jayway.knattra.domain;

import java.util.List;

/**
 * Created by pavelrozenblioum1 on 2017-04-28.
 */
public abstract class MathExpression implements Expression {
    protected final List<Expression> expressions;

    public MathExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Type getType() {
        return expressions.get(0).getType();
    }
}
