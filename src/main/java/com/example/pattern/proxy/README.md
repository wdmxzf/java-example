设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。
现在我们就介绍下代理模式（Proxy Pattern）。

# 定义
对其他对象提供一种代理以控制对这个对象的访问。
解释：就是不想把自己暴露给别人，委托他人帮自己实现某些功能。
> 网上说的增强，在我看来其实就是保留原有的内容，增加自己的逻辑（他也可以重写父类方法实现，不删除super方法）。

# 使用场景

当无法或不想直接访问某个对象时，或者某个对象不方便访问时，可以通过代理对象，间接访问。想要实现代理，需要委托者和代理者实现相同的接口。最典型的代理模式就是Spring 的AOP(切面)
例如：小明把小刚打了，小刚把小明告了，这时，小刚请了一个律师代理他处理这件事。如果小刚自己处理的话，需要提交申请、进行举证、辩护等诉讼流程。委托律师后，小刚把这些材料委托给律师，律师就可以代替小刚把事情处理了。在处理这些流程时，律师为了诉讼成功，增加了一些自己的东西（这就是我认为的【增强】）。
# 代理模式实现：
代理模式实现方式：静态代理和动态代理、动态代理又分为jdk代理和Cglib 代理。

## 静态代理
静态代理就是定义两个实现类A和B，他们都实现了接口C，B类构造器传入A，这时B就可以代替A 做一些事情了。

