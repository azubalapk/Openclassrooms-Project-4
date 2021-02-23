package com.example.projet_3_oc_maru.Activities;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projet_3_oc_maru.DI.DI;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;
import com.example.projet_3_oc_maru.R;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;


public class AddMeetingActivity extends AppCompatActivity  {

    int idMeeting;
    EditText subjectMeeting;
    TextView timeBegin;
    TextView timeEnd;
    EditText participantsMeeting;
    TextView displayIdMeeting;
    TextView dateMeeting;
    NumberPicker numberRoomMeetingNp;
    Button btnTimePickerBegin,btnTimePickerEnd,createNewRoomMeetingButton,btnDate;
    LocalDate dateObject;
    LocalTime timeEndObject;
    LocalTime timeBeginObject;
    int  mHour, mMinute,mYear,mMonth,mDay;

    @RequiresApi(api = VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViews();
        initializeNumberPickerForSelectRoomMeeting();
        userClickOnButtonForCreateNewMeeting();
        userClickOnButtonForSelectDate();
        userClickOnButtonForSelectTimeBegin();
        userClickOnButtonForSelectTimeEnd();

        idMeeting = DI.getMeetingApiService().getMeetings().size()+1;
        displayIdMeeting.setText("Reunion "+idMeeting);

    }

    public void setUpViews() {
        displayIdMeeting =findViewById(R.id.idMeeting);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = VERSION_CODES.O)
    public void userClickOnButtonForCreateNewMeeting(){

        createNewRoomMeetingButton.setOnClickListener(v -> {


            Meeting meeting = new Meeting(
                    idMeeting ,
                    subjectMeeting.getText().toString(),
                    LocalDateTime.of(dateObject,timeBeginObject),
                    LocalDateTime.of(dateObject,timeEndObject),
                    participantsMeeting.getText().toString(),
                    RoomMeeting.getRoomMeetingById(numberRoomMeetingNp.getValue())
            );
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            CharSequence text ;
            Toast toast ;

            if (subjectMeeting.getText().toString().equals("")) {
                text ="Veuillez SVP nommer le sujet de votre réunion" ;
                toast = Toast.makeText(context, text, duration);
                toast.show();

            }else
            if (dateObject.toString().equals("")) {
                text = "Veuillez SVP définir une date";
                toast = Toast.makeText(context, text, duration);
                toast.show();

            }else
            if (timeBeginObject.toString().equals("")) {
                text = "Veuillez SVP définir l'heure de début";
                toast = Toast.makeText(context, text, duration);
                toast.show();

            }else
            if (timeEndObject.toString().equals("")) {
                text = "Veuillez SVP définir l'heure de fin";
                toast = Toast.makeText(context, text, duration);
                toast.show();

            }else
            if(participantsMeeting.getText().toString().equals("")) {
                text = "Veuillez SVP renseigner les adresses mail des participants";
                toast = Toast.makeText(context, text, duration);
                toast.show();

            }else{
                /* Gestion de la disponibilité des salles */
                boolean timeProblem = false;
                boolean reserved = false;
                for (Meeting m : DI.getMeetingApiService().getMeetings()) {
                    if (m.getMeetingRoom().getId().equals(numberRoomMeetingNp.getValue()) &&
                            ((LocalDateTime.of(dateObject,timeBeginObject).isBefore(m.getDateTimeEnd()) && LocalDateTime.of(dateObject,timeBeginObject).isAfter(m.getDateTimeBegin()))
                                    || (LocalDateTime.of(dateObject,timeEndObject).isBefore(m.getDateTimeEnd()) && LocalDateTime.of(dateObject,timeEndObject).isAfter(m.getDateTimeBegin()))
                                    || LocalDateTime.of(dateObject,timeBeginObject).isEqual(m.getDateTimeBegin())
                                    || LocalDateTime.of(dateObject,timeEndObject).isEqual(m.getDateTimeEnd())
                                    || m.getDateTimeBegin().isAfter(LocalDateTime.of(dateObject,timeBeginObject)) && m.getDateTimeBegin().isBefore(LocalDateTime.of(dateObject,timeEndObject))
                                    || m.getDateTimeEnd().isBefore(LocalDateTime.of(dateObject,timeEndObject)) && m.getDateTimeEnd().isAfter(LocalDateTime.of(dateObject,timeBeginObject)))) {
                        reserved = true;
                        break;
                    } else if (LocalDateTime.of(dateObject,timeBeginObject).isAfter(LocalDateTime.of(dateObject,timeEndObject)) || LocalDateTime.of(dateObject,timeBeginObject).isEqual(LocalDateTime.of(dateObject,timeEndObject))) {

                        timeProblem = true;
                    }
                }
                if (timeProblem) {
                    text = "Veuillez vérifier les heures de début et de fin";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else if (reserved) {
                    text = "Cette salle est déjà réservée";
                    toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else {
                    DI.getMeetingApiService().createMeeting(meeting);
                    finish();
                }
            }

        });
    }


    public void userClickOnButtonForSelectDate(){
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
                             dateObject =LocalDate.of(year,monthOfYear+1,dayOfMonth);
                         }
                     }, mYear, mMonth, mDay);
             datePickerDialog.show();
         });
    }

    public void userClickOnButtonForSelectTimeBegin(){
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


                    }, mHour, mMinute, true);
            timePickerDialog.show();
        });
    }

    public void userClickOnButtonForSelectTimeEnd(){
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

                    }, mHour, mMinute, true);
            timePickerDialog.show();
        });
    }

}