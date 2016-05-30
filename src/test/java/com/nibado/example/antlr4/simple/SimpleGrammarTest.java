package com.nibado.example.antlr4.simple;

import com.nibado.example.antlr4.util.TokenUtil;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.Test;

import static com.nibado.example.antlr4.util.TokenUtil.tokenString;
import static org.assertj.core.api.Assertions.assertThat;


public class SimpleGrammarTest {
    private static SimpleParser.CalcContext parse(String calc) {
        SimpleLexer lexer = new SimpleLexer(new ANTLRInputStream(calc));
        SimpleParser parser = new SimpleParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int pos, String msg, RecognitionException e) {
                throw new IllegalStateException("Failed to parse at " + line + "," + pos  + ":  " + msg, e);
            }
        });

        return parser.calc();
    }

    @Test
    public void testAddition() throws Exception {
        SimpleParser.CalcContext ctx = parse("1 + 3");

        assertThat(ctx.expr()).isNotNull();
        assertThat(ctx.expr().expr(0).number().getText().equals("1"));
        assertThat(ctx.expr().PLUS()).isNotNull();
        assertThat(ctx.expr().expr(1).number().getText().equals("3"));

        System.out.println(tokenString(SimpleLexer.class, "1 + 3"));
    }
}
