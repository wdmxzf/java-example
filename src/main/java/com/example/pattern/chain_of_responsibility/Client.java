package com.example.pattern.chain_of_responsibility;

public class Client {
    public static void main(String[] args) {
        ConcreteHandler1 handler1 = new ConcreteHandler1();
        ConcreteHandler2 handler2 = new ConcreteHandler2();
        ConcreteHandler3 handler3 = new ConcreteHandler3();
        handler1.nextHandler = handler2;
        handler2.nextHandler = handler3;
        handler3.nextHandler = handler1;
        handler1.handlerRequest(Handler.TYPE_3);
    }
}
