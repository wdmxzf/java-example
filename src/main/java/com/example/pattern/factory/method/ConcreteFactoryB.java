package com.example.pattern.factory.method;

/**
 * 工厂类二 的实现,创建产品B
 */
public class ConcreteFactoryB implements Factory2 {
    public Product createProductMethod2() {
        // ...
        return new ConcreteProductB();
    }
}
