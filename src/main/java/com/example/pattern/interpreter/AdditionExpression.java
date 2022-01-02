package com.example.pattern.interpreter;

/**
 * 加法运算抽象解释器
 */
public class AdditionExpression extends OperatorExpression {

    public AdditionExpression(AbstractExpression2 expressionA, AbstractExpression2 expressionB) {
        super(expressionA, expressionB);
    }

    @Override
    int interpret() {
        return expressionA.interpret() + expressionB.interpret();
    }
}