*   UML 图
![UML.png](https://upload-images.jianshu.io/upload_images/2209819-53048fed0c0baf12.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


* 示例代码

* 诉讼流程接口（定义业务接口）
```kotlin
package com.example.pattern.proxy.static
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 诉讼流程
 */
interface LitigationProcessInterface {
    // 提交诉讼申请
    fun submitLawsuit();
    // 举证
    fun proof();
    // 辩护
    fun defend();

    fun finish(){
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
        logger.info("----- LitigationProcessInterface::finish   结束");
    }
}
```

*   小刚提供的材料（被代理对象）
```kotlin
package com.example.pattern.proxy.static
import org.slf4j.Logger
import org.slf4j.LoggerFactory
class XiaoGang : LitigationProcessInterface {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun submitLawsuit() {
        logger.info("----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请");
    }

    override fun proof() {
        logger.info("----- XiaoGang::::::proof  小刚举证");
    }

    override fun defend() {
        logger.info("----- XiaoGang::::::defend  小刚辩护");
    }
}
```
*   律师代理（代理对象）
```kotlin
package com.example.pattern.proxy.static
import org.slf4j.Logger
import org.slf4j.LoggerFactory
/**
 * 律师
 */
class Lawyer(private val xiaoGang: XiaoGang) : LitigationProcessInterface {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun submitLawsuit() {
        logger.info("----- Lawyer::submitLawsuit1  律师代替小刚诉讼申请前")
        // 小刚提交的诉讼申请
        xiaoGang.submitLawsuit()
        logger.info("----- Lawyer::submitLawsuit2  律师代替小刚诉讼申请后")
    }
    override fun proof() {
        logger.info("----- Lawyer::proof1  律师在小刚举证内容前")
        // 小刚提交的举证
        xiaoGang.proof()
        logger.info("----- Lawyer::proof2  律师在小刚举证内容后")
    }
    override fun defend() {
        logger.info("----- Lawyer::defend1  律师在小刚辩护内容前")
        // 小刚的辩护
        xiaoGang.defend();
        logger.info("----- Lawyer::defend2  律师在小刚辩护内容后")
    }
}
```
*   流程实现（实现）
```kotlin
package com.example.pattern.proxy.static
object Client {
    @JvmStatic fun main(args: Array<String>) {
        val xiaoGang:LitigationProcessInterface = XiaoGang();
        val lawyer = Lawyer(xiaoGang as XiaoGang)
        lawyer.submitLawsuit()
        lawyer.proof()
        lawyer.defend()
        lawyer.finish()
    }
}
```
*   Log 信息：
```shell
12:22:58.491 [main] INFO com.example.pattern.proxy.static.Lawyer - ----- Lawyer::submitLawsuit1  律师代替小刚诉讼申请前
12:22:58.496 [main] INFO com.example.pattern.proxy.static.XiaoGang - ----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请
12:22:58.496 [main] INFO com.example.pattern.proxy.static.Lawyer - ----- Lawyer::submitLawsuit2  律师代替小刚诉讼申请后
12:22:58.496 [main] INFO com.example.pattern.proxy.static.Lawyer - ----- Lawyer::proof1  律师在小刚举证内容前
12:22:58.496 [main] INFO com.example.pattern.proxy.static.XiaoGang - ----- XiaoGang::::::proof  小刚举证
12:22:58.496 [main] INFO com.example.pattern.proxy.static.Lawyer - ----- Lawyer::proof2  律师在小刚举证内容后
12:22:58.496 [main] INFO com.example.pattern.proxy.static.Lawyer - ----- Lawyer::defend1  律师在小刚辩护内容前
12:22:58.496 [main] INFO com.example.pattern.proxy.static.XiaoGang - ----- XiaoGang::::::defend  小刚辩护
12:22:58.496 [main] INFO com.example.pattern.proxy.static.Lawyer - ----- Lawyer::defend2  律师在小刚辩护内容后
12:22:58.497 [main] INFO com.example.pattern.proxy.static.Lawyer - ----- LitigationProcessInterface::finish   结束
```
静态代理虽然效率挺高，但是它是需要和业务结合使用，实现类都需要实现业务的接口才可以实现功能，这也局限了一个代理对象需要实现一次，如果有另一个代理对象，那么还需要实现另一个代理对象，这时使用起来就不是很方便了。那么动态代理就解决了这个问题。
> 以上是使用接口（interface）方式实现的功能，其实也可以使用 abstract 的方式实现。只不过是implements 改成了extends .

## 动态代理
上面的静态代理是由程序员或者工具生成的代码进行编译，就是说在运行代码时，编译文件就已经存在了。而动态代理是与之相反的，它是通过反射机制动态生成代理对象。
Java 已经给我们提供了一个便捷的代理接口 InvocationHandler 。只要实现它就可以了。

* 示例代码
*   诉讼流程接口（定义业务接口）
```java
package com.example.pattern.proxy.dynamic;
/**
 * 诉讼流程
 */
public interface LitigationProcessInterface {
    // 提交诉讼申请
    void submitLawsuit();
    // 举证
    void proof();
    // 辩护
    void defend();
    // 结束
    void finish();
}
```
*   小刚提供的材料（被代理对象）
```java
package com.example.pattern.proxy.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 小刚需要提供的内容
 */
public class XiaoGang implements LitigationProcessInterface {
    private Logger logger = LoggerFactory.getLogger( this.getClass());
    public void submitLawsuit() {
        logger.info("----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请");
    }

    public void proof() {
        logger.info("----- XiaoGang::::::proof  小刚举证");
    }

    public void defend() {
        logger.info("----- XiaoGang::::::defend  小刚辩护");
    }

    public void finish() {
        logger.info("----- XiaoGang::::::finish   结束");
    }
}
```
*   代理对象
```java
package com.example.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 */
public class DynamicProxy implements InvocationHandler {
    private Object object;
    public DynamicProxy(Object o) {
        this.object = o;
    }

    /**
     * 反射
     *
     * @param proxy  代理实例
     * @param method 方法
     * @param args   方法参数
     * @return 结果
     * @throws Throwable 异常
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.object, args);
    }
}
```
*   实现
```java
package com.example.pattern.proxy.dynamic;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        LitigationProcessInterface xiaoGang = new XiaoGang();
        DynamicProxy dynamicProxy= new DynamicProxy(xiaoGang);
        //获取被代理对象小刚的 ClassLoader
        ClassLoader xiaoGangClassLoader = xiaoGang.getClass().getClassLoader();
        // 动态构造一个代理律师
        LitigationProcessInterface lawyer = (LitigationProcessInterface) Proxy.newProxyInstance(xiaoGangClassLoader, new Class[]{LitigationProcessInterface.class}, dynamicProxy);
        lawyer.submitLawsuit();
        lawyer.proof();
        lawyer.defend();
        lawyer.finish();
        
    }
}
```
*   Log
```shell
13:23:01.047 [main] INFO com.example.pattern.proxy.dynamic.XiaoGang - ----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请
13:23:01.050 [main] INFO com.example.pattern.proxy.dynamic.XiaoGang - ----- XiaoGang::::::proof  小刚举证
13:23:01.050 [main] INFO com.example.pattern.proxy.dynamic.XiaoGang - ----- XiaoGang::::::defend  小刚辩护
13:23:01.050 [main] INFO com.example.pattern.proxy.dynamic.XiaoGang - ----- XiaoGang::::::finish   结束
```
以上代码和静态代理很像，但是当增加另一个代理对象时，静态代理需要重新实现一个类（LitigationProcessInterface），而动态代理则不需要，只要再实例下代理就可以了。
>其实单独定义一个代理对象 DynamicProxy 也是有局限的，它不能够增加额外的东西（也就是所谓的增强），若需要增加额外的东西，需要每个都要重新定义一下，这样和静态代理没有什么区别。

那么我们也可以直接 new 一个 InvocationHandler 对象，这样就可以增加额外的东西了。
```java
package com.example.pattern.proxy.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger( Client.class);
        final LitigationProcessInterface xiaoGang = new XiaoGang();
        // 方法二
        InvocationHandler invocationHandler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("submitLawsuit".equals(method.getName())){
                    logger.info("----- Lawyer::submitLawsuit1  律师代替小刚诉讼申请前");
                    method.invoke(xiaoGang, args);
                    logger.info("----- Lawyer::submitLawsuit2  律师代替小刚诉讼申请后");

                } else if ("proof".equals(method.getName())){
                    logger.info("----- Lawyer::proof1  律师在小刚举证内容前");
                    method.invoke(xiaoGang, args);
                    logger.info("----- Lawyer::proof2  律师在小刚举证内容后");
                } else if ("defend".equals(method.getName())){
                    logger.info("----- Lawyer::defend1  律师在小刚辩护内容前");
                    method.invoke(xiaoGang, args);
                    logger.info("----- Lawyer::defend2  律师在小刚辩护内容后");
                } else if ("finish".equals(method.getName())){
                    logger.info("----- Lawyer::finish1  结束前");
                    method.invoke(xiaoGang, args);
                    logger.info("----- Lawyer::finish2  结束后");
                }
                return null;
            }
        };
        //获取被代理对象小刚的 ClassLoader
        ClassLoader xiaoGangClassLoader = xiaoGang.getClass().getClassLoader();
        // 动态构造一个代理律师
        LitigationProcessInterface lawyer = (LitigationProcessInterface) Proxy.newProxyInstance(xiaoGangClassLoader, new Class[]{LitigationProcessInterface.class}, invocationHandler);
        lawyer.submitLawsuit();
        lawyer.proof();
        lawyer.defend();
        lawyer.finish();
    }
}
```
*   Log
```shell
13:50:04.564 [main] INFO com.example.pattern.proxy.dynamic.Client - ----- Lawyer::submitLawsuit1  律师代替小刚诉讼申请前
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.XiaoGang - ----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.Client - ----- Lawyer::submitLawsuit2  律师代替小刚诉讼申请后
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.Client - ----- Lawyer::proof1  律师在小刚举证内容前
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.XiaoGang - ----- XiaoGang::::::proof  小刚举证
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.Client - ----- Lawyer::proof2  律师在小刚举证内容后
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.Client - ----- Lawyer::defend1  律师在小刚辩护内容前
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.XiaoGang - ----- XiaoGang::::::defend  小刚辩护
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.Client - ----- Lawyer::defend2  律师在小刚辩护内容后
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.Client - ----- Lawyer::finish1  结束前
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.XiaoGang - ----- XiaoGang::::::finish   结束
13:50:04.567 [main] INFO com.example.pattern.proxy.dynamic.Client - ----- Lawyer::finish2  结束后
```


这种Log 是不是和第一种很像了，而它不在需要创建多个代理对象。

## Cglib代理

上面两种代理，都是需要实现特定的接口，才能实现代理功能，或者增强功能。而Spring 提供的Cglib 包则简化了实现接口，只要是单独的对象就可以实现代理。

*   定义被代理对象
```java
package com.example.pattern.proxy.enhancer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 小刚需要提供的内容
 */
public class XiaoGang {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void submitLawsuit() {
        logger.info("----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请");
    }

    public void proof() {
        logger.info("----- XiaoGang::::::proof  小刚举证");
    }

    public void defend() {
        logger.info("----- XiaoGang::::::defend  小刚辩护");
    }

    public void finish() {
        logger.info("----- XiaoGang::::::finish   结束");
    }
}
```

*   代理对象
```java
package com.example.pattern.proxy.enhancer;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyInterceptor implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("----- ProxyFactory:::::: intercept  开始 ");
        //执行目标对象的方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("----- ProxyFactory:::::: intercept  结束 ");
        return result;
    }
}
```
*   实现
```java
package com.example.pattern.proxy.enhancer;

import org.springframework.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(XiaoGang.class);
        //设置回调
        enhancer.setCallback(new ProxyInterceptor());
//        enhancer.setCallback(new MethodInterceptor() {
//            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                // 可以做增强
//                return null;
//            }
//        });
//        enhancer.setCallbackFilter(new CallbackFilter() {
//            public int accept(Method method) {
//                return 0;
//            }
//        });
        //设置代理类对象
        XiaoGang xiaoGang = (XiaoGang) enhancer.create();
        //在调用代理类中方法时会被我们实现的方法拦截器进行拦截
        xiaoGang.submitLawsuit();
        xiaoGang.proof();
        xiaoGang.defend();
        xiaoGang.finish();
    }
}
```
*   LOG
```shell
----- ProxyFactory:::::: intercept  开始 
14:22:11.317 [main] INFO com.example.pattern.proxy.enhancer.XiaoGang$$EnhancerByCGLIB$$4d9ceeba - ----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请
----- ProxyFactory:::::: intercept  结束 
----- ProxyFactory:::::: intercept  开始 
14:22:11.320 [main] INFO com.example.pattern.proxy.enhancer.XiaoGang$$EnhancerByCGLIB$$4d9ceeba - ----- XiaoGang::::::proof  小刚举证
----- ProxyFactory:::::: intercept  结束 
----- ProxyFactory:::::: intercept  开始 
14:22:11.320 [main] INFO com.example.pattern.proxy.enhancer.XiaoGang$$EnhancerByCGLIB$$4d9ceeba - ----- XiaoGang::::::defend  小刚辩护
----- ProxyFactory:::::: intercept  结束 
----- ProxyFactory:::::: intercept  开始 
14:22:11.320 [main] INFO com.example.pattern.proxy.enhancer.XiaoGang$$EnhancerByCGLIB$$4d9ceeba - ----- XiaoGang::::::finish   结束
----- ProxyFactory:::::: intercept  结束 
```
该种代理，比起动态代理更加简单便捷。如果要想扩展方法，则直接new MethodInterceptor 就可以实现，类似于动态代理new InvocationHandler 。
# 总结

学会使用设计模式，可以让我们的代码写的更加好看漂亮。在我们的编程过程中，很多时候我们写的代码，就使用了设计模式，只不过是不清楚罢了。
经过查看博客和书籍，对代理模式有了初步的了解。如果写的不好，请广大网友指正。
demo地址：https://github.com/wdmxzf/java-example/tree/pattern
