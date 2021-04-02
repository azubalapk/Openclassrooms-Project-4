package com.example.projet_3_oc_maru.service;





import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.models.RoomMeeting;
import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_ROOM_MEETS = Arrays.asList(
            new Meeting(1,"Logistique", new DateTime(2021,02,14,14,00),new DateTime(2021,02,14,15,00) ,Arrays.asList("maxime@lamzone.com","alex@lamzone.com"),RoomMeeting.getRoomMeetingById(7)),
            new Meeting(2,"Qualité",new DateTime(2021,02,15,15,00),new DateTime(2021,02,15,16,00),Arrays.asList("paul@lamzone.com","viviane@lamzone.com"),RoomMeeting.getRoomMeetingById(6)),
            new Meeting(3,"Reception",new DateTime(2021,02,16,19,00),new DateTime(2021,02,16,20,00),Arrays.asList("amandine@lamzone.com","luc@lamzone.com"),RoomMeeting.getRoomMeetingById(5))
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_ROOM_MEETS);
    }

}
