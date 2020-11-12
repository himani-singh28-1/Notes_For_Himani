package com.example.notesforhimani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class authenticationPassword extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    ImageView imageView;
    String pass;
    String send, type, desc;
    int count = 0,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_password);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        final Intent intent = getIntent();
        send = intent.getStringExtra("send");
        type = intent.getStringExtra("type");
        desc = intent.getStringExtra("desc");
        id = intent.getIntExtra("id",0);
        editText = findViewById(R.id.editTextTextPassword);
        textView = findViewById(R.id.textView7);
        button = findViewById(R.id.button6);
        imageView = findViewById(R.id.imageView4);
        textView.setText("Enter Your Password to continue 3 attempts are allowed");
        if (send.equals("locked"))
            textView.setText("Someone tried to access your content Himani\nEnter your password to continue");
        SharedPreferences getShared = getSharedPreferences("pass", MODE_PRIVATE);
        pass = getShared.getString("password", null);
        if (pass == null) pass = "HiSi";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(pass)) {
                    imageView.setImageResource(R.drawable.ic_baseline_sentiment_very_satisfied_24);
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            Intent intent1;
                            if (send.equals("add")) {
                                intent1 = new Intent(authenticationPassword.this, AddNotes.class);
                                intent1.putExtra("type",type);
                                startActivity(intent1);
                                finish();
                            } else if (send.equals("locked")) {
                                SharedPreferences shrd = getSharedPreferences("flag", MODE_PRIVATE);
                                SharedPreferences.Editor editor = shrd.edit();
                                editor.putString("locked", "false");
                                editor.apply();
                                intent1 = new Intent(authenticationPassword.this, MainActivity.class);
                                startActivity(intent1);
                                finish();
                            } else if (send.equals("edit")) {
                                intent1 = new Intent(authenticationPassword.this, DisplayActivity.class);
                                intent1.putExtra("type",type);
                                intent1.putExtra("id",id);
                                intent1.putExtra("desc",desc);
                                startActivity(intent1);
                                finish();
                            } else {
                                Toast.makeText(authenticationPassword.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    Handler h = new Handler();
                    h.postDelayed(r, 900);
                } else {
                    count++;
                    if (count == 1) {
                        textView.setText("Oops this was wrong 2 attempts left");
                        imageView.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24);
                        editText.setError("Wrong Password");
                    } else if (count == 2) {
                        textView.setText("Oops this was wrong 1 attempts left");
                        imageView.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
                        editText.setError("Wrong Password Again\nLast Chance");
                    } else {
                        textView.setText("WRONG PASSWORD");
                        SharedPreferences shrd = getSharedPreferences("flag", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shrd.edit();
                        editor.putString("locked", "true");
                        editor.apply();
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory(Intent.CATEGORY_HOME);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);
                        finishAffinity();
                    }
                }
            }
        });
    }
}