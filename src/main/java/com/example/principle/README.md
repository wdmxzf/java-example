* 设计模式中有六大原则和二十三设计模式。
其中六大原则分别为：单一职责原则、开闭原则、里氏替换原则、依赖倒置原则、接口隔离原则、迪米特原则。
* 二十三设计模式：单例模式、Builder 模式、原型模式、工厂方法模式、抽象工厂模式、策略模式、状态模式、责任链模式、解释器模式、命令模式、观察者模式、备忘录模式、迭代器模式、模版方法模式、访问者模式、中介模式、代理模式、组合模式、适配器模式、装饰模式、享元模式、外观模式、桥接模式。

现在我们就介绍下设计原则。
# 单一职责原则（SRP：Single Responsibility Principle）
* 定义
就一个类而言，应该仅有一个引起变化的原因。应该只有一个职责。

说白了就是，一个类或者一个方法就应该干一件事情。
* 举例
例如图片加载分为图片下载、保存到缓存、图片显示等部分。我们应该把这几个部分分别定义单独的方法；如果使用率高，最好定义成单独类，而不是一个类一个方法全搞定。
* 不好的代码
```java
public class ImageLoader {
    public void displayImage(ImageView imageView, String url){
        // 下载
        // 图片显示
        // 图片缓存
    }
}
```
* 优化
```java
public class ImageLoader {
    /**
     * 下载
     */
    public Bitmap download(String url){
        Bitmap bitmap = null;
        // 下载图片
        //...
        return bitmap;
    }
    
    public void displayImage2(ImageView imageView, String url){
        Bitmap bitmap = download(url);
        // 图片显示
        //...
        // 图片缓存
        imageCache(bitmap);
    }
    
    public void imageCache(Bitmap bitmap){
        // 图片缓存
        //...
    }
}
```
* SRP 代码
```java
/**
 * 下载类
 */
public class Download {
    /**
     * 下载
     *
     * @param url 下载地址
     * @return 图片
     */
    public Bitmap download(String url) {
        Bitmap bitmap = null;
        System.out.println("下载图片");
        return bitmap;
    }
}
/**
 * 缓存类
 */
public class Cache {
    /**
     * 缓存图片
     * @param bitmap 图片
     */
    public void imageCache(Bitmap bitmap){
        System.out.println("缓存图片");
    }
}
/**
 * 图片加载类
 */
public class ImageLoader {
    /**
     * 3加载图片
     * @param imageView 图片控件
     * @param url 图片地址
     */
    public void displayImage3(ImageView imageView, String url){
        Download download = new Download();
        Bitmap bitmap = download.download(url);
        // 图片显示
        System.out.println("显示图片");
        //...
        // 图片缓存
        Cache cache = new Cache();
        cache.imageCache(bitmap);
    }
}
```

# 开闭原则（OCP：Open Close Principle）
* 定义
软件中的对象（类、模块、函数等）应该对与扩展是开放的，但是对于修改是封闭的。

它是最基础的设计原则，指导我们建立稳定、灵活的系统。
* 举例
其实开发中有很多OCP 例子，例如：Android 中List的Adapter（适配器模式，也遵循OCP）、Listener等。
* 不好的代码
```java
public class DrawShape {
    // TODO 不好的代码
    public void drawView(int type){
        if (1== type){
            System.out.println("圆形");
        }else if (2==type){
            System.out.println("矩形");
        }
    }
}
public class Draw {
    public static void main(String[] args) {
        DrawShape drawShape = new DrawShape();
        // TODO 不好的代码
        // 绘制圆形
        drawShape.drawView(1);
        // 绘制矩形
        drawShape.drawView(2);
    }
}
```
* OCP代码
```java
/**
 * 画图基础类
 */
public abstract class BaseShape {
    // 画图
    public abstract void drawView();
}

/**
 * 圆形
 */
public class Circle extends BaseShape {
    public void drawView() {
        System.out.println("圆形");
    }
}

/**
 * 矩形
 */
public class Rectangle extends BaseShape {
    public void drawView() {
        System.out.println("矩形");
    }
}

/**
 * 绘制
 */
public class DrawShape {
    public void drawView(BaseShape draw){
        // ... 其他处理
        draw.drawView();
    }
}

public class Draw {
    public static void main(String[] args) {
        DrawShape drawShape = new DrawShape();
        // 绘制圆形
        drawShape.drawView(new Circle());
        // 绘制矩形
        drawShape.drawView(new Rectangle());
    }
}
```
这样可能会是类爆炸，但是它能降低耦合度，而且效率更高。

# 里氏替换原则（LSP：Liskov Substitution Principle）
* 定义
派生类（子类）对象可以在程式中代替其基类（超类）对象。

