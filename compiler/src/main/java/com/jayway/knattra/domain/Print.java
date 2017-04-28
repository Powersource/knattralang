package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Print implements Statement {
    private final Expression value;

    public Print(Expression value) {
        this.value = value;
    }


    @Override
    public void apply(MethodVisitor mv) {
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        value.apply(mv);
        switch (value.getType()) {
            case STRING:
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                break;
            case INTEGER:
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
                break;
        }
    }


}
