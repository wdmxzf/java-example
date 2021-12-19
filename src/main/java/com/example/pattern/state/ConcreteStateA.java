package com.example.pattern.state;

/**
 * 当ON 的时候执行
 */
public class ConcreteStateA implements State {
    public void method1() {
        System.out.println("是ON 状态，可以执行method1");
    }

    public void method2() {
        System.out.println("是ON 状态，可以执行method2");
    }
}
