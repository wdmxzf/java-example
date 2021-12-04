package com.example.pattern.factory.abstractt;

/**
 * 工厂类三
 */
public abstract class Factory3 {

    // 实现方式三
    public abstract <T extends Product> T createProductMethod2(Class clazz);
}
