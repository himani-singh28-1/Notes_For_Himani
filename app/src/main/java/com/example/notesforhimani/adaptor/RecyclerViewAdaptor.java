package com.example.notesforhimani.adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesforhimani.R;
import com.example.notesforhimani.authenticationPassword;
import com.example.notesforhimani.model.notes;

import java.util.List;
import java.util.Random;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {
    private Context context;
    private List<notes> notesList;
    private String type;

    public RecyclerViewAdaptor(Context context, List<notes> notesList, String type) {
        this.context = context;
        this.notesList = notesList;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptor.ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide_left_in_anim);
        Animation animation1 = AnimationUtils.loadAnimation(context,R.anim.slide_right_in_anim);
        animation.setDuration(700);
        animation1.setDuration(900);
        notes note = notesList.get(position);
        holder.textView.setText(note.getDescription());
        holder.time.setText(note.getTime());
        String[] bg = {"#fe4a49","#2ab7ca","#fed766","#e6e6ea","#f4f4f8","#eee3e7","#ead5dc","#eec9d2","#f4b6c2","#f6abb6", "#011f4b","#03396c","#005b96","#6497b1","#b3cde0","#051e3e","#251e3e","#451e3e","#651e3e","#851e3e","#ee4035","#f37736","#fdf498","#7bc043","#0392cf", "#96ceb4","#ffeead","#ff6f69","#ffcc5c","#88d8b0","#d11141","#00b159","#00aedb","#f37735","#ffc425","#eb6841","#cc2a36","#4f372d","#00a0b0","#2e003e"};
        Random random=new Random();
        int r = random.nextInt(bg.length);
        int r1 = random.nextInt(2);
        holder.cardView.setCardBackgroundColor(Color.parseColor(bg[r]));
        if(r1==0) holder.cardView.startAnimation(animation);
        else holder.cardView.startAnimation(animation1);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        public TextView time;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView5);
            time = itemView.findViewById(R.id.textView6);
            cardView = itemView.findViewById(R.id.cardview);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            notes note = notesList.get(position);
            String desc = note.getDescription();
            String type = note.getType();
            int id = note.getId();
            Intent intent = new Intent(context, authenticationPassword.class);
            intent.putExtra("desc",desc);
            intent.putExtra("type",type);
            intent.putExtra("id",id);
            intent.putExtra("send","edit");
            context.startActivity(intent);
        }
    }
}
