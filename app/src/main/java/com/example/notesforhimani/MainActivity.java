package com.example.notesforhimani;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button life,thoughts,poem,others;
    Animation animation;
    ImageButton imageButton,imageButton2;
    Animation slideleftin;
    Animation slideleftout;
    Animation sliderightin;
    Animation sliderightout;
    Animation slideup,slidedown,comesin,goesout;
    Handler handler;
    int interval = 4000;
    int[] images = {R.drawable.h0, R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in_anim);
        animation.setDuration(1000);
        slideleftin = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_left_in_anim);
        slideleftout = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_left_out_anim);
        sliderightin = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_right_in_anim);
        sliderightout = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_right_out_anim);
        slidedown = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_down_anim);
        slideup = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_down_out);
        comesin = AnimationUtils.loadAnimation(MainActivity.this,R.anim.comesin);
        goesout = AnimationUtils.loadAnimation(MainActivity.this,R.anim.goesout_anim);
        goesout.setDuration(700);
        comesin.setDuration(700);
        slideleftin.setDuration(700);
        slideleftout.setDuration(700);
        sliderightout.setDuration(700);
        sliderightin.setDuration(700);
        slideup.setDuration(700);
        slidedown.setDuration(700);
        imageView=findViewById(R.id.imageView);
        life=findViewById(R.id.button);
        thoughts=findViewById(R.id.button2);
        poem=findViewById(R.id.button3);
        others=findViewById(R.id.button4);
        imageButton=findViewById(R.id.imageButton);
        imageButton2=findViewById(R.id.imageButton5);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        handler = new Handler();
        startRepeatingTask();
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Random random = new Random();
            int r = random.nextInt(6);
            imageView.startAnimation(animation);
            imageView.setImageResource(images[r]);
            handler.postDelayed(runnable,interval);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent svc = new Intent(this,BackgroundSound.class);
        startService(svc);
        life.startAnimation(slideleftin);
        thoughts.startAnimation(sliderightin);
        poem.startAnimation(slideleftin);
        others.startAnimation(sliderightin);
        imageButton.startAnimation(slidedown);
        imageButton2.startAnimation(comesin);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopRepeatingTask();
    }

    private void stopRepeatingTask(){
        handler.removeCallbacks(runnable);
    }

    private void startRepeatingTask(){
        runnable.run();
    }
    public void life(View view){
  //      Toast.makeText(this, "You clicked Life", Toast.LENGTH_SHORT).show();
        life.startAnimation(slideleftout);
        thoughts.startAnimation(sliderightout);
        poem.startAnimation(slideleftout);
        others.startAnimation(sliderightout);
        imageButton.startAnimation(slideup);
        imageButton2.startAnimation(slideup);
        imageButton2.startAnimation(goesout);
        Intent intent=new Intent(MainActivity.this,readhere.class);
        intent.putExtra("type","Life");
        intent.putExtra("auth","no");
        startActivity(intent);
    }
    public void thoughts(View view){
//        Toast.makeText(this, "You clicked Thoughts", Toast.LENGTH_SHORT).show();
        life.startAnimation(slideleftout);
        thoughts.startAnimation(sliderightout);
        poem.startAnimation(slideleftout);
        others.startAnimation(sliderightout);
        imageButton.startAnimation(slideup);
        imageButton2.startAnimation(goesout);
        Intent intent=new Intent(MainActivity.this,readhere.class);
        intent.putExtra("type","Thoughts");
        intent.putExtra("auth","no");
        startActivity(intent);
    }
    public void poems(View view){
  //      Toast.makeText(this, "You clicked poems", Toast.LENGTH_SHORT).show();
        life.startAnimation(slideleftout);
        thoughts.startAnimation(sliderightout);
        poem.startAnimation(slideleftout);
        imageButton2.startAnimation(goesout);
        others.startAnimation(sliderightout);
        imageButton.startAnimation(slideup);
        Intent intent=new Intent(MainActivity.this,readhere.class);
        intent.putExtra("type","Poems");
        intent.putExtra("auth","no");
        startActivity(intent);
    }
    public void others(View view){
//        Toast.makeText(this, "You clicked others", Toast.LENGTH_SHORT).show();
        life.startAnimation(slideleftout);
        imageButton2.startAnimation(goesout);
        thoughts.startAnimation(sliderightout);
        poem.startAnimation(slideleftout);
        imageButton.startAnimation(slideup);
        others.startAnimation(sliderightout);
        Intent intent=new Intent(MainActivity.this,readhere.class);
        intent.putExtra("type","Others");
        intent.putExtra("auth","no");
        startActivity(intent);
    }
    public void changeSettings(View view){
        life.startAnimation(slideleftout);
        imageButton2.startAnimation(goesout);
        thoughts.startAnimation(sliderightout);
        poem.startAnimation(slideleftout);
        imageButton.startAnimation(slideup);
        others.startAnimation(sliderightout);
        Intent intent=new Intent(MainActivity.this,Setting.class);
        startActivity(intent);
    }
    public void playxylo(View view){
        life.startAnimation(slideleftout);
        thoughts.startAnimation(sliderightout);
        poem.startAnimation(slideleftout);
        imageButton.startAnimation(slideup);
        others.startAnimation(sliderightout);
        imageButton2.startAnimation(goesout);
        Intent intent = new Intent(MainActivity.this,xylophone.class);
        startActivity(intent);
    }
}