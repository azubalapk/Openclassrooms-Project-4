package com.example.projet_3_oc_maru.service;

import com.example.projet_3_oc_maru.Models.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_ROOM_MEETS = Arrays.asList(
            new Meeting("A","Peach","14h00","F096B5","maxime@lamzone.com,alex@lamzone.com",1),
            new Meeting("B","Mario","16h00","B7DEAC","paul@lamzone.com,viviane@lamzone.com",2),
            new Meeting("C","Luigi","19h00","B7DEAC","amandine@lamzone.com,luc@lamzone.com",3)

    );

    static List<Meeting> generateRoomMeets() {
        return new ArrayList<>(DUMMY_ROOM_MEETS);
    }

}
