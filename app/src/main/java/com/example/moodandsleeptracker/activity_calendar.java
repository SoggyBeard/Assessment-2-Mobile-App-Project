package com.example.moodandsleeptracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class activity_calendar extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();;
    CalendarView calendarView;
    TimePickerDialog timePickerDialog;
    TextView label;
    EditText commentBox;
    static int arraySize;
    int y, m, d, currentHour, currentMinute;
    int startHour = 0, startMin = 0, endHour = 0, endMin = 0;
    int arrayIndex = 0;
    int faceId = 0;
    String timeDisplay;
    boolean exists = false;
    static data[] calendarData = new data[1];
    ImageButton faceBtn1, faceBtn2, faceBtn3, faceBtn4, faceBtn5, btnMood, btnSleep;
    Button chooseStartTime, chooseEndTime;
    long minDate = 1546261200000L;
    long maxDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Intent intent = getIntent();

        arraySize = calendarData.length;
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        commentBox = (EditText) findViewById(R.id.commentBox);
        label = (TextView) findViewById(R.id.test);
        faceBtn1 = (ImageButton) findViewById(R.id.faceBtn1);
        faceBtn2 = (ImageButton) findViewById(R.id.faceBtn2);
        faceBtn3 = (ImageButton) findViewById(R.id.faceBtn3);
        faceBtn4 = (ImageButton) findViewById(R.id.faceBtn4);
        faceBtn5 = (ImageButton) findViewById(R.id.faceBtn5);
        btnMood = (ImageButton) findViewById(R.id.btnMood2);
        btnSleep = (ImageButton) findViewById(R.id.btnSleep2);
        chooseStartTime = findViewById(R.id.chooseSleepStart);
        chooseEndTime = findViewById(R.id.chooseSleepEnd);

        DateFormat dateMonth = new SimpleDateFormat("MM");
        DateFormat dateYear = new SimpleDateFormat("YYYY");
        DateFormat dateDay = new SimpleDateFormat("dd");
        Date date = new Date();
        y = Integer.parseInt(dateYear.format(date));
        m = Integer.parseInt(dateMonth.format(date));
        d = Integer.parseInt(dateDay.format(date));

        if(arraySize > 1) {
            existingEntry();
        }
        else
        {
            addEntry();
        }

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        maxDate = calendar.getTimeInMillis();
        calendarView.setMinDate(minDate);
        calendarView.setMaxDate(maxDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    if(exists == true) {
                        updateEntry();
                    }
                    resetFaces();
                    faceId = 0;
                    startHour = 0;
                    startMin = 0;
                    endHour = 0;
                    endMin = 0;

                    y = year;
                    m = month + 1;
                    d = dayOfMonth;

                    existingEntry();
                    Log.d("boop","size" + Integer.toString(arraySize));
                    Log.d("boop", "********");
                    for (int i = 0; i < calendarData.length-1; i++)
                    {
                        Log.d("boop", " " +Integer.toString(calendarData[i].getYear()) + " " + Integer.toString(calendarData[i].getMonth()) + " " + Integer.toString(calendarData[i].getDay()) + " " +
                                Integer.toString(calendarData[i].getBtnPushed()) + " " + calendarData[i].getStartHour());
                    }
                }
        });

        faceBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFaces();
                faceId = 1;
                alphaFaces();
                updateEntry();
            }
        });

        faceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFaces();
                faceId = 2;
                alphaFaces();
                updateEntry();
            }
        });

        faceBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFaces();
                faceId = 3;
                alphaFaces();
                updateEntry();
            }
        });

        faceBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFaces();
                faceId = 4;
                alphaFaces();
                updateEntry();
            }
        });

        faceBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFaces();
                faceId = 5;
                alphaFaces();
                updateEntry();
            }
        });

        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMood();
            }
        });

        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSleep();
            }
        });

        chooseStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity_calendar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        timeLabel(hourOfDay, minutes);
                        chooseStartTime.setText(timeDisplay);
                        startHour = hourOfDay;
                        startMin = minutes;
                        updateEntry();
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

        chooseEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity_calendar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        timeLabel(hourOfDay, minutes);
                        chooseEndTime.setText(timeDisplay);
                        endHour = hourOfDay;
                        endMin = minutes;
                        updateEntry();
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

    }

    public void timeLabel(int hour, int min)
    {
        if(hour >= 12)
        {
            timeDisplay = hour - 12 + ":" + min + "PM";
        }else {
            timeDisplay = hour + ":" + min + "AM";
        }
    }

    public void openMood()
    {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("data", calendarData);
        intent.putExtra("size",arraySize);
        startActivity(intent);
    }

    public void openSleep()
    {
        Intent intent = new Intent(this, sleep_activty.class);
        startActivity(intent);
    }

    public void alphaFaces()
    {
        if(faceId == 1)
        {
            faceBtn1.setImageAlpha(127);
        }
        if(faceId == 2)
        {
            faceBtn2.setImageAlpha(127);
        }
        if(faceId == 3)
        {
            faceBtn3.setImageAlpha(127);
        }
        if(faceId == 4)
        {
            faceBtn4.setImageAlpha(127);
        }
        if(faceId == 5)
        {
            faceBtn5.setImageAlpha(127);
        }
    }

    public void resetFaces()
    {
        faceBtn1.setImageAlpha(255);
        faceBtn2.setImageAlpha(255);
        faceBtn3.setImageAlpha(255);
        faceBtn4.setImageAlpha(255);
        faceBtn5.setImageAlpha(255);
    }

    public void existingEntry()
    {
        exists = false;
        for (int i = 0; i <  calendarData.length-1; i++) {
            if (y == calendarData[i].getYear() && m == calendarData[i].getMonth() && d == calendarData[i].getDay()) {
                label.setText(Integer.toString(i));
                arrayIndex = i;
                exists = true;
                loadEntry();
                break;
            }
        }

        if (exists != true) {
            Log.d("boop", "hit");
            label.setText("false");
            chooseStartTime.setText("Select");
            chooseEndTime.setText("Select");
            exists = false;
            addEntry();
        }
    }

    public void loadEntry()
    {
        commentBox.setText(calendarData[arrayIndex].getComment());
        faceId = calendarData[arrayIndex].getBtnPushed();
        alphaFaces();
        startHour = calendarData[arrayIndex].getStartHour();
        startMin = calendarData[arrayIndex].getStartMin();
        endHour = calendarData[arrayIndex].getEndHour();
        endMin = calendarData[arrayIndex].getEndMin();
        if(calendarData[arrayIndex].getStartHour() != 0) {
            timeLabel(calendarData[arrayIndex].getStartHour(), calendarData[arrayIndex].getStartMin());
            chooseStartTime.setText(timeDisplay);
        }else {
            chooseStartTime.setText("Select");
        }
        if(calendarData[arrayIndex].getEndHour() != 0) {
            timeLabel(calendarData[arrayIndex].getEndHour(), calendarData[arrayIndex].getEndMin());
            chooseEndTime.setText(timeDisplay);
        }else {
            chooseEndTime.setText("Select");
        }
    }

    public void addEntry()
    {
        arrayIndex = arraySize-1;
        calendarData[arrayIndex] = new data(y,m,d, faceId ,startHour, startMin, endHour, endMin, commentBox.getText().toString());
        arraySize = calendarData.length;
        arraySize++;
        calendarData = Arrays.copyOf(calendarData, arraySize);
        commentBox.setText("");
    }

    public void updateEntry()
    {
        calendarData[arrayIndex] = new data(y,m,d, faceId, startHour, startMin, endHour, endMin, commentBox.getText().toString());
    }
}
