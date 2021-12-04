package com.example.pattern.factory.simple;

public class Client {
public static void main(String[] args) {
    System.out.println("工厂类一 的实现--------------------");
    Factory1 factory = new Factory1();
    // 直接使用method
    factory.createProductMethod1(Factory1.PRODUCT_A).method();
    factory.createProductMethod1(Factory1.PRODUCT_B).method();
    System.out.println("--------------------");
    // 获取对象后实现
    ConcreteProductA concreteProductA = (ConcreteProductA) factory.createProductMethod1(Factory1.PRODUCT_A);
    ConcreteProductB concreteProductB = (ConcreteProductB) factory.createProductMethod1(Factory1.PRODUCT_B);
    concreteProductA.method();
    concreteProductB.method();
}
}
