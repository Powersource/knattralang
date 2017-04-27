package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Print implements Statement {
    private VariableDeclaration value;

    public Print(VariableDeclaration value) {
        this.value = value;
    }


    @Override
    public void apply(MethodVisitor mv) {
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");


        if (value.getValue() instanceof NumericValue) {
            mv.visitVarInsn(Opcodes.ILOAD, value.getIndex());
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        } else if (value.getValue() instanceof StringValue) {
            mv.visitVarInsn(Opcodes.ALOAD, value.getIndex());
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
    }


}
