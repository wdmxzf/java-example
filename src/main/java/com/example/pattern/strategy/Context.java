package com.example.pattern.strategy;

/**
 * 连接策略的上下文
 */
public class Context {
    private Strategy strategy;
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * 策略的使用
     */
    public void strategyMethod(){
        this.strategy.method();
        System.out.println("Context:testMethod");
    }

    /**
     * 不使用策略模式使用方法，根据type 类型调用不同的逻辑
     * @param type 类型
     */
    public void test(String type){
        if ("A".equals(type)){
            System.out.println("ConcreteStrategyA:method");
        }else if ("B".equals(type)){
            System.out.println("ConcreteStrategyB:method");
        }else {
            System.out.println("test");
        }
    }
}
