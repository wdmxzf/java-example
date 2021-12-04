package com.example.pattern.factory.method;

/**
 * 工厂类二 的实现,创建产品A
 */
public class ConcreteFactoryA implements Factory2 {
    public Product createProductMethod2() {
        // ...
        return new ConcreteProductA();
    }
}
