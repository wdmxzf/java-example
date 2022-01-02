package com.example.pattern.interpreter;

import java.util.Stack;

/**
 * 计算
 */
public class Calculator {

    private Stack<AbstractExpression2> mExpStack = new Stack<AbstractExpression2>();

    /**
     *
     * @param expression 传输的字符串必须用空格【' '】隔开
     *                   例如："3 + 4"
     */
    public Calculator(String expression) {
        AbstractExpression2 expressionA, expressionB;
        String[] elements = expression.split(" ");
        for (int i= 0; i<elements.length;i++){
            switch (elements[i].charAt(0)){
                case '+':
                    expressionA = mExpStack.pop();
                    expressionB = new NumExpression(Integer.parseInt(elements[++i]));
                    mExpStack.push(new AdditionExpression(expressionA, expressionB));
                    break;
                case '-':
                    expressionA = mExpStack.pop();
                    expressionB = new NumExpression(Integer.parseInt(elements[++i]));
                    mExpStack.push(new SubtractionExpression(expressionA, expressionB));
                    break;
                default:
                    mExpStack.push(new NumExpression(Integer.parseInt(elements[i])));
                    break;
            }
        }
    }

    /**
     * 计算结果
     * @return 结果
     */
    public int calculate(){
        return mExpStack.pop().interpret();
    }

}
