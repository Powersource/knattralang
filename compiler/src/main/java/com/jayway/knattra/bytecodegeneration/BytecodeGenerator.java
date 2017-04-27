package com.jayway.knattra.bytecodegeneration;

import com.jayway.knattra.domain.CompilationUnit;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BytecodeGenerator {
    private static final int CLASS_VERSION = 52;
    private final ClassWriter classWriter;

    public BytecodeGenerator() {
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
    }


    public byte[] generate(CompilationUnit compilationUnit) {

        String name = compilationUnit.getClassName();
        classWriter.visit(CLASS_VERSION, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, name, null, "java/lang/Object", null);
        MethodVisitor main = classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        compilationUnit.getStatements().forEach(statement -> statement.apply(main));
        main.visitInsn(Opcodes.RETURN); //add return instruction
        main.visitMaxs(-1, -1);
        main.visitEnd();
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
}
