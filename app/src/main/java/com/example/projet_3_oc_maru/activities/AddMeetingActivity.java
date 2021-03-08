package com.example.projet_3_oc_maru.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.projet_3_oc_maru.di.DI;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.models.RoomMeeting;
import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.utils.ToastUtil;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText editTextSubject,editTextParticipant;
    ChipGroup chipGroup;
    TextView textViewTimeBegin,textViewTimeEnd,textViewId,textViewDate;
    Button buttonTimePickerBegin,buttonTimePickerEnd,buttonCreateNewMeeting,buttonDate,buttonAdd;
    ArrayList<String> listParticipants = new ArrayList();
    LocalDate localDate;
    LocalTime localTimeEnd,localTimeBegin;
    int  id,mHour, mMinute,mYear,mMonth,mDay,positionRoom;
    Spinner spinnerRoomMeeting;
    Context context;
    DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        getContext();
        setUpViews();
        setIdMeetingAndDisplayThis();
        setUpSpinnerRoomMeeting();
        userClickOnButtonForSelectDate();
        userClickOnButtonForSelectTimeBegin();
        userClickOnButtonForSelectTimeEnd();
        userClickOnButtonForAddParticipant();
        userClickOnButtonForCreateNewMeeting();
        //listParticipants.add("aaaaaaa");

    }


    public void setIdMeetingAndDisplayThis(){
        id = DI.getMeetingApiService().getMeetings().size()+1;
        textViewId.setText("Reunion "+id);
    }

    public void getContext(){
        context = getApplicationContext();
    }

    public void setUpViews() {
        editTextParticipant =findViewById(R.id.editTextParticipants);
        buttonAdd =findViewById(R.id.addParticipant);
        chipGroup = findViewById(R.id.chipGroup);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        textViewId =findViewById(R.id.idMeeting);
        editTextSubject = findViewById(R.id.subjectMeeting);
        textViewTimeBegin = findViewById(R.id.timeBeginMeeting);
        textViewTimeEnd = findViewById(R.id.timeEndMeeting);
        textViewDate = findViewById(R.id.dateMeeting);
        buttonCreateNewMeeting = findViewById(R.id.create);
        buttonDate = findViewById(R.id.btn_date);
        buttonTimePickerBegin= findViewById(R.id.btn_time_begin);
        buttonTimePickerEnd = findViewById(R.id.btn_time_end);
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
        positionRoom = pos + 1;

    }

    public void onNothingSelected(AdapterView<?> parent) {}

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


    public void userClickOnButtonForAddParticipant(){
        buttonAdd.setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(this);
            Chip newChip = (Chip) inflater.inflate(R.layout.layout_chip_entry, chipGroup, false);
            newChip.setText(editTextParticipant.getText().toString());
            chipGroup.addView(newChip);
            listParticipants.add(newChip.getText().toString());
            editTextParticipant.setText("");

        });
    }

    public void userClickOnButtonForCreateNewMeeting(){


        buttonCreateNewMeeting.setOnClickListener(v -> {

            if (editTextSubject.getText().toString().equals("")) {
                ToastUtil.DisplayToastLong("Veuillez SVP nommer le sujet de votre réunion",context);

            }else if (textViewDate.getText().toString().equals("")) {
                ToastUtil.DisplayToastLong("Veuillez SVP définir une date",context);

            }else if (textViewTimeBegin.getText().toString().equals("")) {
                ToastUtil.DisplayToastLong("Veuillez SVP définir l'heure de début",context);

            }else if (textViewTimeEnd.getText().toString().equals("")) {
                ToastUtil.DisplayToastLong("Veuillez SVP définir l'heure de fin",context);

            }else if (listParticipants.size() < 2){
                ToastUtil.DisplayToastLong("Veuillez SVP définir au minimum deux participants ",context);
            }
            else {

                DateTime finalDateTimeBegin = new DateTime(localDate.getYear(),localDate.getMonthOfYear(),localDate.getDayOfMonth(),localTimeBegin.getHourOfDay(),localTimeBegin.getMinuteOfHour());
                DateTime finalDateTimeEnd = new DateTime(localDate.getYear(),localDate.getMonthOfYear(),localDate.getDayOfMonth(),localTimeEnd.getHourOfDay(),localTimeEnd.getMinuteOfHour());

                Meeting meeting = new Meeting(id , editTextSubject.getText().toString(),
                        new DateTime(finalDateTimeBegin), new DateTime(finalDateTimeEnd),
                        listParticipants, RoomMeeting.getRoomMeetingById(positionRoom)
                );

                /* Gestion de la disponibilité des salles */
                boolean timeProblem = false;
                boolean reserved = false;
                for (Meeting m : DI.getMeetingApiService().getMeetings()) {
                    if (m.getMeetingRoom().getId().equals(positionRoom) &&
                            ((finalDateTimeBegin.isBefore(m.getDateTimeEnd())
                                    && finalDateTimeBegin.isAfter(m.getDateTimeBegin()))
                                    || finalDateTimeEnd.isBefore(m.getDateTimeEnd())
                                    && finalDateTimeEnd.isAfter(m.getDateTimeBegin()))
                                    || finalDateTimeBegin.isEqual(m.getDateTimeBegin())
                                    || finalDateTimeEnd.isEqual(m.getDateTimeEnd())
                                    || m.getDateTimeBegin().isAfter(finalDateTimeBegin)
                                    && m.getDateTimeBegin().isBefore(finalDateTimeEnd)
                                    || m.getDateTimeEnd().isBefore(finalDateTimeEnd)
                                    && m.getDateTimeEnd().isAfter(finalDateTimeBegin)) {
                        reserved = true;
                        break;
                    } else if (finalDateTimeBegin.isAfter(finalDateTimeEnd) || finalDateTimeBegin.isEqual(finalDateTimeEnd)) {

                        timeProblem = true;
                    }
                }
                if (timeProblem) {
                    ToastUtil.DisplayToastLong("Veuillez vérifier les heures de début et de fin", context);

                } else if (reserved) {
                    ToastUtil.DisplayToastLong("Cette salle est déjà réservée", context);

                } else {
                    DI.getMeetingApiService().createMeeting(meeting);
                    finish();
                }
            }

        });
    }

    public void userClickOnButtonForSelectDate(){
         buttonDate.setOnClickListener(v -> {

             final Calendar c = Calendar.getInstance();
             mYear = c.get(Calendar.YEAR);
             mMonth = c.get(Calendar.MONTH);
             mDay = c.get(Calendar.DAY_OF_MONTH);

             DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                     (view, year, monthOfYear, dayOfMonth) -> {

                             localDate =new LocalDate(year,monthOfYear+1,dayOfMonth);
                             textViewDate.setText(localDate.toString());

                     }, mYear, mMonth, mDay);
             datePickerDialog.show();
         });
    }

    public void userClickOnButtonForSelectTimeBegin(){
        buttonTimePickerBegin.setOnClickListener(v -> {

            getCurrentTime();

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {

                localTimeBegin=new LocalTime(hourOfDay,minute);
                textViewTimeBegin.setText(localTimeBegin.toString(fmt));

                    }, mHour, mMinute, true);
            timePickerDialog.show();
        });
    }

    public void userClickOnButtonForSelectTimeEnd(){
        buttonTimePickerEnd.setOnClickListener(v -> {

            getCurrentTime();
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {

                            localTimeEnd = new LocalTime(hourOfDay,minute);
                            textViewTimeEnd.setText(localTimeEnd.toString(fmt));

                    }, mHour, mMinute, true);
            timePickerDialog.show();
        });
    }
    public void getCurrentTime(){
        final Calendar c = Calendar.getInstance();
        TimeZone tz =TimeZone.getTimeZone("GMT+1");
        c.setTimeZone(tz);
        mMinute = c.get(Calendar.MINUTE);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMonth = c.get(Calendar.MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
    }

}