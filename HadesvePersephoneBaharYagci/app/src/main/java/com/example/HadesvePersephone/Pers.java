package com.example.HadesvePersephone;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.HadesvePersephone.GameView.screenRatioX;
import static com.example.HadesvePersephone.GameView.screenRatioY;

public class Pers {
    public boolean isGoingUp = false;
    int toShoot=0;
    int x, y, widht,height, fireCounter=0, shootCounter=1;
    Bitmap pers1, pers2, shoot1, shoot2, shoot3, shoot4, shoot5, dead;
    private GameView gameView;

    Pers(GameView gameView, int screenY, Resources res){
        this.gameView = gameView;
        pers1= BitmapFactory.decodeResource(res, R.drawable.persephone1);
        pers2= BitmapFactory.decodeResource(res, R.drawable.persephone2);

        widht=pers1.getWidth();
        height=pers1.getHeight();

        widht /= 2;
        height /= 2;

        widht*= (int) screenRatioX;
        height*= (int) screenRatioY;

        pers1=Bitmap.createScaledBitmap(pers1, widht, height, false);
        pers2=Bitmap.createScaledBitmap(pers2, widht, height, false);

        shoot1=BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot2=BitmapFactory.decodeResource(res, R.drawable.shoot2);
        shoot3=BitmapFactory.decodeResource(res, R.drawable.shoot3);
        shoot4=BitmapFactory.decodeResource(res, R.drawable.shoot4);
        shoot5=BitmapFactory.decodeResource(res, R.drawable.shoot5);

        shoot1=Bitmap.createScaledBitmap(shoot1,widht,height,false);
        shoot2=Bitmap.createScaledBitmap(shoot2,widht,height,false);
        shoot3=Bitmap.createScaledBitmap(shoot3,widht,height,false);
        shoot4=Bitmap.createScaledBitmap(shoot4,widht,height,false);
        shoot5=Bitmap.createScaledBitmap(shoot5,widht,height,false);

        dead=BitmapFactory.decodeResource(res, R.drawable.dead);
        dead=Bitmap.createScaledBitmap(dead,widht,height,false);

        y=screenY/2;
        x=(int) (64*screenRatioX);
    }
    Bitmap getPers() {
        if(toShoot != 0){
            if (shootCounter==1){
                shootCounter++;
                return  shoot1; }
            if (shootCounter==2){
                shootCounter++;
                return  shoot2; }
            if (shootCounter==3){
                shootCounter++;
                return  shoot3; }
            if (shootCounter==4){
                shootCounter++;
                return  shoot4; }
            shootCounter=1;
            toShoot--;
            gameView.newBullet();
            return  shoot5;
        }
        if(fireCounter==0){
            fireCounter++;
            return pers1;
        }
        fireCounter--;
        return pers2;
    }
    Rect getCollisionShape (){
        return new Rect(x, y, x+widht, y+height);
    }
    Bitmap getDead(){
        return  dead;
    }
}
