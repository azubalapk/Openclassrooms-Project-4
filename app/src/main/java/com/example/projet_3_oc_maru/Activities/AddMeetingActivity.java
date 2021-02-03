package com.example.projet_3_oc_maru.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projet_3_oc_maru.DI.DI;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.R;
import com.example.projet_3_oc_maru.service.MeetingApiService;
import com.google.android.material.textfield.TextInputLayout;

public class AddMeetingActivity extends AppCompatActivity {
    TextInputLayout idMeeting;
    TextInputLayout subjectMeeting;
    TextInputLayout timeBegin;
    TextInputLayout timeEnd;
    TextInputLayout participantsMeeting;
    TextInputLayout roomMeeting;
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
        roomMeeting=findViewById(R.id.RoomMeetingLyt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getMeetingApiService();
    }



}