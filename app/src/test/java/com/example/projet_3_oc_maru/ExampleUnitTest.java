package com.example.projet_3_oc_maru;

import com.example.projet_3_oc_maru.di.DI;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.service.DummyMeetingGenerator;
import com.example.projet_3_oc_maru.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings  = DummyMeetingGenerator.DUMMY_ROOM_MEETS;
        assertEquals(meetings,expectedMeetings);
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting meetingToAdd = DummyMeetingGenerator.DUMMY_ROOM_MEETS.get(0);
        service.createMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

}