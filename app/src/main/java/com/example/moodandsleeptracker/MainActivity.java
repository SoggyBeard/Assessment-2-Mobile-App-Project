package com.example.moodandsleeptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> months = new ArrayList<String>();
    String date;
    int faceVal1, faceVal2, faceVal3, faceVal4, faceVal5, faceVal6;
    boolean exists = false;
    private int arraySize = activity_calendar.arraySize;
    int y = 2019;
    data[] calendarData = new data[arraySize];

    private ImageButton btnMood, btnCalendar;
    Spinner yearSpin, monthSpin;
    TextView c1, c2, c3, c4, c5, c6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalendar = (ImageButton) findViewById(R.id.btnCalendar);
        yearSpin = (Spinner) findViewById(R.id.yearSpin);
        monthSpin = (Spinner) findViewById(R.id.monthSpinner);
        c1 = (TextView) findViewById(R.id.count1);
        c2 = (TextView) findViewById(R.id.count2);
        c3 = (TextView) findViewById(R.id.count3);
        c4 = (TextView) findViewById(R.id.count4);
        c5 = (TextView) findViewById(R.id.count5);
        c6 = (TextView) findViewById(R.id.count6);

        Intent intent = getIntent();
        //arraySize = intent.getIntExtra("size", 0);


        calendarData = activity_calendar.calendarData;

        for (int i = 0; i < 100; i++) {
            years.add(Integer.toString(y));
            y++;
        }

        months.add("1. January");
        months.add("2. February");
        months.add("3. March");
        months.add("4. April");
        months.add("5. May");
        months.add("6. June");
        months.add("7. July");
        months.add("8. August");
        months.add("9. September");
        months.add("10. October");
        months.add("11 November");
        months.add("12 December");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        yearSpin.setAdapter(adapter1);
        yearSpin.setSelection(1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        monthSpin.setAdapter(adapter2);
        monthSpin.setSelection(1);

        yearSpin.setOnItemSelectedListener(this);
        monthSpin.setOnItemSelectedListener(this);

        //calendarData = (data[])getIntent().getParcelableArrayExtra("data");




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
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int y = Integer.parseInt(years.get(yearSpin.getSelectedItemPosition()));
        char first = months.get(position).charAt(0);
        int m = Character.getNumericValue(first);

        if(arraySize > 0) {
            Log.d("boop", "main ");
            for (int i = 0; i < arraySize-1; i++) {
                if(y == calendarData[i].getYear() && m == calendarData[i].getMonth())
                {
                    if(calendarData[i].getBtnPushed() == 0)
                    {
                        faceVal1++;
                        c1.setText(Integer.toString(faceVal1));
                    }
                    if(calendarData[i].getBtnPushed() == 1)
                    {
                        faceVal2++;
                        c2.setText(Integer.toString(faceVal2));
                    }
                    if(calendarData[i].getBtnPushed() == 2)
                    {
                        faceVal3++;
                        c3.setText(Integer.toString(faceVal3));
                    }
                    if(calendarData[i].getBtnPushed() == 3)
                    {
                        faceVal4++;
                        c4.setText(Integer.toString(faceVal4));
                    }
                    if(calendarData[i].getBtnPushed() == 4)
                    {
                        faceVal5++;
                        c5.setText(Integer.toString(faceVal5));
                    }
                    if(calendarData[i].getBtnPushed() == 5)
                    {
                        faceVal5++;
                        c6.setText(Integer.toString(faceVal6));
                    }
                }
                Log.d("boop", " " + Integer.toString(calendarData[i].getYear()) + " " + Integer.toString(calendarData[i].getMonth()) + " " + Integer.toString(calendarData[i].getDay()) + " " +
                        Integer.toString(calendarData[i].getBtnPushed()) + " " + calendarData[i].getComment());
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
