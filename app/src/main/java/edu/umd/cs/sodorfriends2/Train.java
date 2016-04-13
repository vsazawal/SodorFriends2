package edu.umd.cs.sodorfriends2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Vibha on 4/7/2016.
 */
public class Train {
    private String name;
    private String num;
    private byte[] image;
    public Train(String s, String n, byte[] image) {
        num = n;
        name = s;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public String getNum() {
        return num;
    }

    public Bitmap getBitmap() {
        Bitmap b = BitmapFactory.decodeByteArray(image, 0, image.length);
        return b;
    }

    public String toString() {
        return name;
    }
}
