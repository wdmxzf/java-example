package com.example.pattern.prototype;

/**
 * 原型类
 */
public class Prototype2 implements Cloneable {

    private String message;
    private String title;
    private Children children;
    // ...

    @Override
    protected Prototype2 clone() throws CloneNotSupportedException {
        // 浅拷贝
        Prototype2 prototype2 = (Prototype2)super.clone();
        prototype2.children = this.children.clone();
        return prototype2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }
}
