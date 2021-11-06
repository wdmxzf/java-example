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
