# 策略模式

设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。

策略模式在我们开发中也是比较常见的，例如在Android 常使用MVP 就和策略模式很相近，java 开发中我们也会经常 set 一个类过去等等，这些都是根据策略模式过来的。
使用过工厂模式的人，可能发现工厂模式和策略模式很像，用法什么的很类似，其实他们是不一样的。工厂模式主要是为了创建对象，二策略模式主要是为了使用对象。
## 定义
策略模式定义了一些列的算法，并将每个算法封装起来，而且它们还可以相互替换，策略模式让算法独立与使用它的客户而独立变化。
> 对象有某个行为，但是在不同的场景中，该行为有不同的实现算法。
## 使用场景
* 针对同一类型的问题多种处理
* 需要安全的封装多种同一类型的操作，对客户隐藏具体实现。
* 出现同一抽象类有多个子类，而又需要使用 if-else 或者switch-case 来选择具体子类时。
## 优缺点
### 优点
* 结构清晰明了，使用简单直观
* 耦合度相对较低，扩展方便
* 操作封装更彻底，数据更加安全
### 缺点
* 随着策略的增加，类 会变得越来越多，容易造成类爆炸
* 使用者必须知道所有策略类，并自行解决使用哪个策略
## UML图
![截屏2021-12-12 下午2.15.48.png](https://upload-images.jianshu.io/upload_images/2209819-4d76ad3227178849.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* Context类 用来操作策略的上下文
* Strategy类 策略的抽象
* ConcreteStrategyA、ConcreteStrategyB类具体实现类
## 代码实现
* 不是用策略模式的实现方法
```java
   /**
     * 不使用策略模式使用方法，根据type 类型调用不同的逻辑
     * @param type 类型
     */
    public void test(String type){
        if ("A".equals(type)){
            System.out.println("ConcreteStrategyA:method");
        }else if ("B".equals(type)){
            System.out.println("ConcreteStrategyB:method");
        }else {
            System.out.println("test");
        }
    }
   // 使用方式
    public static void main(String[] args) {
        // 不使用策略模式使用方法
        Context test =new Context();
        test.test("A");
    }
```
当不是用策略模式时，我们使用if else 或者 switch case 对业务进行判断，这样很容造成高耦合，不方便后续人员进行维护。而使用策略模式，会大大降低耦合度，只需要在外层简单调用下就可以了。
* 使用策略
```java
/**
 * 策略接口
 */
public interface Strategy {
    // 策略算法
    void method();
}

/**
 * 策略实现类A
 */
public class ConcreteStrategyA implements Strategy {
    /**
     * 策略算法实现A
     */
    public void method() {
        System.out.println("ConcreteStrategyA:method");
    }
}

/**
 * 策略实现类B
 */
public class ConcreteStrategyB implements Strategy {
    /**
     * 策略算法实现B
     */
    public void method() {
        System.out.println("ConcreteStrategyB:method");
    }
}

/**
 * 连接策略的上下文
 */
public class Context {
    private Strategy strategy;
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    /**
     * 策略的使用
     */
    public void strategyMethod(){
        this.strategy.method();
        System.out.println("Context:testMethod");
    }
}

// 使用
    public static void main(String[] args) {
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
```
> 结果
> ```shell
>ConcreteStrategyA:method
>Context:testMethod
>--------------------------
>ConcreteStrategyB:method
>Context:testMethod
>```

## 总结
策略模式主要是分离算法，减少代码的耦合度，而且这个模式很好遵循了开闭原则。在书写代码中，多多使用设计模式，可以让代码更加优美。

*参考：《Android 源码设计模式解析与实践》*
