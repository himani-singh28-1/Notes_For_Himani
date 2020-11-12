package com.example.notesforhimani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.notesforhimani.adaptor.RecyclerViewAdaptor;
import com.example.notesforhimani.data.MyDBHandler;
import com.example.notesforhimani.model.notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class readhere extends AppCompatActivity {
    TextView textView;
    RecyclerView recyclerView;
    RecyclerViewAdaptor recyclerViewAdaptor;
    ArrayList<notes> notesArrayList;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_readhere);
        type = getIntent().getExtras().getString("type");
        String auth = getIntent().getExtras().getString("auth");
        textView=findViewById(R.id.textView4);
        textView.setText(type);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyDBHandler db = new MyDBHandler(readhere.this);
        List<notes> notesList = db.getallNotes();
        notesArrayList = new ArrayList<>();
        for(notes note : notesList)
        {
            if(note.getType().equals(type)) {
                notesArrayList.add(note);
            }
        }
        recyclerViewAdaptor = new RecyclerViewAdaptor(readhere.this,notesArrayList,type);
        recyclerView.setAdapter(recyclerViewAdaptor);
    }

    public void addNte(View view){
        Intent intent = new Intent(readhere.this,authenticationPassword.class);
        intent.putExtra("send","add");
        intent.putExtra("type",type);
        startActivity(intent);
    }
}
