package com.example.projet_3_oc_maru.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.projet_3_oc_maru.DI.DI;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;
import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.service.MeetingApiService;
import com.google.android.material.textfield.TextInputLayout;

public class AddMeetingActivity extends AppCompatActivity {
    TextInputLayout idMeeting;
    TextInputLayout subjectMeeting;
    TextInputLayout timeBegin;
    TextInputLayout timeEnd;
    TextInputLayout participantsMeeting;
    private Integer idOfRoomMeet;
    private Button createNewRoomMeetingButton;


    private MeetingApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        idMeeting = findViewById(R.id.idMeetingLyt);
        subjectMeeting = findViewById(R.id.subjectMeetingLyt);
        timeBegin = findViewById(R.id.timeBeginMeetingLyt);
        timeEnd = findViewById(R.id.timeEndLyt);
        participantsMeeting = findViewById(R.id.participantsMeetingLyt);
        createNewRoomMeetingButton = findViewById(R.id.create);


        NumberPicker numberRoomMeeting = (NumberPicker) findViewById(R.id.numberRoomMeeting);
        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        numberRoomMeeting.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        numberRoomMeeting.setMaxValue(10);


        //Gets whether the selector wheel wraps when reaching the min/max value.
        numberRoomMeeting.setWrapSelectorWheel(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getMeetingApiService();
        idOfRoomMeet=numberRoomMeeting.getValue();

        createNewRoomMeetingButton.setOnClickListener((View.OnClickListener) v -> {
            addMeeting();

        });
    }

    void addMeeting() {

        Meeting meeting = new Meeting(
                idMeeting.getEditText().getText().toString(),
                subjectMeeting.getEditText().getText().toString(),
                timeBegin.getEditText().getText().toString(),
                timeEnd.getEditText().getText().toString(),
                participantsMeeting.getEditText().getText().toString(),
                RoomMeeting.getRoomMeetingById(idOfRoomMeet)

        );
        mApiService.createRoomMeet(meeting);
        finish();
    }

}