package com.example.pattern.factory.abstractt;

public class Client {
public static void main(String[] args) {
    System.out.println("工厂类三的实现--------------------");
    Factory3 factory3 = new ConcreteFactory();
    ConcreteProductA concreteProductA2 = factory3.createProductMethod2(ConcreteProductA.class);
    concreteProductA2.method();
}
}
