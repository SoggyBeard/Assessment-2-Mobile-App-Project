package com.example.moodandsleeptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class sleep_activty extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> months = new ArrayList<String>();

    private int arraySize = activity_calendar.arraySize;
    int sumH=0, sumM=0;
    private data[] calendarData = new data[arraySize];
    String[] sleepData= new String[1];
    int currentYear, currentMonth, yearPos, sleepHour, sleepMin;
    int y = 2019;
    Spinner yearSpin, monthSpin;
    private ImageButton btnMood, btnCalendar;
    TextView monthTotal;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_activty);
        calendarData = activity_calendar.calendarData;
        yearSpin = (Spinner) findViewById(R.id.yearSpin2);
        monthSpin = (Spinner) findViewById(R.id.monthSpinner2);
        monthTotal = (TextView) findViewById(R.id.monthTotal);
        listView = (ListView) findViewById(R.id.monthList);
        createSpinners();

        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMood();
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });
    }

    public void openMood()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openCalendar()
    {
        Intent intent = new Intent(this, activity_calendar.class);
        startActivity(intent);
    }

    public void createSpinners()
    {
        DateFormat dateMonth = new SimpleDateFormat("MM");
        DateFormat dateYear = new SimpleDateFormat("YYYY");
        Date date = new Date();
        currentYear = Integer.parseInt(dateYear.format(date));
        currentMonth = Integer.parseInt(dateMonth.format(date));
        btnCalendar = (ImageButton) findViewById(R.id.btnCalendar3);
        btnMood = (ImageButton) findViewById(R.id.btnMood3);

        for (int i = 0; i < 100; i++) {
            if (currentYear == y)
            {
                yearPos = i;
            }
            years.add(Integer.toString(y));
            y++;
        }
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        yearSpin.setAdapter(adapter1);
        yearSpin.setSelection(yearPos);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        monthSpin.setAdapter(adapter2);
        monthSpin.setSelection(currentMonth-1);
        yearSpin.setOnItemSelectedListener(this);
        monthSpin.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int y = Integer.parseInt(years.get(yearSpin.getSelectedItemPosition()));
        int m = monthSpin.getSelectedItemPosition()+1;

        if(arraySize > 0) {
            Log.d("boop", "main " + arraySize);
            Log.d("boop", Integer.toString( calendarData.length));
            for (int i = 0; i < arraySize -1; i++) {
                if(y == calendarData[i].getYear() && m == calendarData[i].getMonth())
                {
                    sleepData = Arrays.copyOf(sleepData, i+1);

                    sleepHour = calendarData[i].getEndHour() - calendarData[i].getStartHour();
                    sleepMin = calendarData[i].getEndMin() - calendarData[i].getStartMin();
                    if(sleepMin < 0)
                    {
                        sleepHour--;
                        sleepMin = 60 + sleepMin;
                    }
                    if(sleepHour < 0)
                    {
                        sleepHour = sleepHour * -1;
                        sleepData[i] = "Day: " + calendarData[i].getDay() + ") 1 Day " + sleepHour + " Hours " + sleepMin + " Minutes";
                    }else {
                        sleepData[i] = "Day: " + calendarData[i].getDay() + ") " + sleepHour + " Hours " + sleepMin + " Minutes";
                    }
                    if(calendarData[i].getEndHour() == calendarData[i].getStartHour() && calendarData[i].getEndMin() == calendarData[i].getStartMin() || calendarData[i].getEndHour() == 0
                            || calendarData[i].getStartHour() == 0 || calendarData[i].getStartMin() == 0 ||calendarData[i].getStartMin() == 0)
                    {
                        sleepData[i] = "Day: " + calendarData[i].getDay() + ") No sleep recorded!";
                    }
                    sumH += sleepHour;
                    sumM += sleepMin;
                    // Log.d("boop", sleepData[i]);
                }
            }

            monthTotal.setText("Average Sleep this month: " + sumH / arraySize + ":" + sumM / arraySize);
            ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sleepData);
            listView.setAdapter(adapterList);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
