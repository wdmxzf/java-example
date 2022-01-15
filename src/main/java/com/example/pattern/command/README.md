# 设计模式之命令模式(Command Pattern)
设计模式中有六大原则和二十三设计模式。
其中**六大原则**分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
**二十三设计模式**：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。

命令模式，看这个名字就知道它是以命令为终极目标，比如开机命令、关机命令等。
命令模式相对于其他设计模式来说，它并没有那么多的条条框框，它不是一个“规矩”的模式，它比其他模式更加灵活多变。

## 定义
将一个请求封装成一个对象，从而让用户使用不同的请求把客户端参数化；对请求排队或者记录请求日志，以及支持可撤销的操作。
## 使用场景
认为是命令的地方都可以使用命令模式。
* 系统需要将请求调用者和请求接收者解耦，使得调用者和接收者不直接交互
* 系统需要在不同的时间指定请求、将请求排队和执行请求
* 系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作
* 系统需要将一组操作组合在一起，即支持宏命令
## 优缺点
### 优点
* 降低耦合度
* 灵活性更高，新的命令可以很容易加到系统中
* 实现简单，可以比较容易的设计一个命令
* 调用同一方法实现不同功能
### 缺点
* 类爆炸，使用命令模式可能会导致某些系统有过多的具体命令类
## UML图
![在这里插入图片描述](https://img-blog.csdnimg.cn/69b513d95a0d460396126c2a20381515.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAd2RteHpm,size_20,color_FFFFFF,t_70,g_se,x_16#pic_center)
* Receiver：接收者角色。负责具体实施或执行一个请求，真正执行命令的对象。任何类都可能成为一个接收者，只要它能够实现命令要求实现的相应功能
* Command：命令角色。定义所有具体命令类的抽象接口
* ConcreteCommand：具体命令角色。命令接口实现对象，是“虚”的实现；通常会持有接收者，并调用接收者的功能来完成命令要执行的操作
* Invoker：请求者角色。要求命令对象执行请求，通常会持有命令对象，可以持有很多的命令对象。这个是客户端真正触发命令并要求命令执行相应操作的地方，也就是说相当于使用命令对象的入口
## 代码实现
* 接收者
```java
/**
 * 接收者
 */
public class Receiver {
    // 执行具体命令逻辑的方法
    public void action1(){
        System.out.println("执行具体操作逻辑1");
    }
}
```
* 命令角色
```java
/**
 * 命令接口
 */
public interface Command {
    /**
     * 执行命令
     */
    void execute();
}
```
* 命令具体实现
```java
/**
 * 具体命令角色
 */
public class ConcreteCommand1 implements Command {
    private Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        // 调用接收者的相关方法来执行具体逻辑
        receiver.action1();
    }
}
```
* 请求者角色
```java
/**
 * 请求者角色
 */
public class Invoker {
    private Command command1;
    
    /**
     * 执行请求方法
     */
    public void action1(){
        // 调用具体命令对象的相关方法，执行具体命令
        command1.execute();
    }

    public Command getCommand1() {
        return command1;
    }

    public void setCommand1(Command command1) {
        this.command1 = command1;
    }
}
```
* 执行
```java
public static void main(String[] args) {
    // 定义接收者（执行具体逻辑）
    Receiver receiver =new Receiver();
    // 根据接收者构造具体命令对象
    Command command1 = new ConcreteCommand1(receiver);
    Command command2 = new ConcreteCommand2(receiver);
    // 根据命令对象构造请求对象
    Invoker invoker = new Invoker();
    invoker.setCommand1(command1);
    invoker.setCommand2(command2);
    // 发送命令请求
    invoker.action1();
    invoker.action2();
}
```
* 结果
```shell
执行具体操作逻辑1
执行具体操作逻辑2
```
## 总结
命令模式看起来和策略模式很像（策略模式好像和所有模式都好像🤦‍♂️），
**Command模式**用于从需要完成的操作中创建一个对象 - 获取操作及其参数并将它们包装在要记录的对象中，保留用于撤消随着时间的推移，将会有大量不同的Command对象通过系统中的给定点，并且Command对象将包含描述所请求操作的不同参数。
**策略模式**则用于指定如何应该完成的事情，并插入更大的对象或方法以提供特定的算法。排序策略可能是合并排序，可能是插入排序，也可能是更复杂的事情，如果列表大于某个最小大小，则仅使用合并排序。策略对象很少受到Command对象的大规模混乱，而是经常用于配置或调优目的。
这两种模式都涉及将包含它们的原始类中的单个操作的代码和可能的参数分解为另一个对象以提供独立的可变性。不同之处在于实践中遇到的用例以及每种模式背后的意图。

*参考*
*《Android 源码设计模式解析与实践》*
*[策略模式和命令模式的不同](https://www.thinbug.com/q/4834979)*