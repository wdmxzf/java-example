package com.example.pattern.observer.test1;

import java.util.Observable;

/**
 * 被观察者-具体主题
 */
public class ConcreteSubject extends Observable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateName(String name){
        this.name = name;
        // 告知数据改变
        super.setChanged();
        // 发送信号通知观察者
        super.notifyObservers(name);
    }
}
