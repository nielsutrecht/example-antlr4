package com.nibado.example.antlr4.simple;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.nibado.example.antlr4.simple.SimpleVisitor.parse;

public class SimpleVisitorTest {

    @Test
    public void testResult() throws Exception {
        assertThat(parse("3")).isEqualTo(3.0);
    }


}