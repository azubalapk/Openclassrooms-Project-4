package com.example.projet_3_oc_maru.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.projet_3_oc_maru.models.Meeting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> res = new ArrayList<>();

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        resetFilter();
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

    @Override
    public List<Meeting> getMeetingsFilterRoom(String salle) {

        resetFilter();

        for (Meeting m : meetings) {
            if (m.getMeetingRoom().getmNameRoomMeeting().equals(salle)) {
                m.setMeetingInFilterList(true);
                res.add(m);
            }
        }

        return res;
    }

    @Override
    public List<Meeting> getMeetingsFilterDate(LocalDate date) {

        resetFilter();

        for (Meeting m : meetings) {
            if (m.getDateTimeBegin().toLocalDate().equals(date)) {
                m.setMeetingInFilterList(true);
                res.add(m);
            }
        }
        return res;
    }


    @Override
    public void resetFilter() {
        for (Meeting m : meetings) {
            res.clear();

            m.setMeetingInFilterList(false);
        }
    }

}
