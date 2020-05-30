package com.example.moodandsleeptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import com.anychart.charts.Pie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> months = new ArrayList<String>();
    String date;
    int faceVal1, faceVal2, faceVal3, faceVal4, faceVal5, faceVal6;
    boolean exists = false;
    private int arraySize = activity_calendar.arraySize;
    int currentYear, currentMonth, yearPos, faceTotal;
    double per1, per2, per3, per4, per5;
    int y = 2019;
    data[] calendarData = new data[arraySize];

    private ImageButton btnMood, btnCalendar;
    Spinner yearSpin, monthSpin;
    TextView c1, c2, c3, c4, c5, c6, p1, p2, p3, p4, p5;
    AnyChartView pieChart;


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
        p1 = (TextView) findViewById(R.id.percent1);
        p2 = (TextView) findViewById(R.id.percent2);
        p3 = (TextView) findViewById(R.id.percent3);
        p4 = (TextView) findViewById(R.id.percent4);
        p5 = (TextView) findViewById(R.id.percent5);
        pieChart = findViewById(R.id.pie);


        createSpinners();

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
        int y = Integer.parseInt(years.get(yearSpin.getSelectedItemPosition()));/*
        String first = months.get(monthSpin.getSelectedItemPosition()).substring(0,1);
        if (monthSpin.getSelectedItemPosition() > 8)
        {
            first =  months.get(position).substring(0,2);
        }*/
        int m = monthSpin.getSelectedItemPosition()+1;

        resetCounters();

        if(arraySize > 0) {
            Log.d("boop", "main " + m);
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
                        faceVal6++;
                        c6.setText(Integer.toString(faceVal6));
                    }
                }
                Log.d("boop", " " + Integer.toString(calendarData[i].getYear()) + " " + Integer.toString(calendarData[i].getMonth()) + " " + Integer.toString(calendarData[i].getDay()) + " " +
                        Integer.toString(calendarData[i].getBtnPushed()) + " " + calendarData[i].getComment());
            }

        }
        percentage();
    }

    public void resetCounters()
    {
        faceTotal = 0;
        faceVal1 = 0;
        faceVal2 = 0;
        faceVal3 = 0;
        faceVal4 = 0;
        faceVal5 = 0;
        faceVal6 = 0;
        per1 = 0;
        per2 = 0;
        per3 = 0;
        per4 = 0;
        per5 = 0;

        c1.setText(Integer.toString(faceVal1));
        c2.setText(Integer.toString(faceVal2));
        c3.setText(Integer.toString(faceVal3));
        c4.setText(Integer.toString(faceVal4));
        c5.setText(Integer.toString(faceVal5));
        c6.setText(Integer.toString(faceVal6));

        p1.setText(String.format("%.0f",per1) + "%");
        p2.setText(String.format("%.0f",per2) + "%");
        p3.setText(String.format("%.0f",per3) + "%");
        p4.setText(String.format("%.0f",per4) + "%");
        p5.setText(String.format("%.0f",per5) + "%");
    }

    public void createSpinners()
    {
        calendarData = activity_calendar.calendarData;
        DateFormat dateMonth = new SimpleDateFormat("MM");
        DateFormat dateYear = new SimpleDateFormat("YYYY");
        Date date = new Date();
        currentYear = Integer.parseInt(dateYear.format(date));
        currentMonth = Integer.parseInt(dateMonth.format(date));

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

    public void percentage()
    {
        faceTotal = faceVal2 + faceVal3 + faceVal4 + faceVal5 + faceVal6;
        if(faceTotal > 0) {
            per1 = faceVal2 * 100 / faceTotal;
            per2 = faceVal3 * 100 / faceTotal;
            per3 = faceVal4 * 100 / faceTotal;
            per4 = faceVal5 * 100 / faceTotal;
            per5 = faceVal6 * 100 / faceTotal;

            p1.setText(String.format("%.0f", per5) + "%");
            p2.setText(String.format("%.0f", per4) + "%");
            p3.setText(String.format("%.0f", per3) + "%");
            p4.setText(String.format("%.0f", per2) + "%");
            p5.setText(String.format("%.0f", per1) + "%");
            createPie();
            //createLineGraph();
        }

    }

    public void createPie()// mmmm pie
    {
        Log.d("boop", "test pie" + per1);
        List<DataEntry> percent = new ArrayList<>();
        percent.clear();
        percent.add(new ValueDataEntry("Sad",per1));
        percent.add(new ValueDataEntry("Mid sad ",per2));
        percent.add(new ValueDataEntry("Mild",per3));
        percent.add(new ValueDataEntry("Mid happy",per4));
        percent.add(new ValueDataEntry("Happy",per5));

        Pie pie = AnyChart.pie();
        pie.data(percent);
        pieChart.setChart(pie);

    }

    public void createLineGraph()
    {
        List<DataEntry> percent = new ArrayList<>();
        percent.clear();
        percent.add(new ValueDataEntry(0,per1));
        percent.add(new ValueDataEntry(0,per2));
        percent.add(new ValueDataEntry(0,per3));
        percent.add(new ValueDataEntry(0,per4));
        percent.add(new ValueDataEntry(0,per5));



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
