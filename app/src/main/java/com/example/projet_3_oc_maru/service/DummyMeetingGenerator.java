package com.example.projet_3_oc_maru.service;

import com.example.projet_3_oc_maru.Models.Meeting;
import com.example.projet_3_oc_maru.Models.RoomMeeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_ROOM_MEETS = Arrays.asList(
            new Meeting("1","Logistique","14h00","15h00" ,"maxime@lamzone.com,alex@lamzone.com",RoomMeeting.getRoomMeetingById(7)),
            new Meeting("2","Qualité","16h00","17h00","paul@lamzone.com,viviane@lamzone.com",RoomMeeting.getRoomMeetingById(6)),
            new Meeting("3","Reception","19h00","20h00","amandine@lamzone.com,luc@lamzone.com",RoomMeeting.getRoomMeetingById(5))

    );

    static List<Meeting> generateRoomMeets() {
        return new ArrayList<>(DUMMY_ROOM_MEETS);
    }

}
