package com.example.pattern.singleton;

import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式
 */
public class Singleton implements Serializable {
    private static Singleton instance;
    // 懒汉式—线程不安全
    public static Singleton getInstance1(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    // 懒汉式—线程安全
    public static synchronized Singleton getInstance11(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    // 饿汉式
    private static Singleton instance2 = new Singleton();
    public static Singleton getInstance2(){
        return instance2;
    }
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

    // 登记式-静态内部类单例模式
    public static Singleton getInstance4(){
        return SingletonHolder.instance;
    }
    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }

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

    private Object readResolve(){
        return instance;
    }
// 枚举单例
    public static void main(String[] args) {
        SingletonEnum.INSTANCE.method();
    }
}
