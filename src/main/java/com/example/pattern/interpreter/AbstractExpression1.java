package com.example.pattern.interpreter;

/**
 * 抽象表达式
 * 为所有解释器的基础类
 */
public interface AbstractExpression1 {
    boolean interpret(String context);
}
