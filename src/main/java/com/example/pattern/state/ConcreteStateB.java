package com.example.pattern.state;

/**
 * 当 OFF 的时候执行
 */
public class ConcreteStateB implements State {
    public void method1() {
        System.out.println("是 OFF 状态，可以执行method1");
    }

    public void method2() {
        System.out.println("是 OFF 状态，可以执行method2");
    }
}
