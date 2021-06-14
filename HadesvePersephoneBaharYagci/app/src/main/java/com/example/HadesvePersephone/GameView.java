package com.example.HadesvePersephone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying, isGameOver=false;
    private int screenX , screenY, score=0;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private Hades[] hadess;
    private SharedPreferences prefs;
    private Random random;
    private SoundPool soundPool;
    private List<Bullet> bullets;
    private Pers pers;
    private int sound;
    private GameActivity activity;
    private Background background1, background2;
    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity=activity;

        prefs=activity.getSharedPreferences("game", Context.MODE_PRIVATE);

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME).build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes).build();
        }else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        sound = soundPool.load(activity, R.raw.ates, 1);

        this.screenX=screenX;
        this.screenY=screenY;
        screenRatioX=1920f /screenX;
        screenRatioY=1080f /screenY;
        background1= new Background(screenX, screenY, getResources());
        background2= new Background(screenX, screenY, getResources());
        pers = new Pers(this,screenY, getResources());
        bullets = new ArrayList<>();
        background2.x=screenX;
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);
        hadess = new Hades[4];
        for (int i = 0; i<4; i++) {
            Hades hades = new Hades(getResources());
            hadess[i] = hades;
        }
        random = new Random();
    }

    @Override
    public void run() {
        while (isPlaying){
            update ();
            draw();
            sleep();
        }

    }
    private void  update() {
        background1.x -= 10*screenRatioX;
        background2.x -= 10*screenRatioX;
        if (background1.x + background1.background.getWidth() < 0){
            background1.x=screenX;
        }
        if (background2.x + background2.background.getWidth() < 0){
            background2.x=screenX;
        }
        if (pers.isGoingUp)
            pers.y -= 30*screenRatioY;
        else
            pers.y += 30*screenRatioY;
        if (pers.y<0)
            pers.y=0;
        if (pers.y>screenY-pers.height)
            pers.y=screenY-pers.height;

        List<Bullet> trash = new ArrayList<>();

        for (Bullet bullet: bullets){
            if (bullet.x > screenX)
                trash.add(bullet);

            bullet.x += 50 * screenRatioX;
            for (Hades hades : hadess){
                if (Rect.intersects(hades.getCollisionShape(), bullet.getCollisionShape())){
                    score++;
                    hades.x = -500;
                    bullet.x =screenX +500;
                    hades.wasShot=true;
                }
            }
        }

        for(Bullet bullet : trash)
            bullets.remove(bullet);

        for (Hades hades:hadess){
            hades.x -= hades.speed;
            if (hades.x + hades.width < 0){
                if (!hades.wasShot){
                    isGameOver=true;
                    return;
                }
                int bound = (int) (30 *screenRatioX);
                hades.speed = random.nextInt(bound);

                if (hades.speed < 10*screenRatioX)
                    hades.speed = (int) (10*screenRatioX);
                hades.x = screenX;
                hades.y = random.nextInt(screenY - hades.height);
                hades.wasShot=false;
            }
            if (Rect.intersects(hades.getCollisionShape(), pers.getCollisionShape())){
                isGameOver = true;
                return;
            }
        }
    }
    private  void draw() {
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            for (Hades hades:hadess)
                canvas.drawBitmap(hades.getHades(), hades.x, hades.y, paint);
            canvas.drawText(score + "",screenX / 2f, 164, paint);

            if (isGameOver){
                isPlaying = false;
                canvas.drawBitmap(pers.getDead(), pers.x, pers.y, paint);
                getHolder().unlockCanvasAndPost(canvas);
                saveIfHighScore();
                waitBeforeExiting();
                return;
            }

            canvas.drawBitmap(pers.getPers(), pers.x, pers.y, paint);
            for (Bullet bullet : bullets)
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void waitBeforeExiting() {
        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveIfHighScore() {
        if (prefs.getInt("highscore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore",score);
            editor.apply();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume() {
        isPlaying=true;
        thread = new Thread(this);
        thread.start();
    }
    public void pause () {
        try {
            isPlaying=false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX()<screenX/2){
                    pers.isGoingUp=true;
                }
                break;
            case MotionEvent.ACTION_UP:
                pers.isGoingUp= false;
                if (event.getX() > screenX / 2)
                    pers.toShoot++;
                break;
        }
        return true;
    }

    public void newBullet() {

        if (!prefs.getBoolean("İsMute", false))
            soundPool.play(sound, 1, 1, 0, 0,1);
        Bullet bullet = new Bullet(getResources());
        bullet.x = pers.x + pers.widht;
        bullet.y = pers.y + pers.height / 2;
        bullets.add(bullet);
    }
}
