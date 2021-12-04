package com.example.pattern.factory.abstractt;

/**
 * 工厂类三 的实现
 */
public class ConcreteFactory extends Factory3 {
    public <T extends Product> T createProductMethod2(Class clazz) {
        Product product = null;
        try {
            // ...
            product = (Product) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
