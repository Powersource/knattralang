package com.jayway.knattra;

import com.jayway.knattra.bytecodegeneration.BytecodeGenerator;
import com.jayway.knattra.domain.CompilationUnit;
import com.jayway.knattra.parsing.Parser;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Compiler {

    public static final String SIMPLE_EXAMPLE = "KnattraCode/first.knt";

    public static void main(String[] args) throws Exception {
        new Compiler().compile(args);
    }

    public void compile(String[] args) throws Exception {
        File knattraFile = new File(args.length == 0 ? SIMPLE_EXAMPLE : args[0]);
        String fileAbsolutePath = knattraFile.getAbsolutePath();
        CompilationUnit compilationUnit = new Parser().getCompilationUnit(fileAbsolutePath);
        compilationUnit.setClassName(knattraFile.getName().split("\\.")[0]);
        saveBytecodeToClassFile(compilationUnit);
    }


    private void saveBytecodeToClassFile(CompilationUnit compilationUnit) throws IOException {
        BytecodeGenerator bytecodeGenerator = new BytecodeGenerator();
        byte[] bytecode = bytecodeGenerator.generate(compilationUnit);
        String fileName = compilationUnit.getClassName() + ".class";
        IOUtils.write(bytecode, new FileOutputStream(fileName));
    }
}
