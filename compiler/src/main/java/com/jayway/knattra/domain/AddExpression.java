package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

public class AddExpression implements Expression {
    private final List<Expression> expressions;

    public AddExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Type getType() {
        return expressions.get(0).getType();
    }

    @Override
    public void apply(MethodVisitor mv) {
        switch (getType()) {
            case STRING:
                // create stringBuilder
                mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
                mv.visitInsn(Opcodes.DUP);
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
                expressions.forEach(e -> {
                    // for every expression walk through, evaluate, call StringBuilder append
                    e.apply(mv);
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                });
                // call stringBuilder to String
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                break;
            case INTEGER:
                boolean first = true;
                for (Expression expression : expressions) {
                    expression.apply(mv);
                    if (!first)
                        mv.visitInsn(Opcodes.IADD);
                    first = false;
                }
                break;
        }
    }
}
