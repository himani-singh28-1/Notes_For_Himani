package com.example.notesforhimani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.example.notesforhimani.data.MyDBHandler;
import com.example.notesforhimani.model.notes;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DisplayActivity extends AppCompatActivity {
    String type,desc,time,original,encoded,decoded;
    EditText editText;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        desc = intent.getStringExtra("desc");
        id = intent.getIntExtra("id",-1);
        byte[] tmp = Base64.decode(desc,Base64.DEFAULT);
        decoded = new String(tmp, StandardCharsets.UTF_8);
        editText = findViewById(R.id.editTextTextPersonName2);
        editText.setText(decoded);
    }
    public void updatenote(View view){
        MyDBHandler db = new MyDBHandler(DisplayActivity.this);
        notes note=new notes();
        original = editText.getText().toString();
        encoded = Base64.encodeToString(original.getBytes(), Base64.DEFAULT);
        note.setDescription(encoded);
        note.setId(id);
        note.setType(type);
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(calendar.getTime());
        note.setTime(curdate);
        int i = db.updateNote(note);
        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void deletenote(View view){
        MyDBHandler db=new MyDBHandler(DisplayActivity.this);
        db.deleteNote(id);
        finish();
    }
}