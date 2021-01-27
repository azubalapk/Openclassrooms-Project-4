package com.example.projet_3_oc_maru.events;


import com.example.projet_3_oc_maru.Models.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;


    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }


}
