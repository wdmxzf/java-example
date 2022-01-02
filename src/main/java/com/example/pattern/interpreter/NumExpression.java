package com.example.pattern.interpreter;

/**
 * 数字解释器
 * 仅仅解释数字
 */
public class NumExpression extends AbstractExpression2 {
    private int num;

    public NumExpression(int num) {
        this.num = num;
    }

    @Override
    public int interpret() {
        return num;
    }
}
