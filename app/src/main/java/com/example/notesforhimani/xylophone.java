package com.example.notesforhimani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
//import android.widget.Button;

public class xylophone extends AppCompatActivity {
//    Button button,button2,button3,button4,button5,button6,button7;
    SoundPool soundPool;
    int maxstream = 7;
    int a,b,c,d,e,f,g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(this,BackgroundSound.class);
        stopService(intent);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xylophone);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        button = findViewById(R.id.button16);
//        button2 = findViewById(R.id.button12);
//        button3 = findViewById(R.id.button15);
//        button4 = findViewById(R.id.button11);
//        button5 = findViewById(R.id.button10);
//        button6 = findViewById(R.id.button13);
//        button7 = findViewById(R.id.button14);

        soundPool = new SoundPool.Builder().setMaxStreams(maxstream).build();

        a=soundPool.load(xylophone.this,R.raw.note1_c,1);
        b=soundPool.load(xylophone.this,R.raw.note2_d,1);
        c=soundPool.load(xylophone.this,R.raw.note3_e,1);
        d=soundPool.load(xylophone.this,R.raw.note4_f,1);
        e=soundPool.load(xylophone.this,R.raw.note5_g,1);
        f=soundPool.load(xylophone.this,R.raw.note6_a,1);
        g=soundPool.load(xylophone.this,R.raw.note7_b,1);
    }

    public void playit(View view){
        switch (view.getId()){
            case R.id.button16: {
                soundPool.play(a,1,1,1,0,1);
                break;}
            case R.id.button12: {
                soundPool.play(b,1,1,1,0,1);
                break;
            }
            case R.id.button15: {
                soundPool.play(c,1,1,1,0,1);
                break;
            }
            case R.id.button11: {
                soundPool.play(d,1,1,1,0,1);
                break;
            }
            case R.id.button10: {
                soundPool.play(e,1,1,1,0,1);
                break;
            }
            case R.id.button13: {
                soundPool.play(f,1,1,1,0,1);
                break;
            }
            case R.id.button14: {
                soundPool.play(g,1,1,1,0,1);
                break;
            }
        }
    }
}