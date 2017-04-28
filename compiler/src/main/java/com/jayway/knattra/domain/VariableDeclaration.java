package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class VariableDeclaration implements Statement {

    private final Expression value;
    private int index;

    public VariableDeclaration(Expression value) {

        this.value = value;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public void apply(MethodVisitor mv) {
        value.apply(mv);
        switch (value.getType()) {
            case STRING:
                mv.visitVarInsn(Opcodes.ASTORE, index);
                break;
            case INTEGER:
                mv.visitVarInsn(Opcodes.ISTORE, index);
                break;
        }
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
