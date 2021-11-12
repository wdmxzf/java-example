package com.example.principle.ocp;

public class Draw {
    public static void main(String[] args) {
        DrawShape drawShape = new DrawShape();
        // 绘制圆形
        drawShape.drawView(new Circle());
        // 绘制矩形
        drawShape.drawView(new Rectangle());

        // TODO 不好的代码
        // 绘制圆形
        drawShape.drawView(1);
        // 绘制矩形
        drawShape.drawView(2);
    }
}
