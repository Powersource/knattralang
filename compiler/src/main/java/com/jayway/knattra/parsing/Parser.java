package com.jayway.knattra.parsing;

import com.jayway.knattra.antlr.KnattraLexer;
import com.jayway.knattra.antlr.KnattraParser;
import com.jayway.knattra.domain.CompilationUnit;
import com.jayway.knattra.parsing.visitors.CompilationUnitVisitor;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Parser {
    public CompilationUnit getCompilationUnit(String fileAbsolutePath) throws IOException {
        CharStream charStream = new ANTLRFileStream(fileAbsolutePath);
        KnattraLexer lexer = new KnattraLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        KnattraParser parser = new KnattraParser(tokenStream);
        CompilationUnitVisitor compilationUnitVisitor = new CompilationUnitVisitor();
        return parser.compilationUnit().accept(compilationUnitVisitor);
    }

}
