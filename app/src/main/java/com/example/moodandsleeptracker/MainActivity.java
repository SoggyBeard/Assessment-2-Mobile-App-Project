package com.example.moodandsleeptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    String date;
    public static String[][][] calendarData;

    private ImageButton btnMood, btnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalendar = (ImageButton) findViewById(R.id.btnCalendar);

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });

    }

    public void openCalendar()
    {
        Intent intent = new Intent(this, activity_calendar.class);
        //intent.putExtra("data", calendarData);
        startActivity(intent);
    }
}
