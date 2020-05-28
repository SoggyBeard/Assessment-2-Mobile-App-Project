package com.example.moodandsleeptracker;

public class data implements Comparable<data>{
    private String comment;
    private int year, month, day, btnPushed;

    public data(int year, int month, int day, int btnPushed, String comment){
        this.year = year;
        this.month = month;
        this.day = day;
        this.btnPushed = btnPushed;
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

    public String getComment()
    {
        return comment;
    }

    @Override
    public int compareTo(data o) {
        return 0;
    }
}
