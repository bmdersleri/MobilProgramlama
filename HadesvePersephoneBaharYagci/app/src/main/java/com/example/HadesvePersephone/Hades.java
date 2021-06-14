package com.example.HadesvePersephone;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.HadesvePersephone.GameView.screenRatioX;
import static com.example.HadesvePersephone.GameView.screenRatioY;

public class Hades {
    public int speed=20;
    public boolean wasShot=true;
    int x=0, y, width, height, hadesCounter = 1;
    Bitmap hades1, hades2, hades3, hades4;
    Hades (Resources res){
        hades1 = BitmapFactory.decodeResource(res, R.drawable.hades1);
        hades2 = BitmapFactory.decodeResource(res, R.drawable.hades2);
        hades3 = BitmapFactory.decodeResource(res, R.drawable.hades3);
        hades4 = BitmapFactory.decodeResource(res, R.drawable.hades4);

        width = hades1.getWidth();
        height = hades1.getHeight();

        width /= 4;
        height /= 4;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        hades1 = Bitmap.createScaledBitmap(hades1,width,height,false);
        hades2 = Bitmap.createScaledBitmap(hades2,width,height,false);
        hades3 = Bitmap.createScaledBitmap(hades3,width,height,false);
        hades4 = Bitmap.createScaledBitmap(hades4,width,height,false);

        y = -height;
    }

    Bitmap getHades(){
        if(hadesCounter==1){
            hadesCounter++;
            return hades1;
        }
        if(hadesCounter==2){
            hadesCounter++;
            return hades2;
        }
        if(hadesCounter==3) {
            hadesCounter++;
            return hades3;
        }
        hadesCounter = 1;
        return hades4;
    }
    Rect getCollisionShape (){
        return new Rect(x, y, x + width, y+ height);
    }
}
