package com.nibado.example.antlr4.util;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

public class TokenUtil {
    public static String tokenString(Lexer lexer) {
        StringBuilder builder = new StringBuilder();
        for(Token token = lexer.nextToken();token.getType() != Token.EOF; token = lexer.nextToken()) {
            builder.append(lexer.getVocabulary().getDisplayName(token.getType()));
            builder.append('(');
            builder.append(token.getText());
            builder.append("), ");
        }

        builder.append("EOF");

        return builder.toString();
    }

    public static String tokenString(Class<? extends Lexer> lexer, String body) {
        try {
            Lexer instance = lexer.getConstructor(CharStream.class).newInstance(new ANTLRInputStream(body));
            return tokenString(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
