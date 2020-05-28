package com.example.moodandsleeptracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class activity_calendar extends AppCompatActivity {
    CalendarView calendarView;
    TextView label;
    EditText commentBox;
    int arraySize, y, m, d;
    int arrayIndex = 0;
    int first = 0;
    boolean exists = false;
    data[] calendarData = new data[1];
    //public static String[][][] calendarData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Intent intent = getIntent();

        arraySize = calendarData.length;
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        commentBox = (EditText) findViewById(R.id.commentBox);
        label = (TextView) findViewById(R.id.test) ;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    y = year;
                    m = month;
                    d = dayOfMonth;
                    arraySize = calendarData.length -1;


                    existingEntry();
                    if(exists == false)
                    {
                        calendarData[arrayIndex] = new data(year,month,dayOfMonth,1,commentBox.getText().toString());
                    }
                    if(exists == true)
                    {
                        commentBox.setText(calendarData[arrayIndex].getComment());
                    }

                    //label.setText(Integer.toString(calendarData[arrayIndex].getDay()));
                    arraySize++;
                    //arrayIndex++;
                    calendarData = Arrays.copyOf(calendarData, arraySize);
                }
            });
    }
    public void existingEntry()
    {
        for (int i = 0; i < calendarData.length; i++)
        {
            Log.d("boop",Integer.toString(d));
            if(first == 0)
            {
                exists = false;
            }
            if (first != 0) {
                if (y == calendarData[i].getYear() && m == calendarData[i].getMonth() && d == calendarData[i].getDay()) {
                    label.setText(Integer.toString(i));
                    arrayIndex = i;
                    exists = true;
                } else {
                    label.setText("false");
                    exists = false;
                }
            }
        }
        first = 1;
    }
}
