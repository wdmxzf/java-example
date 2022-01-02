package com.example.pattern.interpreter;

/**
 * 终结符表达式
 */
public class TerminalExpression implements AbstractExpression1 {

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    public boolean interpret(String context) {
        return context.contains(this.data);
    }
}
