package com.example.medicinealarm;

public class Alarm{
    private int id;
    private String title;
    private String time;
    private boolean alarmEnabled;

    public Alarm(int id, String title, String time) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.alarmEnabled = true;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isAlarmEnabled() {
        return alarmEnabled;
    }

    public void setAlarmEnabled(boolean enabled) {
        alarmEnabled = enabled;
    }


}