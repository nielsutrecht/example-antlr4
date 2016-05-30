package com.nibado.example.antlr4.util;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenStream;

public class AbstractVisitor<L extends Lexer, P extends Parser> {
    private L lexer;
    private P parser;

    public AbstractVisitor(Class<L> lexerClass, Class<P> parserClass, String text) {
        this(lexerClass, parserClass, new ANTLRInputStream(text));
    }

    public AbstractVisitor(Class<L> lexerClass, Class<P> parserClass, CharStream charStream) {
        try {
            lexer = lexerClass.getConstructor(CharStream.class).newInstance(charStream);
            parser = parserClass.getConstructor(TokenStream.class).newInstance(new CommonTokenStream(lexer));
            setErrorListener();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public L getLexer() {
        return lexer;
    }

    public P getParser() {
        return parser;
    }

    private void setErrorListener() {
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int pos, String msg, RecognitionException e) {
                throw new IllegalStateException("Failed to parse at " + line + "," + pos  + ":  " + msg, e);
            }
        });
    }
}
