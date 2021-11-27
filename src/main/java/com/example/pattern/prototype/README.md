# 设计模式之原型模式(Prototype)

设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。
## 定义
* 用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象。《Android 源码设计模式解析与实践》
* 原型模式是一种创建型设计模式，Prototype模式允许一个对象再创建另外一个可定制的对象，根本无需知道任何如何创建的细节,工作原理是:通过将一个原型对象传给那个要发动创建的对象，这个要发动创建的对象通过请求原型对象拷贝它们自己来实施创建。[[1]](https://baike.baidu.com/item/原型模式/4941014?fr=aladdin)
## 使用场景
* 类初始化需要消耗很多资源（数据、硬件资源等）。
* 通过 new 产生一个对象需要非常繁琐的数据准备或访问权限。
* 一个对象需提供给其他对象访问，而且哥哥调用者可能都需要修改起值时，可以考虑使用原型模式拷贝多个对象共调用者使用，即保护性拷贝。

说到拷贝，我们再说下深拷贝和浅拷贝。
## 深拷贝和浅拷贝
* 浅拷贝
创建一个新对象，新对象的属性和原来对象完全相同，对于非[基本类型](https://blog.csdn.net/wdmxzf/article/details/53417971)属性，仍指向原有属性所指向的对象的内存地址。
常用的方法有：一一赋值、clone()等。
> 需要注意的是：对于我们常用【=】它不属于浅拷贝，属于赋值，是同一个对象。如果是[基本类型](https://blog.csdn.net/wdmxzf/article/details/53417971)我们拷贝的是它的值；如果是对象则是拷贝的是对象内存地址的引用，其实他们还是同一个对象。
* 深拷贝
创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
常用的方法有：层层拷贝（每个元素都需要实现`clone()`方法）、序列化反序列化、Gson 转换、构造函数（相当重新new一个对象，一一赋值）等

## 示例
* 对象类
```java
/**
 * 元素类
 */
public class Children implements Cloneable {
    private String content;

    @Override
    protected Children clone() throws CloneNotSupportedException {
        return (Children)super.clone();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
/**
 * 原型类
 */
public class Prototype implements Cloneable {

    private String message;
    private String title;
    private Children children;
    // ...

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        // 浅拷贝
        return (Prototype)super.clone();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }
}
```
* 实现
```java
    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype1 = new Prototype();
        prototype1.setTitle("Title1");
        prototype1.setMessage("Message1");
        Children children = new Children();
        children.setContent("Content1");
        prototype1.setChildren(children);
        System.out.println("prototype1------\n    hashCode = "+prototype1.hashCode()+"\n    title ="+prototype1.getTitle()+"\n    message="+prototype1.getMessage());
        System.out.println("Children1-------\n    hashCode = "+prototype1.getChildren().hashCode()+"\n    content="+prototype1.getChildren().getContent());
        Prototype prototype2 = prototype1.clone();
        System.out.println("prototype2------\n    hashCode = "+prototype2.hashCode()+"\n    title ="+prototype2.getTitle()+"\n    message="+prototype2.getMessage());
        System.out.println("Children2-------\n    hashCode = "+prototype2.getChildren().hashCode()+"\n    content="+prototype2.getChildren().getContent());
        prototype2.getChildren().setContent("Content2");
        System.out.println("prototype11------\n    hashCode = "+prototype1.hashCode()+"\n    title ="+prototype1.getTitle()+"\n   message="+prototype1.getMessage());
        System.out.println("Children11-------\n    hashCode = "+prototype1.getChildren().hashCode()+"\n    content="+prototype1.getChildren().getContent());
        System.out.println("prototype22------\n    hashCode = "+prototype2.hashCode()+"\n    title ="+prototype2.getTitle()+"\n   message="+prototype2.getMessage());
        System.out.println("Children22-------\n    hashCode = "+prototype2.getChildren().hashCode()+"\n    content="+prototype2.getChildren().getContent());
    }
```
* 结果
```shell
prototype1------
    hashCode = 531885035
    title =Title1
    message=Message1
Children1-------
    hashCode = 1418481495
    content=Content1
prototype2------
    hashCode = 303563356
    title =Title1
    message=Message1
Children2-------
    hashCode = 1418481495
    content=Content1
prototype11------
    hashCode = 531885035
    title =Title1
   message=Message1
Children11-------
    hashCode = 1418481495
    content=Content2
prototype22------
    hashCode = 303563356
    title =Title1
   message=Message1
Children22-------
    hashCode = 1418481495
    content=Content2
```
从上边的例子我们可以看出 Prototype 的 clone() 方法只是创建它自己本身新对象，而它的元素 Children 还是指向了原有的地址，这就是浅拷贝。要是想实现深拷贝，只需要重写 Prototype 的 clone() 方法。
```java
    @Override
    protected Prototype2 clone() throws CloneNotSupportedException {
        // 浅拷贝
        Prototype2 prototype2 = (Prototype2)super.clone();
        prototype2.children = this.children.clone();
        return prototype2;
    }
```
```shell
prototype1------
    hashCode = 531885035
    title =Title1
    message=Message1
Children1-------
    hashCode = 1418481495
    content=Content1
prototype2------
    hashCode = 303563356
    title =Title1
    message=Message1
Children2-------
    hashCode = 135721597
    content=Content1
prototype11------
    hashCode = 531885035
    title =Title1
   message=Message1
Children11-------
    hashCode = 1418481495
    content=Content1
prototype22------
    hashCode = 303563356
    title =Title1
   message=Message1
Children22-------
    hashCode = 135721597
    content=Content2
```
以上对原型模式（深拷贝、浅拷贝）进行了举例。接下来我们再说下它的优缺点。
## 优缺点
### 优点
* 性能高：原型模式是在内存中二进制流的拷贝，要比直接new 一个对象性能好，特别是在循环体中产生大量对象时，
* 简化流程：可以利用深拷贝，实现状态记录、回退等功能。
### 缺点
* 构造函数不会执行：原型模式是在内存中进行拷贝的，会跳过构造函数。
* 实现繁琐：必须实现clone()方法
* 拷贝风险：浅拷贝和深拷贝，一旦使用不正确，就会造成大灾难。
## 总结
浅拷贝和深拷贝有多种实现方法，这里只用一种方法举例，其他的方法可以参照[深拷贝](https://www.cnblogs.com/xinruyi/p/11537963.html)和[浅拷贝](https://www.cnblogs.com/shakinghead/p/7651502.html)。
原型模式的宗旨就是对象的拷贝，节省创建多次创建新对象带来的消耗，提高性能。但是也需要注意深浅拷贝带来的问题。

*参考*
* *[原型模式](https://baike.baidu.com/item/原型模式/4941014?fr=aladdin)*
* *《Android 源码设计模式解析与实践》*