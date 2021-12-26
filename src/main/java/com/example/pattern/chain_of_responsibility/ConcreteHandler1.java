package com.example.pattern.chain_of_responsibility;

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
