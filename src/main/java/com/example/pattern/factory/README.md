# 工厂模式
设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。
工厂模式是我们在java 开发中常用的一种，例如在 Android 中 onCreate等生命周期方法就是一种工厂模式。它分为简单工厂、工厂方法、抽象工厂。
## 定义
定义一个用于创建对象的接口，让子类决定实例化哪个类。
## 场景
* 复杂对象的创建
> 例如：Android 中的 Activity 的创建，它需要7个生命周期。
* 类本身有很多子类
* 降低代码重复
* 不能直接new 实例化对象
## 优缺点[[1]](https://www.runoob.com/design-pattern/factory-pattern.html)
### 优点
* 一个调用者想创建一个对象，只要知道其名称就可以了，不需要知道实现逻辑
* 扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以
* 屏蔽产品的具体实现，调用者只关心产品的接口
### 缺点
* 每次增加一个子类或者实现，都需要创建一个新类，容易造成类爆炸
* 类爆炸一定程度造成系统复杂度
* 增加系统具体类的依赖

## 工厂模式[[2]](https://www.cnblogs.com/yssjun/p/11102162.html)
### UML
![简单工厂](https://img-blog.csdnimg.cn/aa5d3b70522149248d382c42bd446799.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAd2RteHpm,size_20,color_FFFFFF,t_70,g_se,x_16)
### 简单工厂
* 产品类
```java
/**
 * 产品接口/产品抽象类
 */
//public abstract class Product{} // 也可以使用abstract 方式
public interface Product {
    void method();
}
/**
 * 产品A 实现
 */
public class ConcreteProductA implements Product {
    public void method() {
        System.out.println("ConcreteProductA");
    }
}
/**
 * 产品B实现
 */
public class ConcreteProductB implements Product {
    public void method() {
        System.out.println("ConcreteProductB");
    }
}
```
* 工厂类
```java
/**
 * 工厂类一
 */
public class Factory1 {
    public static final String PRODUCT_A = "productA";
    public static final String PRODUCT_B = "productB";
    // 实现方式一
    public Product createProductMethod1(String type){
        if (PRODUCT_A.equals(type)) {
            return new ConcreteProductA();
        } else if (PRODUCT_B.equals(type)) {
            return new ConcreteProductB();
        }
        return null;
    }
}
```
* 使用
```java
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
```
* 结果
```shell
工厂类一 的实现--------------------
ConcreteProductA
ConcreteProductB
--------------------
ConcreteProductA
ConcreteProductB
```
### 工厂方法
* 产品类同上
* 工厂类
```java
/**
 * 工厂类二
 */
public interface Factory2 {
    // 实现方式二
    Product createProductMethod2();
}

/**
 * 工厂类二 的实现,创建产品A
 */
public class ConcreteFactoryA implements Factory2 {
    public Product createProductMethod2() {
        return new ConcreteProductA();
    }
}

/**
 * 工厂类二 的实现,创建产品B
 */
public class ConcreteFactoryB implements Factory2 {
    public Product createProductMethod2() {
        return new ConcreteProductB();
    }
}
```
* 使用
```java
public static void main(String[] args) {
    System.out.println("工厂类二的实现--------------------");
    Factory2 factory2A = new ConcreteFactoryA();
    Factory2 factory2B = new ConcreteFactoryB();
    factory2A.createProductMethod2().method();
    factory2B.createProductMethod2().method();
}
```
* 结果
```shell
工厂类二的实现--------------------
ConcreteProductA
ConcreteProductB
```
### 抽象工厂
主要是通过反射来创建产品类。
* 产品类同上
* 工厂类
```java
/**
 * 工厂类三
 */
public abstract class Factory3 {

    // 实现方式三
    public abstract <T extends Product> T createProductMethod2(Class clazz);
}

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
```
* 使用
```java
public static void main(String[] args) {
    System.out.println("工厂类三的实现--------------------");
    Factory3 factory3 = new ConcreteFactory();
    ConcreteProductA concreteProductA2 = factory3.createProductMethod2(ConcreteProductA.class);
    concreteProductA2.method();
}
```
* 结果
```shell
工厂类三的实现--------------------
ConcreteProductA
 ```
## 总结
工厂模式是一个很好的模式，有优点也有缺点，在我们使用的过程中，应该根据自己的需求选择相应的模式。

