package com.example.projet_3_oc_maru.service;



import com.example.projet_3_oc_maru.di.DI;
import com.example.projet_3_oc_maru.models.Meeting;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

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
            if (m.getMeetingRoom().getNameRoomMeeting().equals(salle)) {
                m.setMeetingInFilterList(true);
            }
        }

        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsFilterDate(LocalDate date) {

        resetFilter();

        for (Meeting m : meetings) {
            if (m.getDateTimeBegin().toLocalDate().equals(date)) {
                m.setMeetingInFilterList(true);
            }
        }
        return meetings;
    }

    @Override
    public void resetFilter() {
        for (Meeting m : meetings) {
            m.setMeetingInFilterList(false);
        }
    }

    public Boolean theRoomIsAvailableOrNotAvailable(DateTime finalDateTimeBegin , DateTime finalDateTimeEnd, Integer positionRoom){
        Boolean reserved = false;
        for (Meeting m : DI.getMeetingApiService().getMeetings()) {
            if ((m.getMeetingRoom().getId().equals(positionRoom) && finalDateTimeBegin.isBefore(m.getDateTimeEnd()) && finalDateTimeBegin.isAfter(m.getDateTimeBegin()))
                    || (m.getMeetingRoom().getId().equals(positionRoom) && finalDateTimeEnd.isBefore(m.getDateTimeEnd()) && finalDateTimeEnd.isAfter(m.getDateTimeBegin()))
                    || (m.getMeetingRoom().getId().equals(positionRoom) && finalDateTimeBegin.isEqual(m.getDateTimeBegin()))
                    || (m.getMeetingRoom().getId().equals(positionRoom) &&finalDateTimeEnd.isEqual(m.getDateTimeEnd()))
                    || (m.getMeetingRoom().getId().equals(positionRoom) &&m.getDateTimeBegin().isAfter(finalDateTimeBegin) && m.getDateTimeBegin().isBefore(finalDateTimeEnd))
                    || (m.getMeetingRoom().getId().equals(positionRoom) &&m.getDateTimeEnd().isBefore(finalDateTimeEnd) && m.getDateTimeEnd().isAfter(finalDateTimeBegin))
            )
            {
                 reserved = true;
                break;
            }
        }

        return reserved;
    }

}
