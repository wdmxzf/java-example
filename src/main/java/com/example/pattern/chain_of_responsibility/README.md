[[toc]]
# 责任链模式(ChainOfResponsibility Pattern)

哈哈哈～
面包终于不是馒头味啦！！！！

设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。

责任链模式，也叫做职责链模式。就像上下级关系一样，例如：在公司中要报费用，首先需要自己填表发起申请，然后项目经理审批，之后是部门经理审批，再最后财务才会把钱给你。
什么是链，首尾多个节点想连接构成链，而且链具有很好的灵活性。比如：很多公司，报销费用，可以直接越过项目经理直接到部门经理。
当然对于每个节点，也就是每个环节，都是由不同的逻辑处理的，开始都是由链首开始，链尾结束。
在我们实际开发中，见过很多责任链模式，比如Spring 中的拦截器、Android中的有序广播等。
## 定义
使多个对象都有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链传递该请求，直到有对象处理它为止。
## 使用场景
* 多个对象可以处理同一请求，但具体由哪个对象处理则在运行时动态决定
* 在请求处理者不明确的情况下向多个对象中的一个提交一个请求
* 需要动态指定一组对象处理请求
## 优缺点
### 优点
* 降低耦合度，将发起者和接收者分割开
* 简化对象，使对象不需要知道链结构
* 增强给对象指派职责的灵活性，允许动态新增或者删除责任链
* 良好的扩展性，使增加新的处理类方便
### 缺点
* 不能保证请求一定被接收
* 系统性能将受到一定影响
* 调试不方便
* 可能造成循环调用
* 代码量会比较大
## UML 图
![责任链模式.png](https://upload-images.jianshu.io/upload_images/2209819-5e011c2740126ec5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* Handler：抽象处理者角色，声明一个请求处理的方法，并在其中保持一个对下一个处理节点Handler 对象的引用
* ConcreteHandler：具体处理者角色，对请求进行处理，如果不能处理则将该请求转发给下一个节点对象
## 代码实现
* 抽象处理者角色
```java
/**
 * 抽象处理者角色
 */
public abstract class Handler {
    public static String TYPE_1 = "HANDLER_1";
    public static String TYPE_2 = "HANDLER_2";
    public static String TYPE_3 = "HANDLER_3";

    // 下一节点对象
    protected Handler nextHandler;

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 处理方法
     * @param condition 处理条件
     */
    public abstract void handlerRequest(Object condition);
}
```
* 具体处理者角色
```java
/**
 * 具体处理者角色
 */
public class ConcreteHandler1 extends Handler {
    /**
     * 具体处理方法
     * @param condition 处理条件
     */
    public void handlerRequest(Object condition) {
        String type = (String) condition;
        if (TYPE_1.equals(type)){
            System.out.println("handlerRequest : ConcreteHandler1");
        }else {
            System.out.println("ConcreteHandler1: next");
            nextHandler.handlerRequest(condition);
        }
    }
}
```
* 执行
```java
    public static void main(String[] args) {
        ConcreteHandler1 handler1 = new ConcreteHandler1();
        ConcreteHandler2 handler2 = new ConcreteHandler2();
        ConcreteHandler3 handler3 = new ConcreteHandler3();
        handler1.nextHandler = handler2;
        handler2.nextHandler = handler3;
        handler3.nextHandler = handler1;
        handler1.handlerRequest(Handler.TYPE_3);
    }
```
* 结果
```shell
ConcreteHandler1: next
ConcreteHandler2: next
handlerRequest : ConcreteHandler3
```
## 总结
责任链模式，很适合IF嵌套、switch 嵌套等条件嵌套中使用，可以很好的进行代码解耦，提高代码灵活性；当然对于过度使用它，也会对系统的性能造成一定的影响。