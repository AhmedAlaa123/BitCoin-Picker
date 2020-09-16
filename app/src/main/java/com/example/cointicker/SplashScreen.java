package com.example.cointicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cointicker.ui.MainActivity;

public class SplashScreen extends AppCompatActivity {
private ImageView imageView_logo;
private TextView textView_emojy,textView_coin,textView_picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView_logo=findViewById(R.id.imageView_logo);
        textView_coin=findViewById(R.id.textView_coin);
        textView_emojy=findViewById(R.id.textView_emojy);
        textView_picker=findViewById(R.id.textView_picker);
        startAnimation();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }).start();

    }
    private void startAnimation()
    {
        Animation logoِAnimation = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        imageView_logo.startAnimation(logoِAnimation);
        Animation emojyAnimation = AnimationUtils.loadAnimation(this,R.anim.emojy_animation);
        textView_emojy.startAnimation(emojyAnimation);
        Animation coinAnimation = AnimationUtils.loadAnimation(this,R.anim.coin_animation);
        textView_coin.startAnimation(coinAnimation);
        Animation pickerAnimation = AnimationUtils.loadAnimation(this,R.anim.picker_animation);
        textView_picker.startAnimation(pickerAnimation);
    }
}