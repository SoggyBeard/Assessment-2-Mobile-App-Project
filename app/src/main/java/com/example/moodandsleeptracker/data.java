package com.example.moodandsleeptracker;

import android.widget.Chronometer;

public class data implements Comparable<data>{
    private String comment;
    private int year, month, day, btnPushed, startHour, startMin, endHour, endMin;

    public data(int year, int month, int day, int btnPushed, int startHour, int startMin, int endHour, int endMin, String comment){
        this.year = year;
        this.month = month;
        this.day = day;
        this.btnPushed = btnPushed;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.comment = comment;
    }

    public int getYear()
    {
        return year;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getBtnPushed()
    {
        return btnPushed;
    }

    public int getStartHour()
    {
        return startHour;
    }
    public int getStartMin()
    {
        return startMin;
    }

    public int getEndHour()
    {
        return endHour;
    }
    public int getEndMin()
    {
        return endMin;
    }

    public String getComment()
    {
        return comment;
    }

    @Override
    public int compareTo(data o) {
        return 0;
    }
}
