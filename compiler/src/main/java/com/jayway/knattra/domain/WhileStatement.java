package com.jayway.knattra.domain;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

/**
 * Created by pavelrozenblioum1 on 2017-04-28.
 */
public class WhileStatement implements Statement {
    private final Expression condition;
    private final List<Statement> statements;

    public WhileStatement(Expression condition, List<Statement> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    @Override
    public void apply(MethodVisitor mv) {
        Label start = new Label();
        Label end = new Label();
        mv.visitLabel(start);
        condition.apply(mv);
        mv.visitJumpInsn(Opcodes.IFEQ, end);
        statements.forEach(s -> s.apply(mv));
        mv.visitJumpInsn(Opcodes.GOTO, start);
        mv.visitLabel(end);
    }
}
