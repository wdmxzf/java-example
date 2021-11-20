# 建造者模式
设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。

## 定义
将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
## 特性
* 方便用户创建复杂对象
* 代码复用性、封装性（将对象构建过程和细节进行封装、复用）
*参考（https://blog.csdn.net/qq_23452385/article/details/89285189）*
## 使用场景
* 当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时。
* 当构造过程必须允许被构造的对象有不同表示时。
* 相同的方法，不同的执行顺序，产生不同的事件结果时。
* 多个成员都可以装配到一个对象中，产生的运行结果又不相同时。
* 类的构成非常复杂时。
* 当初始化一个对象时，参数非常多而且复杂时。

## 示例
在Android 中 Builder 模式使用的还是很普遍的，比如Dialog、Retrofit 等都是用了建造者模式。
下面实现一个简单的Builder 模式。
* 代码示例
```java
// 产品类
public class Test {
    public String title;
    public String message;

    public void showTitle(){
        System.out.println("Test:: title is "+title);
    }
    public void showMessage(){
        System.out.println("Test:: message is "+message);
    }
}
// 建造者类
public class TestBuilder {
    private Test test;
    public TestBuilder() {
        test = new Test();
    }

    public TestBuilder setTitle(String title){
        test.title = title;
        return this;
    }

    public TestBuilder setMessage(String message){
        test.message = message;
        return this;
    }

    public Test build(){
        System.out.println("TestBuilder:: TITLE is "+test.title+"\n"+"MESSAGE is "+test.message);
        return test;
    }
}
```
* 使用方式
```java
    public static void main(String[] args) {
        // 方式一，显示TestBuilder的结果
        new TestBuilder().setTitle("建造者1").setMessage("BUILDER1").build();

        // 方式二，显示Test 内的方法
        Test test = new TestBuilder().setTitle("建造者2").setMessage("BUILDER2").build();
        test.showTitle();
        test.showMessage();
    }
```
* 输出结果
```shell
TestBuilder:: TITLE is 建造者1
MESSAGE is BUILDER1
TestBuilder:: TITLE is 建造者2
MESSAGE is BUILDER2
Test:: title is 建造者2
Test:: message is BUILDER2
```
## 总结
Builder 模式可以根据具体的需求，也可以把Builder 放到私有类中，这样更加直观的表现产品的构造。Builder 具有良好的封装性，使用Builder 模式可以让使用者不必知道构成的细节；而且也容易扩展。唯一不好的地方就是它会产生多余的Builder 对象（类爆炸），消耗内存。