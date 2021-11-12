package com.example.principle.ocp;

/**
 * 绘制
 */
public class DrawShape {
    public void drawView(BaseShape draw){
        // ... 其他处理
        draw.drawView();
    }

    // TODO 不好的代码
    public void drawView(int type){
        if (1== type){
            System.out.println("圆形");
        }else if (2==type){
            System.out.println("矩形");
        }
    }
}
