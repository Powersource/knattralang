package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class NumericValue implements Expression {
    private int val;

    public NumericValue(String text) {
        val = Integer.parseInt(text);
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public void apply(MethodVisitor mv) {
        mv.visitIntInsn(Opcodes.BIPUSH, val);

    }
}
