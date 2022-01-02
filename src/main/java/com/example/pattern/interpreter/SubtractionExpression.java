package com.example.pattern.interpreter;


/**
 * 减法运算抽象解释器
 */
public class SubtractionExpression extends OperatorExpression {
    public SubtractionExpression(AbstractExpression2 expressionA, AbstractExpression2 expressionB) {
        super(expressionA, expressionB);
    }

    @Override
    int interpret() {
        return expressionA.interpret() - expressionB.interpret();
    }
}
