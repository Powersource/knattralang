package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ReferenceExpression implements Expression {
    private final Type type;
    private final int index;

    public ReferenceExpression(Type type, int index) {
        this.type = type;
        this.index = index;
    }

    @Override
    public void apply(MethodVisitor mv) {
        switch (type) {
            case STRING:
                mv.visitVarInsn(Opcodes.ALOAD, index);
                break;
            case INTEGER:
                mv.visitVarInsn(Opcodes.ILOAD, index);
                break;
        }

    }

    @Override
    public Type getType() {
        return type;
    }
}
