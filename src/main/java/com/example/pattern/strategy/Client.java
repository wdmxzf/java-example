package com.example.pattern.strategy;

public class Client {
    public static void main(String[] args) {
        // 不使用策略模式使用方法
//        Context test =new Context();
//        test.test("A");
//        System.out.println("==========================");
        Context context =new Context();
        // 使用 ConcreteStrategyA 算法
        context.setStrategy(new ConcreteStrategyA());
        // ConcreteStrategyA method方法
        context.strategyMethod();
        System.out.println("--------------------------");
        // 使用 ConcreteStrategyB 算法
        context.setStrategy(new ConcreteStrategyB());
        // ConcreteStrategyB method方法
        context.strategyMethod();
    }
}
