package com.nibado.example.antlr4.simple;

import com.nibado.example.antlr4.util.AbstractVisitor;

public class SimpleVisitor extends AbstractVisitor<SimpleLexer, SimpleParser> {
    public SimpleVisitor(String text) {
        super(SimpleLexer.class, SimpleParser.class, text);
    }

    public double result() {
        return visit(getParser().calc().expr());
    }

    private double visit(SimpleParser.ExprContext context) {
        if(context.number() != null) {
            return Double.parseDouble(context.number().getText());
        }
        else if (context.BR_CLOSE() != null) { //Expression between brackets
            return visit(context.expr(0));
        }
        else if (context.TIMES() != null) { //Expression * expression
            return visit(context.expr(0)) * visit(context.expr(1));
        }
        else if (context.DIV() != null) { //Expression / expression
            return visit(context.expr(0)) / visit(context.expr(1));
        }
        else if (context.PLUS() != null) { //Expression + expression
            return visit(context.expr(0)) + visit(context.expr(1));
        }
        else if (context.MINUS() != null) { //Expression - expression
            return visit(context.expr(0)) - visit(context.expr(1));
        }
        else {
            throw new IllegalStateException();
        }
    }

    public static double parse(String text) {
        return new SimpleVisitor(text).result();
    }
}
