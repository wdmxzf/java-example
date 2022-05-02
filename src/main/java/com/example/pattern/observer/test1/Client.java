package com.example.pattern.observer.test1;

public class Client {
    public static void main(String[] args) {
        // 被观察者
        ConcreteSubject concreteSubject = new ConcreteSubject();
        // 观察者
        ConcreteObserver concreteObserver1 = new ConcreteObserver("observer-1");
        ConcreteObserver concreteObserver2 = new ConcreteObserver("observer-2");
        ConcreteObserver concreteObserver3 = new ConcreteObserver("observer-3");
        ConcreteObserver concreteObserver4 = new ConcreteObserver("observer-4");
        concreteSubject.addObserver(concreteObserver1);
        concreteSubject.addObserver(concreteObserver2);
        concreteSubject.addObserver(concreteObserver3);
        concreteSubject.updateName("张三");
    }
}
