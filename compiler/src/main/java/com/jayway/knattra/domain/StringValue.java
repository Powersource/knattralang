package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;

public class StringValue implements Expression {
    public final String text;

    public StringValue(String text) {
        this.text = text;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    public void apply(MethodVisitor mv) {
        mv.visitLdcInsn(text);
    }
}
