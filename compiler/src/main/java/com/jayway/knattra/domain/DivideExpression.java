package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

public class DivideExpression extends MathExpression {
    public DivideExpression(List<Expression> expressions) {
        super(expressions);
    }

    @Override
    public void apply(MethodVisitor mv) {
        switch (getType()) {
            case STRING:
                break;
            case INTEGER:
                boolean first = true;
                for (Expression expression : expressions) {
                    expression.apply(mv);
                    if (!first)
                        mv.visitInsn(Opcodes.IDIV);
                    first = false;
                }
                break;
        }


    }
}
