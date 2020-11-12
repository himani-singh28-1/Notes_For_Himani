package com.example.notesforhimani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesforhimani.data.MyDBHandler;
import com.example.notesforhimani.model.notes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNotes extends AppCompatActivity {
    EditText editText;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        type = getIntent().getStringExtra("type");
        editText=findViewById(R.id.editTextTextPersonName);

    }
    public void addThis(View view){
        MyDBHandler db = new MyDBHandler(AddNotes.this);
        notes note = new notes();
        String original = editText.getText().toString();

        String encoded = Base64.encodeToString(original.getBytes(), Base64.DEFAULT);
//        String encoded = Base64.encode(original.getBytes(),Base64.DEFAULT).toString();
        note.setDescription(encoded);
        note.setType(type);
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(calendar.getTime());
        note.setTime(curdate);
        db.addNote(note);
        finish();
    }
}