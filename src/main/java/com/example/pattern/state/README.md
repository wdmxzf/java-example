# 策略模式
设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。

状态模式中的行为是由状态来决定的，不同状态下有不同的行为。 状态模式和策略模式的结构几乎一样，但是他们的本质和目的是不同的。状态模式的行为是平行的、不可替换的；策略的模式是彼此独立的、可以相互替换的。
## 定义
当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类。
## 使用场景
* 一个对象的行为取决于它的状态，并且它必须在运行时根据状态改变它的行为。
* 一个操作中含有庞大的多分支结构，并且这些分支决定于对象的状态。（代码中包含多个 if-else、switch-case等）
## 优缺点[[1]](http://c.biancheng.net/view/1388.html)
### 优点
* 结构清晰，把所有与一个特定的状态相关的行为都放入了一个状态对象中，并将不同的状态分割开，满足[单一职责](https://www.jianshu.com/p/322576b3f9ca)
* 减少对象间的依赖，将不同的状态放到不同的对象中
* 职责明确，有利于扩展
### 缺点
* 会增加类或者对象的个数
* 实现比较复杂，使用不当可能导致逻辑混乱
* 对开闭原则的支持并不友好，对于新增加的状态可能需要修改源代码
## UML 图
![状态模式.png](https://upload-images.jianshu.io/upload_images/2209819-18b4d2ed70b7972a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* Controller: 环境类，定义状态接口，维护一个state子类的实例，这个实例定义了对象当前状态
* State： 状态类，可以是接口类，也可以是抽象类，表示该状态下的具体行为
* ConcreteState：具体状态类。
## 代码实现
* 状态接口
```java
/**
 * 状态接口
 */
public interface State {
    public void method1();
    public void method2();
}
```
* 状态实现类
```java
/**
 * 当ON 的时候执行
 */
public class ConcreteStateA implements State {
    public void method1() {
        System.out.println("是ON 状态，可以执行method1");
    }

    public void method2() {
        System.out.println("是ON 状态，可以执行method2");
    }
}
/**
 * 当 OFF 的时候执行
 */
public class ConcreteStateB implements State {
    public void method1() {
        System.out.println("是 OFF 状态，可以执行method1");
    }

    public void method2() {
        System.out.println("是 OFF 状态，可以执行method2");
    }
}
```
* 环境类
```java
/**
 * 环境类
 */
public class Controller implements State {
    private State state;

    private void setState(State state) {
        this.state = state;
    }

    public void on(){
        this.setState(new ConcreteStateA());
    }

    public void off(){
        this.setState(new ConcreteStateB());
    }

    public void method1() {
        this.state.method1();
    }

    public void method2() {
        this.state.method1();
    }
}
```
* 执行
```java
  public static void main(String[] args) {
        Controller controller =new Controller();
        controller.on();
        controller.method1();
        controller.method2();
        controller.off();
        controller.method1();
        controller.method2();
    }
```
* 结果
```shell
是ON 状态，可以执行method1
是ON 状态，可以执行method1
是 OFF 状态，可以执行method1
是 OFF 状态，可以执行method1
```
## 总结
状态模式主要是不同状态下对相同的行为有不同的响应，减少if-else、switch-case 的使用。在我们开发中需要根据业务需求，合理使用设计模式，不要为了使用而使用。
[DEMO](设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。

状态模式中的行为是又状态来决定的，不同状态下有不同的行为。 状态模式和策略模式的结构几乎一样，但是他们的本质和目的是不同的。状态模式的行为是平行的、不可替换的；策略的模式是彼此独立的、可以相互替换的。
## 定义
当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类。
## 使用场景
* 一个对象的行为取决于它的状态，并且它必须在运行时根据状态改变它的行为。
* 一个操作中含有庞大的多分支结构，并且这些分支决定于对象的状态。（代码中包含多个 if-else、switch-case等）
## 优缺点[[1]](http://c.biancheng.net/view/1388.html)
### 优点
* 结构清晰，把所有与一个特定的状态相关的行为都放入了一个状态对象中，并将不同的状态分割开，满足[单一职责](https://www.jianshu.com/p/322576b3f9ca)
* 减少对象间的依赖，将不同的状态放到不同的对象中
* 职责明确，有利于扩展
### 缺点
* 会增加类或者对象的个数
* 实现比较复杂，使用不当可能导致逻辑混乱
* 对开闭原则的支持并不友好，对于新增加的状态可能需要修改源代码
## UML 图
![状态模式.png](https://upload-images.jianshu.io/upload_images/2209819-18b4d2ed70b7972a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* Controller: 环境类，定义状态接口，维护一个state子类的实例，这个实例定义了对象当前状态
* State： 状态类，可以是接口类，也可以是抽象类，表示该状态下的具体行为
* ConcreteState：具体状态类。
## 代码实现
* 状态接口
```java
/**
 * 状态接口
 */
public interface State {
    public void method1();
    public void method2();
}
```
* 状态实现类
```java
/**
 * 当ON 的时候执行
 */
public class ConcreteStateA implements State {
    public void method1() {
        System.out.println("是ON 状态，可以执行method1");
    }

    public void method2() {
        System.out.println("是ON 状态，可以执行method2");
    }
}
/**
 * 当 OFF 的时候执行
 */
public class ConcreteStateB implements State {
    public void method1() {
        System.out.println("是 OFF 状态，可以执行method1");
    }

    public void method2() {
        System.out.println("是 OFF 状态，可以执行method2");
    }
}
```
* 环境类
```java
/**
 * 环境类
 */
public class Controller implements State {
    private State state;

    private void setState(State state) {
        this.state = state;
    }

    public void on(){
        this.setState(new ConcreteStateA());
    }

    public void off(){
        this.setState(new ConcreteStateB());
    }

    public void method1() {
        this.state.method1();
    }

    public void method2() {
        this.state.method1();
    }
}
```
* 执行
```java
  public static void main(String[] args) {
        Controller controller =new Controller();
        controller.on();
        controller.method1();
        controller.method2();
        controller.off();
        controller.method1();
        controller.method2();
    }
```
* 结果
```shell
是ON 状态，可以执行method1
是ON 状态，可以执行method1
是 OFF 状态，可以执行method1
是 OFF 状态，可以执行method1
```
## 总结
状态模式主要是不同状态下对相同的行为有不同的响应，减少if-else、switch-case 的使用。在我们开发中需要根据业务需求，合理使用设计模式，不要为了使用而使用。
*参考：《Android 源码设计模式解析与实践》*
