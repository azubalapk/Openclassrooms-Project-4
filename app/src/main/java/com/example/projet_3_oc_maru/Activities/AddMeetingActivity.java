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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
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
import java.util.Objects;


public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText subjectMeeting,participantsMeeting;
    TextView timeBegin,timeEnd,displayIdMeeting,dateMeeting;
    Button btnTimePickerBegin,btnTimePickerEnd,createNewRoomMeetingButton,btnDate;
    LocalDate dateObject;
    LocalTime timeEndObject,timeBeginObject;
    int  idMeeting,mHour, mMinute,mYear,mMonth,mDay,positionRoomMeetings;
    Spinner spinnerRoomMeeting;

    @RequiresApi(api = VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        setUpViews();
        userClickOnButtonForCreateNewMeeting();
        userClickOnButtonForSelectDate();
        userClickOnButtonForSelectTimeBegin();
        userClickOnButtonForSelectTimeEnd();
        setIdMeetingAndDisplayThis();
        setUpSpinnerRoomMeeting();

    }
    public void setIdMeetingAndDisplayThis(){
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
        spinnerRoomMeeting = findViewById(R.id.roomMeetingSpinner);
        spinnerRoomMeeting.setOnItemSelectedListener(this);
    }


    public void setUpSpinnerRoomMeeting(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roomsMeeting_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerRoomMeeting.setAdapter(adapter);


    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        positionRoomMeetings = pos + 1;

    }

    public void onNothingSelected(AdapterView<?> parent) {

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

    public void PrepareAndDisplayToast(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast ;
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @RequiresApi(api = VERSION_CODES.O)
    public void userClickOnButtonForCreateNewMeeting(){

        createNewRoomMeetingButton.setOnClickListener(v -> {

            Meeting meeting = new Meeting(idMeeting , subjectMeeting.getText().toString(),
                    LocalDateTime.of(dateObject,timeBeginObject), LocalDateTime.of(dateObject,timeEndObject),
                    participantsMeeting.getText().toString(), RoomMeeting.getRoomMeetingById(positionRoomMeetings)
            );

            if (subjectMeeting.getText().toString().equals("")) {
                PrepareAndDisplayToast("Veuillez SVP nommer le sujet de votre réunion");

            }else if (dateObject.toString().equals("")) {
                PrepareAndDisplayToast("Veuillez SVP définir une date");

            }else if (timeBeginObject.toString().equals("")) {
                PrepareAndDisplayToast("Veuillez SVP définir l'heure de début");

            }else if (timeEndObject.toString().equals("")) {
                PrepareAndDisplayToast("Veuillez SVP définir l'heure de fin");

            }else if(participantsMeeting.getText().toString().equals("")) {
                PrepareAndDisplayToast("Veuillez SVP renseigner les adresses mail des participants");

            }else{
                /* Gestion de la disponibilité des salles */
                boolean timeProblem = false;
                boolean reserved = false;
                for (Meeting m : DI.getMeetingApiService().getMeetings()) {
                    if (m.getMeetingRoom().getId().equals(positionRoomMeetings) &&
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
                    PrepareAndDisplayToast("Veuillez vérifier les heures de début et de fin");

                } else if (reserved) {
                    PrepareAndDisplayToast("Cette salle est déjà réservée");

                } else {
                    DI.getMeetingApiService().createMeeting(meeting);
                    finish();
                }
            }
        });
    }

    public void userClickOnButtonForSelectDate(){
         btnDate.setOnClickListener(v -> {
             getCurrentDateAndTime();

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

            getCurrentDateAndTime();
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

            getCurrentDateAndTime();
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

    public void getCurrentDateAndTime(){
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }
}