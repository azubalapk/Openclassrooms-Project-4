package com.example.projet_3_oc_maru.service;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.projet_3_oc_maru.DI.DI;
import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;

import java.util.LinkedList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DummyMeetingApiService implements MeetingApiService {



    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();


    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }





    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
