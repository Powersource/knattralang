package com.jayway.knattra.domain;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

public class IfStatement implements Statement {
    private final Expression condition;
    private final List<Statement> tru;
    private final List<Statement> fals;

    public IfStatement(Expression condition, List<Statement> tru, List<Statement> fals) {

        this.condition = condition;
        this.tru = tru;
        this.fals = fals;
    }

    @Override
    public void apply(MethodVisitor mv) {
        condition.apply(mv);
        Label elseLabel = new Label();
        Label endLabel = new Label();
        mv.visitJumpInsn(Opcodes.IFEQ, elseLabel);
        tru.forEach(t -> t.apply(mv));
        mv.visitJumpInsn(Opcodes.GOTO, endLabel);
        mv.visitLabel(elseLabel);
        fals.forEach(t -> t.apply(mv));
        mv.visitLabel(endLabel);

    }
}
