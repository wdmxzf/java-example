package com.example.pattern.factory.method;

public class Client {
public static void main(String[] args) {
    System.out.println("工厂类二的实现--------------------");
    Factory2 factory2A = new ConcreteFactoryA();
    Factory2 factory2B = new ConcreteFactoryB();
    factory2A.createProductMethod2().method();
    factory2B.createProductMethod2().method();
}
}
