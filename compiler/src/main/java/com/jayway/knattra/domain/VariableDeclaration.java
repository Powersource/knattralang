package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class VariableDeclaration implements Statement {

    private Value value;
    private int index;

    public VariableDeclaration(Value value) {

        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public void apply(MethodVisitor mv) {
        if (value instanceof NumericValue) {
            int val = Integer.valueOf(((NumericValue) value).text);
            mv.visitIntInsn(Opcodes.BIPUSH, val);
            mv.visitVarInsn(Opcodes.ISTORE, index);
        } else if (value instanceof StringValue) {
            mv.visitLdcInsn(((StringValue) value).text);
            mv.visitVarInsn(Opcodes.ASTORE, index);
        }
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
