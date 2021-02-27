package com.example.projet_3_oc_maru.activities;


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
import android.widget.Spinner;
import android.widget.TextView;
import com.example.projet_3_oc_maru.di.DI;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.models.RoomMeeting;
import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.utils.ToastUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText editTextSubject,editTextParticipants;
    TextView textViewTimeBegin,textViewTimeEnd,textViewId,textViewDate;
    Button buttonTimePickerBegin,buttonTimePickerEnd,buttonCreateNewMeeting,buttonDate;
    LocalDate localDate;
    LocalTime localTimeEnd,localTimeBegin;
    int  id,mHour, mMinute,mYear,mMonth,mDay,positionRoom;
    Spinner spinnerRoomMeeting;
    Context context;

    @RequiresApi(api = VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        getContext();
        setUpViews();
        userClickOnButtonForSelectDate();
        userClickOnButtonForSelectTimeBegin();
        userClickOnButtonForSelectTimeEnd();
        userClickOnButtonForCreateNewMeeting();
        setIdMeetingAndDisplayThis();
        setUpSpinnerRoomMeeting();

    }
    @RequiresApi(api = VERSION_CODES.O)
    public void setIdMeetingAndDisplayThis(){
        id = DI.getMeetingApiService().getMeetings().size()+1;
        textViewId.setText("Reunion "+id);
    }

    public void getContext(){
        context = getApplicationContext();
    }

    public void setUpViews() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        textViewId =findViewById(R.id.idMeeting);
        editTextSubject = findViewById(R.id.subjectMeeting);
        textViewTimeBegin = findViewById(R.id.timeBeginMeeting);
        textViewTimeEnd = findViewById(R.id.timeEndMeeting);
        editTextParticipants = findViewById(R.id.participantsMeeting);
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



    @RequiresApi(api = VERSION_CODES.O)
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

            }else if(editTextParticipants.getText().toString().equals("")) {
                ToastUtil.DisplayToastLong("Veuillez SVP renseigner les adresses mail des participants", context);
            }else {

                Meeting meeting = new Meeting(id , editTextSubject.getText().toString(),
                        LocalDateTime.of(localDate,localTimeBegin), LocalDateTime.of(localDate,localTimeEnd),
                        editTextParticipants.getText().toString(), RoomMeeting.getRoomMeetingById(positionRoom)
                );

                /* Gestion de la disponibilité des salles */
                boolean timeProblem = false;
                boolean reserved = false;
                for (Meeting m : DI.getMeetingApiService().getMeetings()) {
                    if (m.getMeetingRoom().getId().equals(positionRoom) &&
                            ((LocalDateTime.of(localDate, localTimeBegin).isBefore(m.getDateTimeEnd())
                                    && LocalDateTime.of(localDate, localTimeBegin).isAfter(m.getDateTimeBegin()))
                                    || (LocalDateTime.of(localDate, localTimeEnd).isBefore(m.getDateTimeEnd())
                                    && LocalDateTime.of(localDate, localTimeEnd).isAfter(m.getDateTimeBegin()))
                                    || LocalDateTime.of(localDate, localTimeBegin).isEqual(m.getDateTimeBegin())
                                    || LocalDateTime.of(localDate, localTimeEnd).isEqual(m.getDateTimeEnd())
                                    || m.getDateTimeBegin().isAfter(LocalDateTime.of(localDate, localTimeBegin))
                                    && m.getDateTimeBegin().isBefore(LocalDateTime.of(localDate, localTimeEnd))
                                    || m.getDateTimeEnd().isBefore(LocalDateTime.of(localDate, localTimeEnd))
                                    && m.getDateTimeEnd().isAfter(LocalDateTime.of(localDate, localTimeBegin)))) {
                        reserved = true;
                        break;
                    } else if (LocalDateTime.of(localDate, localTimeBegin).isAfter(LocalDateTime.of(localDate, localTimeEnd)) || LocalDateTime.of(localDate, localTimeBegin).isEqual(LocalDateTime.of(localDate, localTimeEnd))) {

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
                      if(monthOfYear<10){
                          textViewDate.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                      }else{
                          textViewDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                      }

                         if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
                             localDate =LocalDate.of(year,monthOfYear+1,dayOfMonth);
                         }
                     }, mYear, mMonth, mDay);
             datePickerDialog.show();
         });
    }

    public void userClickOnButtonForSelectTimeBegin(){
        buttonTimePickerBegin.setOnClickListener(v -> {

            getCurrentTime();

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {

                        if(minute == 0 && hourOfDay<10){
                            textViewTimeBegin.setText("0"+hourOfDay + ":" + minute+"0");
                        }else if(hourOfDay<10){
                            textViewTimeBegin.setText("0"+hourOfDay + ":" + minute);
                        }else if(minute ==0) {
                            textViewTimeBegin.setText(hourOfDay + ":" + minute+"0");
                        }else{
                            textViewTimeBegin.setText(hourOfDay + ":" + minute);
                        }

                        if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
                            localTimeBegin=LocalTime.of(hourOfDay,minute);
                        }

                    }, mHour, mMinute, true);
            timePickerDialog.show();
        });
    }

    public void userClickOnButtonForSelectTimeEnd(){
        buttonTimePickerEnd.setOnClickListener(v -> {

            getCurrentTime();

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> {

                        if(minute == 0 && hourOfDay<10){
                            textViewTimeEnd.setText("0"+hourOfDay + ":" + minute+"0");
                        }else if(hourOfDay<10){
                            textViewTimeEnd.setText("0"+hourOfDay + ":" + minute);
                        }else if(minute ==0) {
                            textViewTimeEnd.setText(hourOfDay + ":" + minute+"0");
                        }else{
                            textViewTimeEnd.setText(hourOfDay + ":" + minute);
                        }

                        if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
                            localTimeEnd = LocalTime.of(hourOfDay,minute);
                        }

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