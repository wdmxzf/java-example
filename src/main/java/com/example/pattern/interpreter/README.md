# 设计模式之解释器模式(Interpreter pattern)
祝大家新年快乐，万事如意，在新的一年里，步步高升，财源滚滚。
设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。

解释器模式，是我们平常很少用到的一种设计模式，也是我认为最难的一种设计模式，书看了一遍，没怎么懂，看看例子，一遍一遍的琢磨，才懂那么一点点。

解释器可以理解为对 上下文（Context）或者说是最终输入的内容的解释。
比如在 java 中String 类型内写运算公式(如："12 + 3 + 4")，它是不能直接运算的，需要我们对他解析才能得到结果，这就需要我们对它进行解释，算出最后结果；
还比如已经提前录入了姓名和性别，在最终输入姓名和性别，就可以筛选出这个人的姓名和性别是否相匹配。

## 定义
定义了一个解释器，来解释给定语言和文法的句子。其实质是把语言中的每个符号定义成一个（对象）类，从而把每个程序转换成一个具体的对象树。
## 使用场景
* 可以将一个需要解释执行的语言中的句子表示为一个抽象语法树
* 一些重复出现的问题可以用一种简单的语言来进行表达
* 一个简单语法需要解释的场景
## 优缺点*[[1]](https://www.runoob.com/design-pattern/interpreter-pattern.html)*
### 优点
* 可扩展性比较好，灵活。 
* 增加了新的解释表达式的方式。 
* 易于实现简单文法。
### 缺点
* 可利用场景比较少。 
* 对于复杂的文法比较难维护。 
* 解释器模式会引起类膨胀。 
* 解释器模式采用递归调用方法。
## UML图
![解释器模式.png](https://upload-images.jianshu.io/upload_images/2209819-e91119d3502c28d3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* AbstractExpression：抽象表达式
* TerminalExpression：终结符表达式
* NonterminalExpression：非终结符表达式。实现文法中与非终结符有关的解释操作
* Context：上下文环境类。包含解释器之外全局信息
* Client：客户类。实际使用类
## 代码实现
* 抽象表达式
```java
/**
 * 抽象表达式
 * 为所有解释器的基础类
 */
public interface AbstractExpression1 {
    boolean interpret(String context);
}
```
* 终结符表达式
```java
/**
 * 终结符表达式
 */
public class TerminalExpression implements AbstractExpression1 {

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    public boolean interpret(String context) {
        return context.contains(this.data);
    }
}
```
* 非终结符表达式
```java
/**
 * 非终结符表达式（组合式表达式）-- 或操作
 */
public class OrExpression implements AbstractExpression1 {

    private AbstractExpression1 expressionA;
    private AbstractExpression1 expressionB;

    public OrExpression(AbstractExpression1 expressionA, AbstractExpression1 expressionB) {
        this.expressionA = expressionA;
        this.expressionB = expressionB;
    }

    @Override
    public boolean interpret(String context) {
        return this.expressionA.interpret(context) || this.expressionB.interpret(context);
    }
}
```
* 执行
```java
public class ClientOne {
    //规则：Robert 和 John 是男性
    public static AbstractExpression1 getMaleExpression(){
        AbstractExpression1 robert = new TerminalExpression("Robert");
        AbstractExpression1 john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie 是一个已婚的女性
    public static AbstractExpression1 getMarriedWomanExpression(){
        AbstractExpression1 julie = new TerminalExpression("Julie");
        AbstractExpression1 married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        AbstractExpression1 isMale = getMaleExpression();
        AbstractExpression1 isMarriedWoman = getMarriedWomanExpression();
        boolean isMan1 = isMale.interpret("John");
        boolean isMan2 = isMale.interpret("Robert");
        boolean isMan3 = isMale.interpret("Bill");
        boolean isMarriedWomen = isMarriedWoman.interpret("Married Julie");
        System.out.println("John is male? \n"+(isMan1?"Yes":"no"));
        System.out.println("Robert is male? \n"+(isMan2?"Yes":"no"));
        System.out.println("Bill is male? \n"+(isMan3?"Yes":"no"));
        System.out.println("Julie is a married women? \n"+(isMarriedWomen?"Yes":"no"));
    }
}
```
* 结果
```shell
John is male? 
Yes
Robert is male? 
Yes
Bill is male? 
no
Julie is a married women? 
Yes
```
## 总结
解释器模式，它是真的难，理解起来也不是很容易，在平常的开发中，我们很少使用到。
以上就是我看书和博客中了解到的内容，在 DEMO 中也写了另一个例子（字符串中运算而计算结果）。

*参考：《Android 源码设计模式解析与实践》、[解释器模式-菜鸟教程](https://www.runoob.com/design-pattern/interpreter-pattern.html)*