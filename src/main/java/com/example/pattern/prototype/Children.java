package com.example.pattern.prototype;

/**
 * 元素类
 */
public class Children implements Cloneable {
    private String content;

    @Override
    protected Children clone() throws CloneNotSupportedException {
        return (Children)super.clone();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
