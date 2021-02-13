package com.example.projet_3_oc_maru.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.projet_3_oc_maru.DI.DI;
import com.example.projet_3_oc_maru.Fragments.MainFragment;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;
import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.events.DeleteMeetingEvent;
import com.google.android.material.textfield.TextInputLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {
    TextInputLayout idMeeting;
    TextInputLayout subjectMeeting;
    TextInputLayout timeBegin;
    TextInputLayout timeEnd;
    TextInputLayout participantsMeeting;
    private boolean statutForChange = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        idMeeting = findViewById(R.id.idMeetingLyt);
        subjectMeeting = findViewById(R.id.subjectMeetingLyt);
        timeBegin = findViewById(R.id.timeBeginMeetingLyt);
        timeEnd = findViewById(R.id.timeEndLyt);
        participantsMeeting = findViewById(R.id.participantsMeetingLyt);
        Button createNewRoomMeetingButton = findViewById(R.id.create);
        NumberPicker numberRoomMeeting = (NumberPicker) findViewById(R.id.numberRoomMeeting);

        numberRoomMeeting.setMinValue(1);
        numberRoomMeeting.setMaxValue(10);
        numberRoomMeeting.setWrapSelectorWheel(true);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        createNewRoomMeetingButton.setOnClickListener(v -> {
            Meeting meeting = new Meeting(
                    Objects.requireNonNull(idMeeting.getEditText()).getText().toString(),
                    Objects.requireNonNull(subjectMeeting.getEditText()).getText().toString(),
                    Objects.requireNonNull(timeBegin.getEditText()).getText().toString(),
                    Objects.requireNonNull(timeEnd.getEditText()).getText().toString(),
                    Objects.requireNonNull(participantsMeeting.getEditText()).getText().toString(),
                    RoomMeeting.getRoomMeetingById(numberRoomMeeting.getValue())
            );
            DI.getMeetingApiService().createMeeting(meeting);

            finish();


        });

    }



}