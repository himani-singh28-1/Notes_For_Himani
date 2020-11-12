package com.example.notesforhimani;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Random;

public class SplashS extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    String flg;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_s);
        int[] images = {R.drawable.h0, R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5};
        Random random = new Random();
        int r=random.nextInt(6);
        imageView = findViewById(R.id.imageView2);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView3);
        textView3 = findViewById(R.id.textView9);
        imageView.setImageResource(images[r]);
        Animation slideup = AnimationUtils.loadAnimation(SplashS.this,R.anim.slide_up_anim);
        Animation slidedown = AnimationUtils.loadAnimation(SplashS.this, R.anim.slide_down_anim);
        slidedown.setDuration(850);
        slideup.setDuration(850);
        SharedPreferences getShared = getSharedPreferences("flag",MODE_PRIVATE);
        flg = getShared.getString("locked","kaustubh");
        Calendar calendar=Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        if(day == 28 && month == 11)
        {
            textView3.setText(R.string.hbd);
        }
        else if(day >28 && month >= 11){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(year+1,10,28,0,0,0);
            long diff = calendar1.getTimeInMillis() - calendar.getTimeInMillis();
            long daysc = diff/(24*60*60*1000);
            diff-=daysc * (24*60*60*1000);
            long hoursc = diff/(60*60*1000);
            diff-=hoursc * (60*60*1000);
            long minutesc = diff/(60*1000);
            diff-=minutesc * (60*1000);
            long secondc = diff/1000;
            String dte = daysc +":"+ hoursc +":"+ minutesc +":"+ secondc;
            textView3.setText(dte);
        }
        else{
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(year,10,28,0,0,0);
            long diff = calendar1.getTimeInMillis() - calendar.getTimeInMillis();
            long daysc = diff/(24*60*60*1000);
            diff-=daysc * (24*60*60*1000);
            long hoursc = diff/(60*60*1000);
            diff-=hoursc * (60*60*1000);
            long minutesc = diff/(60*1000);
            diff-=minutesc * (60*1000);
            long secondc = diff/1000;
            String dte = String.valueOf(daysc) +":"+ hoursc +":"+ minutesc +":"+ secondc;
            textView3.setText(dte);
        }
        if(hours < 12)
        {
            textView.setText("Good Morning");
        }
        else if(hours < 13){
            textView.setText("Guten Tag");
        }
        else if(hours < 17){
            textView.setText("Good Afternoon");
        }
        else if(hours < 22){
            textView.setText("Good Evening");
        }
        else{
            textView.setText("Good Night");
        }
        textView.startAnimation(slideup);
        textView1.startAnimation(slidedown);
        textView2.startAnimation(slideup);
        textView3.startAnimation(slidedown);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(flg.equals("true"))
                {
                    Intent intent = new Intent(SplashS.this, authenticationPassword.class);
                    intent.putExtra("send","locked");
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(SplashS.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },4000);
    }
}