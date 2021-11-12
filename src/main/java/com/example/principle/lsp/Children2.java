package com.example.principle.lsp;

/**
 * 子类二
 */
public class Children2 extends Parent {
    public void method3(){
        System.out.println("method3");
    }
    @Override
    public void method1() {
        super.method1();
        System.out.println("Children2 method1");
    }

}
