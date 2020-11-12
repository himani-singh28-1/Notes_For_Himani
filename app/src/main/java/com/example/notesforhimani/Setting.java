package com.example.notesforhimani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Setting extends AppCompatActivity {
    EditText editText,editText1,editText2;
    String pass,newpass,confirmpass;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Window window = getWindow();
        int[] images = {R.drawable.h0, R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5};
        Random random = new Random();
        int r=random.nextInt(6);
        imageView = findViewById(R.id.imageView5);
        editText=findViewById(R.id.editTextTextPassword2);
        editText1=findViewById(R.id.editTextTextPassword3);
        editText2=findViewById(R.id.editTextTextPassword4);
        imageView.setImageResource(images[r]);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences getShared = getSharedPreferences("pass", MODE_PRIVATE);
        pass = getShared.getString("password", "HiSi");
    }
    public void saveSettings(View view){
        newpass=editText1.getText().toString();
        confirmpass=editText2.getText().toString();
        if(editText.getText().toString().equals(pass)){
            if(newpass.equals(confirmpass)){
                String temp = newpass.toLowerCase();
                if(temp.contains("himani")){
                    editText1.setError("You can not have your name inside your password. It is highly insecure");
                }
                else if(newpass.isEmpty()){
                    editText1.setError("Enter a valid password");
                }
                else{
                    SharedPreferences shrd = getSharedPreferences("pass", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putString("password", newpass);
                    editor.apply();
                    Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                editText2.setError("You have to enter same password as you have entered in new password");
            }
        }
        else{
            editText.setError("Wrong Password");
        }
    }
}