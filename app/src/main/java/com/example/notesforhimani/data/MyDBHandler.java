package com.example.notesforhimani.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notesforhimani.model.notes;
import com.example.notesforhimani.params.params;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {


    public MyDBHandler(@Nullable Context context) {
        super(context, params.DB_NAME,null, params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE "+params.TABLE_NAME+"("+params.KEY_ID+" integer primary key, "+params.KEY_NAME+" text, "+params.KEY_TIME+" text, "+params.KEY_TYPE+" text)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNote(notes note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(params.KEY_NAME, note.getDescription());
        values.put(params.KEY_TIME,note.getTime());
        values.put(params.KEY_TYPE,note.getType());
        db.insert(params.TABLE_NAME, null, values);
        db.close();
    }

    public List<notes> getallNotes(){
        List<notes> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM "+params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst()){
            do{
                notes note = new notes();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setDescription(cursor.getString(1));
                note.setTime(cursor.getString(2));
                note.setType(cursor.getString(3));
                notesList.add(note);
            }while(cursor.moveToNext());
        }
        db.close();
        return notesList;
    }

    public int updateNote(notes note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(params.KEY_NAME,note.getDescription());
        values.put(params.KEY_TIME,note.getTime());
        return  db.update(params.TABLE_NAME, values, params.KEY_ID+" =?",new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(params.TABLE_NAME,params.KEY_ID+" =?",new String[]{String.valueOf(id)});
        db.close();
    }
}