也就说所有引用基类的地方必须能透明的使用其子类的对象。其父类可以替换成子类，而子类不能替换成父类。
[里氏替换原则主要阐述了有关继承的一些原则，也就是什么时候应该使用继承，什么时候不应该使用继承，以及其中蕴含的原理。里氏替换原是继承复用的基础，它反映了基类与子类之间的关系，是对开闭原则的补充，是对实现抽象化的具体步骤的规范.](http://c.biancheng.net/view/1324.html)

* 举例
```java
/**
 * 父类
 */
public class Parent {
    public void method1(){
        System.out.println("method1");
    }
}
/**
 * 子类1
 */
public class Children1 extends Parent {
    public void method2(){
        System.out.println("method2");
    }

    @Override
    public void method1() {
        super.method1();
        System.out.println("Children1 method1");
    }
}
/**
 * 子类二
 */
public class Children2 extends Parent {
    public void method3(){
        System.out.println("method3");
    }
    @Override
    public void method1() {
        super.method1();
        System.out.println("Children2 method1");
    }
}

public class Client {
    public void method(Parent child){
        child.method1();
    }
}
// 调用
public static void main(String[] args) {
    Client client = new Client();
    client.method(new Children1());
    client.method(new Children2());
}
```

# 依赖倒置原则（DIP：Dependence Inversion Principle）
* 定义
程序要依赖于抽象接口，不要依赖于具体实现。简单的说就是要求对抽象进行编程，不要对实现进行编程，这样就降低了客户与实现模块间的耦合。

DIP 在java 中的表现是：模块间的依赖通过抽象发生，实现类之间不发生直接的依赖关系。其依赖关系是通过接口或者抽象类产生的。
其实它更倾向于面向接口或者抽象变成。
* 关键点
1. 高层模块不应该依赖底层模块，两者都应该依赖其抽象
2. 抽象不应该依赖细节
3. 细节应该依赖抽象
* 举例
```java
public interface TestListener {
    void test();
}
public class TestListenerImpl implements TestListener {
    public void test() {
        System.out.println("TEST");
    }
}
public class Client {
    private TestListener testListener;
    public void setTestListener(TestListener testListener) {
        this.testListener = testListener;
    }

    public void method(){
        this.testListener.test();
    }
}
// 执行
    public static void main(String[] args) {
        Client client = new Client();
        client.setTestListener(new TestListenerImpl());
        client.method();
    }
```
# 接口隔离原则（ISP：Interface Segregation Principles）
* 定义
客户端不应该依赖它不需要的接口。一个类对另一个类的依赖应该建立在最小的接口上。

一个接口代表一个角色，不应当将不同的角色都交给一个接口。没有关系的接口合并在一起，形成一个臃肿的大接口，这是对角色和接口的污染。
接口隔离原则和单一职责原则很像，都是一个类或接口、方法或接口方法只做一件事。
* 举例
* 不好的例子
```java
public interface BadListener {
    void badFood();
    void badThing();
}
```
以上例子中，bad 东西，可能是食物，也可能是物品，实现这个接口后，就会出现有一个回调是用不到。造成接口浪费（污染）。
* ISP 例子
```java
public interface BadFoodListener {
    void food();
}
public interface BadThingListener {
    void thing();
}
```
以上接口，使用到那个接口，就实现那个接口就可，不会造成接口方法的浪费。
# 迪米特原则（LOD：Law of Demeter）
* 定义
又叫作最少知识原则（The Least Knowledge Principle）。一个对象应该对其他对象有最少的了解。

一个类对于其他类知道的越少越好，就是说一个对象应当对其他对象有尽可能少的了解,只和朋友通信，不和陌生人说话。
* 意义
迪米特法则的初衷在于降低类之间的耦合度。由于每个类尽量减少对其他类的依赖，因此，很容易使得系统的功能模块功能独立，相互之间不存在（或很少有）依赖关系。
* 举例
好比租客想通过中介找房子，租客想要一个20平、2000一个月的。这时中介有两种方案提供给租客：
第一种：把所有房源都给租客，租客自己选
第二种：先按照要求把房源筛选出来，给租客。
第一种会增加租客和房源之间的耦合；而第二种就利用了LOD 原则，会降低租客和房源的耦合度，租客只看到符合条件的房源就可以了。
通俗点讲，就是把筛选的过程放到了租客能联系到的中介身上，中介处理房源，而租客和房源的联系微乎其微。
```java
/**
 * 房源
 */
public class Room {
    private int are;
    private int pay;

    public int getAre() {
        return are;
    }

    public void setAre(int are) {
        this.are = are;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
/**
 * 中介
 */
public class Mediator {
    private List<Room> allRooms = new ArrayList<Room>();
    public Mediator(){
        for (int i=0;i<10;i++){
            Room room =new Room();
            room.setAre((i+1)*5);
            room.setPay((i%4==0?1:i%4)* 1000);
            allRooms.add(room);
        }
    }

    public List<Room> getAllRooms() {
        return allRooms;
    }
    // 改造后
    public Room rentRoom(int are, int pay){
        // 通过中介获取所有房屋
        for (Room room: allRooms){
            if (isSuitable(room, are, pay)){
                return room;
            }
        }
        return null;
    }

    // 改造后 判断是否有合适的
    private boolean isSuitable(Room room, int are, int pay){
        // 筛选房屋
        return true;
    }
}
/**
 * 租客
 */
public class Tenant {

    // 不好的例子
//    // 改造前
//    public void rentRoom(Mediator mediator){
//        // 通过中介获取所有房屋
//        List<Room> allRoom = mediator.getAllRooms();
//        for (Room room: allRoom){
//            if (isSuitable(room)){
//                System.out.println("房屋找到了");
//            }
//        }
//    }
//
//    // 改造前-判断是否有合适的
//    private boolean isSuitable(Room room){
//        // 筛选房屋
//        return true;
//    }

    // 改造后-好例子
    public void rentRoom(Mediator mediator){
        System.out.println(mediator.rentRoom(20,2000));
    }
}
```
# 总结
经过设计原则的学习，总结出一句话就是一个类或者一个方法，只做一件事。