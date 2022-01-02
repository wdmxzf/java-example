package com.example.pattern.interpreter;

/**
 * 运算符抽象解释器
 * 运算基础类
 */
public abstract class OperatorExpression extends AbstractExpression2 {
    protected AbstractExpression2 expressionA;
    protected AbstractExpression2 expressionB;

    public OperatorExpression(AbstractExpression2 expressionA, AbstractExpression2 expressionB) {
        this.expressionA = expressionA;
        this.expressionB = expressionB;
    }
}
