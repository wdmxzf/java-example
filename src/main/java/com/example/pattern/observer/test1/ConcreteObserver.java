package com.example.pattern.observer.test1;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者-具体观察者
 */
public class ConcreteObserver implements Observer {
    public ConcreteObserver(String thisName) {
        this.thisName = thisName;
    }

    private String thisName;

    public String getThisName() {
        return thisName;
    }

    public void setThisName(String thisName) {
        this.thisName = thisName;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.thisName+":::"+arg+"更新了");
    }
}
