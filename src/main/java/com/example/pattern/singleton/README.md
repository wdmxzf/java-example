设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。
现在我们就介绍下单例模式（Singleton）。
# 单例模式（Singleton）
单例模式应该是我们开发中使用最多的一种设计模式，不管是资深的还是新手程序员，因该都用过它。
## 定义
一个类有且仅有一个实例，并且自行实例化向整个系统提供。
## 使用场景
确保某个类在项目中有且只有一个对象，避免产生过多的对象浪费资源。例如：
* 工具类（时间转换、图片加载等）
* 网络请求IO 操作等
## 实现
单例模式有很多实现方式，例如：
* 懒汉式—线程不安全
* 懒汉式—线程安全
* 饿汉方式
* 双检锁式
* 登记式
* 枚举
### 懒汉式—线程不安全
最基础的实现方式，线程上下文单例，不需要共享给所有线程，也不需要加synchronize之类的锁，以提高性能。
* 示例
```java
/**
 * 单例模式
 */
public class Singleton {
    private static Singleton instance;
    // 懒汉式—线程不安全
    public static Singleton getInstance1(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
```
然而它有个致命缺点，就是在两个相同的线程中同时调用了`getInstance1()` 时，就会在这两个线程中产生不同的Singleton 对象。单例的作用就相当没有了。由于它的线程不安全，所以有了下面的方式。
### 懒汉式—线程安全
加上synchronize之类保证线程安全的基础上的懒汉模式，相对性能很低，大部分时间并不需要同步。
* 示例
```java
/**
 * 单例模式
 */
public class Singleton {
    private static Singleton instance;
    // 懒汉式—线程安全
    public static synchronized Singleton getInstance2(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
```
这样写，它是线程安全了；但由于它是同步方法，在多线程调用它时，都会synchronized下，从而效率低下。在使用的过程中为了提高效率，所以我们有了如下方式
### 饿汉方式
指全局的单例实例在类装载时构建。它天生就是线程安全的。
* 示例
```java
/**
 * 单例模式
 */
public class Singleton {
    // 饿汉式
    private static Singleton instance2 = new Singleton();
    public static Singleton getInstance3(){
        return instance2;
    }
}
```
它是线程安全了，但是如果这个类我一直不使用，由于类初始化时，就已经实例它了，所以它会一直占着资源不释放。鉴于这种情况又有了如下方式。
### 双检锁式
在懒汉式基础上利用synchronize关键字和volatile关键字确保第一次创建时没有线程间竞争而产生多个实例，仅第一次创建时同步，性能相对较高
* 示例
```java
/**
 * 单例模式
 */
public class Singleton {
    // 双检锁式
    private static volatile Singleton instance3;
    public static Singleton getInstance3(){
        if (null == instance3){
            synchronized (Singleton.class){
                if (null == instance3){
                    instance3 = new Singleton();
                }
            }
        }
        return instance3;
    }
}
```
它是我们平时开发过程中使用单例模式最多的一种方式。它线程安全而且效率也提高了，但是它第一次加载时反应慢，偶尔也会加载失败。在高并发下也会有一些缺陷，虽然概率很小。为了优化它，出现了如下的方式。
### 登记式
作为创建类的全局属性存在，创建类被装载时创建。
* 示例
```java
/**
 * 单例模式
 */
public class Singleton {

    // 登记式
    public static Singleton getInstance4(){
        return SingletonHolder.instance;
    }
    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }
}
```
当第一次记载`Singleton` 时并不会初始化`instance`，只有第一次调用`getInstance4()`时才会实例化。它不仅保证线程安全、也能保证对象的唯一性，同时也延迟了单例的实例化。***so 它也是最为推荐的一种单例模式***
这种也叫做**静态内部类单例模式**。还有一种是**容器式单例模式**。
* 示例
```java
/**
 * 单例模式
 */
public class Singleton {
    // 登记式-容器式单例模式
    private static Map<String, Object> singletonMap = new HashMap<String, Object>();
    public static Object getInstance5(Class clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String className = clazz.getName();
        if (ObjectUtils.isEmpty(className)){
            className = Singleton.class.getName();
        }
        if (!singletonMap.containsKey(className) || null == singletonMap.get(className)){
            singletonMap.put(className, (Singleton)Class.forName(className).newInstance());
        }
        return singletonMap.get(className);
    }
}
```
以上这种方式，可以把多种单例类型放到统一的一个map 中管理，降低了我们使用的成本。也对用户隐藏了具体实现。降低耦合度。
最后在说下**枚举单例**
### 枚举
java中枚举类本身也是一种单例模式。最重要的是枚举实例的创建是线程安全的，并且在任何情况下他都是一个单例。
* 示例
```java
/**
 * 枚举单例
 */
public enum SingletonEnum {
    INSTANCE;
    public void method(){
        System.out.println("singleton");
    }
}
public static void main(String[] args) {
    SingletonEnum.INSTANCE.method();
}
```
这个方式使用的很少，但是它真的很好用，不需要担心序列化和反序列化的问题。如果是其他单例反序列化时，需要增加一个`readResolve()` 的函数，返回单例实例。
```java
    private Object readResolve(){
        return instance;
    }
```
> `readResolve()` 是反序列化提供的钩子函数。防止单例在反序列化时再重新new 一个新的对象。

# 总结
单例模式在我们开发中是经常用到的一种设计模式，使用哪种方式的单例，我们应该根据实际的需求去选型，而不是一味的跟风。了解更多的设计模式才能写出优雅的代码。