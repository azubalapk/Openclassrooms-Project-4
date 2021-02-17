package com.example.projet_3_oc_maru.service;

import com.example.projet_3_oc_maru.Models.Meeting;

import java.util.List;

/**
 * Meeting API client
 */
public interface MeetingApiService {
    /**
     * Get all my RoomMeets
     * @return {@link List}
     * */
    List<Meeting> getMeetings();



    /**
     * Deletes a meeting
     * @param meeting
     * */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

}
