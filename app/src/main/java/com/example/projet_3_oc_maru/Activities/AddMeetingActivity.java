package com.example.projet_3_oc_maru.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.projet_3_oc_maru.DI.DI;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;
import com.example.projet_3_oc_maru.R;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity  {

    EditText idMeeting;
    EditText subjectMeeting;
    EditText timeBegin;
    EditText timeEnd;
    EditText participantsMeeting;
    EditText dateMeeting;
    NumberPicker numberRoomMeetingNp;





    Button btnTimePickerBegin,btnTimePickerEnd,createNewRoomMeetingButton,btnDate;

    LocalDate dateObject;
    LocalTime timeEndObject;
    LocalTime timeBeginObject;

    int  mHour, mMinute,mYear,mMonth,mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        setUpViews();
        initializeNumberPickerForSelectRoomMeeting();
        UserClickOnButtonForCreateNewMeeting();
        UserClickOnButtonForSelectDate();
        UserClickOnButtonForSelectTimeBegin();
        UserClickOnButtonForSelectTimeEnd();

    }

    public void setUpViews() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        idMeeting = findViewById(R.id.idMeeting);
        subjectMeeting = findViewById(R.id.subjectMeeting);
        timeBegin = findViewById(R.id.timeBeginMeeting);
        timeEnd = findViewById(R.id.timeEndMeeting);
        participantsMeeting = findViewById(R.id.participantsMeeting);
        dateMeeting = findViewById(R.id.dateMeeting);

        createNewRoomMeetingButton = findViewById(R.id.create);
        btnDate = findViewById(R.id.btn_date);
        btnTimePickerBegin= findViewById(R.id.btn_time_begin);
        btnTimePickerEnd = findViewById(R.id.btn_time_end);

        numberRoomMeetingNp = findViewById(R.id.numberRoomMeeting);






    }

    public void initializeNumberPickerForSelectRoomMeeting(){
        numberRoomMeetingNp.setMinValue(1);
        numberRoomMeetingNp.setMaxValue(10);
        numberRoomMeetingNp.setWrapSelectorWheel(true);
    }

    public void UserClickOnButtonForCreateNewMeeting(){
        createNewRoomMeetingButton.setOnClickListener(v -> {
            Meeting meeting = new Meeting(
                    idMeeting.getText().toString(),
                    subjectMeeting.getText().toString(),
                    dateObject,
                    timeBeginObject,
                    timeEndObject,
                    participantsMeeting.getText().toString(),
                    RoomMeeting.getRoomMeetingById(numberRoomMeetingNp.getValue())
            );

            DI.getMeetingApiService().createMeeting(meeting);
            finish();

        });
    }

    public void UserClickOnButtonForSelectDate(){
         btnDate.setOnClickListener(v -> {
             // Get Current Date
             final Calendar c = Calendar.getInstance();
             mYear = c.get(Calendar.YEAR);
             mMonth = c.get(Calendar.MONTH);
             mDay = c.get(Calendar.DAY_OF_MONTH);


             DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                     (view, year, monthOfYear, dayOfMonth) -> {

                         dateMeeting.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                         if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
                             dateObject =LocalDate.of(year,monthOfYear,dayOfMonth);
                         }
                     }, mYear, mMonth, mDay);
             datePickerDialog.show();
         });
    }

    public void UserClickOnButtonForSelectTimeBegin(){
        btnTimePickerBegin.setOnClickListener(v -> {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {

                        timeBegin.setText(hourOfDay + ":" + minute);
                        if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
                            timeBeginObject=LocalTime.of(hourOfDay,minute);
                        }


                    }, mHour, mMinute, false);
            timePickerDialog.show();
        });

    }
    public void UserClickOnButtonForSelectTimeEnd(){
        btnTimePickerEnd.setOnClickListener(v -> {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {

                        timeEnd.setText(hourOfDay + ":" + minute);
                        if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
                            timeEndObject = LocalTime.of(hourOfDay,minute);
                        }

                    }, mHour, mMinute, false);
            timePickerDialog.show();
        });
    }
}