package com.example.pattern.factory.simple;

/**
 * 工厂类一
 */
public class Factory1 {
    public static final String PRODUCT_A = "productA";
    public static final String PRODUCT_B = "productB";
    // 实现方式一
    public Product createProductMethod1(String type){
        if (PRODUCT_A.equals(type)) {
            // ...
            return new ConcreteProductA();
        } else if (PRODUCT_B.equals(type)) {
            // ...
            return new ConcreteProductB();
        }
        return null;
    }
}
