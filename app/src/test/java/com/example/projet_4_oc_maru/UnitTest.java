package com.example.projet_4_oc_maru;

import com.example.projet_4_oc_maru.di.DI;
import com.example.projet_4_oc_maru.models.Meeting;
import com.example.projet_4_oc_maru.service.DummyMeetingGenerator;
import com.example.projet_4_oc_maru.service.MeetingApiService;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_ROOM_MEETS;
        assertEquals(meetings, expectedMeetings);
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting meetingToAdd = DummyMeetingGenerator.DUMMY_ROOM_MEETS.get(0);
        service.deleteMeeting(meetingToAdd);
        service.createMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void getMeetingsFilterRoom() {
        String expectedMeetings = service.getMeetings().get(0).getMeetingRoom().getNameRoomMeeting();
        assertEquals(service.getMeetingsFilterRoom("Peach").get(0).getMeetingRoom().getNameRoomMeeting(), expectedMeetings);
    }

    @Test
    public void getMeetingFilterDate() {
        DateTime expectedMeetings = service.getMeetings().get(0).getDateTimeBegin();
        assertEquals(service.getMeetingsFilterDate(new LocalDate(2021, 02, 14)).get(0).getDateTimeBegin(), expectedMeetings);
    }

    @Test
    public void theRoomIsNotAvailable() {
        assertEquals(true, service.theRoomIsAvailableOrNotAvailable(
                new DateTime(2021, 02, 14, 13, 30),
                new DateTime(2021, 02, 14, 15, 30),
                7)
        );
        assertEquals(true, service.theRoomIsAvailableOrNotAvailable(
                new DateTime(2021, 02, 14, 14, 30),
                new DateTime(2021, 02, 14, 14, 45),
                7)
        );
        assertEquals(true, service.theRoomIsAvailableOrNotAvailable(
                new DateTime(2021, 02, 14, 12, 30),
                new DateTime(2021, 02, 14, 14, 30),
                7)
        );
        assertEquals(true, service.theRoomIsAvailableOrNotAvailable(
                new DateTime(2021, 02, 14, 14, 30),
                new DateTime(2021, 02, 14, 15, 30),
                7)
        );
        assertEquals(true, service.theRoomIsAvailableOrNotAvailable(
                new DateTime(2021, 02, 14, 14, 00),
                new DateTime(2021, 02, 14, 15, 00),
                7)
        );
    }

}