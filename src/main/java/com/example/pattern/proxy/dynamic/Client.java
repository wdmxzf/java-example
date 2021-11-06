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
        // 方法一
//        DynamicProxy dynamicProxy= new DynamicProxy(xiaoGang);
//        //获取被代理对象小刚的 ClassLoader
//        ClassLoader xiaoGangClassLoader = xiaoGang.getClass().getClassLoader();
//        // 动态构造一个代理律师
//        LitigationProcessInterface lawyer = (LitigationProcessInterface) Proxy.newProxyInstance(xiaoGangClassLoader, new Class[]{LitigationProcessInterface.class}, dynamicProxy);
//        lawyer.submitLawsuit();
//        lawyer.proof();
//        lawyer.defend();
//        lawyer.finish();

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
