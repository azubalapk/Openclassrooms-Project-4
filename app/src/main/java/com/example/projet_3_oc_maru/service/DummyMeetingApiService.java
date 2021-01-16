package com.example.projet_3_oc_maru.service;

import com.example.projet_3_oc_maru.Models.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateRoomMeets();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void deleteRoomMeet(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createRoomMeet(Meeting meeting) {
        meetings.add(meeting);
    }
}
