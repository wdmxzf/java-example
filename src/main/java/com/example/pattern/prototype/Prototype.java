package com.example.pattern.prototype;

/**
 * 原型类
 */
public class Prototype implements Cloneable {

    private String message;
    private String title;
    private Children children;
    // ...

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        // 浅拷贝
        return (Prototype)super.clone();
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
