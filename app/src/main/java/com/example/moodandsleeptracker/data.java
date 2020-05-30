package com.example.moodandsleeptracker;

import android.widget.Chronometer;

public class data implements Comparable<data>{
    private String comment;
    private int year, month, day, btnPushed;
    private Long startTime, endTime;

    public data(int year, int month, int day, int btnPushed, Long startTime, Long endTime, String comment){
        this.year = year;
        this.month = month;
        this.day = day;
        this.btnPushed = btnPushed;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Long getStartTime()
    {
        return startTime;
    }
    public Long getEndTime()
    {
        return endTime;
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
