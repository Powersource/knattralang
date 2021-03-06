package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

public class SubExpression implements Expression {
    private final List<Expression> expressions;

    public SubExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Type getType() {
        return expressions.get(0).getType();
    }

    @Override
    public void apply(MethodVisitor mv) {
        switch (getType()) {
            case STRING: //TODO String subtraction is harder
                break;
            case INTEGER:
                boolean first = true;
                for (Expression expression : expressions) {
                    expression.apply(mv);
                    if (!first)
                        mv.visitInsn(Opcodes.ISUB);
                    first = false;
                }
                break;
        }
    }
}
