package com.duatson.studentapp.application;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

public class DrawableUtil {

//    public static Drawable LoadImageFromURL(String url) {
//        try {
//            InputStream is = (InputStream) new URL(url).getContent();
//            return Drawable.createFromStream(is, "Image From URL");
//        } catch (Exception e) {
//            return null;
//        }
//    }

    public static Bitmap LoadImageFromURL(String url) {
        try {
            return BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
        } catch (Exception e) {
            return null;
        }
    }
}
