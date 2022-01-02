package com.example.pattern.interpreter;

/**
 * 非终结符表达式（组合式表达式）-- 且操作
 */
public class AndExpression implements AbstractExpression1 {

    private AbstractExpression1 expressionA;
    private AbstractExpression1 expressionB;

    public AndExpression(AbstractExpression1 expressionA, AbstractExpression1 expressionB) {
        this.expressionA = expressionA;
        this.expressionB = expressionB;
    }

    @Override
    public boolean interpret(String context) {
        return this.expressionA.interpret(context) && this.expressionB.interpret(context);
    }
}
