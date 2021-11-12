package com.example.principle.lsp;

/**
 * 子类1
 */
public class Children1 extends Parent {
    public void method2(){
        System.out.println("method2");
    }

    @Override
    public void method1() {
        super.method1();
        System.out.println("Children1 method1");
    }
}
