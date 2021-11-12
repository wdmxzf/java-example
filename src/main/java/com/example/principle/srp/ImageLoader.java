package com.example.principle.srp;

import sun.jvm.hotspot.utilities.BitMap;

import javax.swing.text.html.ImageView;

/**
 * 图片加载类
 */
public class ImageLoader {
    /**
     * 1加载图片
     */
    public void displayImage1(ImageView imageView, String url){
        // 下载
        System.out.println("下载图片");
        // 图片显示
        System.out.println("显示图片");
        // 图片缓存
        System.out.println("缓存图片");
    }

    /**
     * 2下载
     */
    public Bitmap download(String url){
        Bitmap bitmap = null;
        // 下载图片
        System.out.println("下载图片");
        //...
        return bitmap;
    }

    /**
     * 2 加载图片
     */
    public void displayImage2(ImageView imageView, String url){
        Bitmap bitmap = download(url);
        // 图片显示
        System.out.println("显示图片");
        //...
        // 图片缓存
        imageCache(bitmap);
    }

    /**
     * 2缓存
     */
    public void imageCache(Bitmap bitmap){
        // 图片缓存
        System.out.println("缓存图片");
        //...
    }

    /**
     * 3加载图片
     * @param imageView 图片控件
     * @param url 图片地址
     */
    public void displayImage3(ImageView imageView, String url){
        Download download = new Download();
        Bitmap bitmap = download.download(url);
        // 图片显示
        System.out.println("显示图片");
        //...
        // 图片缓存
        Cache cache = new Cache();
        cache.imageCache(bitmap);
    }
}
