package com.jayway.knattra.domain;

import org.objectweb.asm.MethodVisitor;

public interface Statement {
    void apply(MethodVisitor mv);
}
