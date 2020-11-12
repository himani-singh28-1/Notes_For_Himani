package com.example.notesforhimani.model;

public class notes {
    int id;
    String description;
    String time;
    String type;

    public notes() {
    }

    public notes(int id, String description, String time, String type) {
        this.id = id;
        this.description = description;
        this.time = time;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
