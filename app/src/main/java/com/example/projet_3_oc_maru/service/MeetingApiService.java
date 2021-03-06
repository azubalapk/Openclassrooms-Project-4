package com.example.projet_3_oc_maru.service;

import com.example.projet_3_oc_maru.models.Meeting;


import org.joda.time.LocalDate;

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
    List<Meeting> getRes();

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

    /**
     * Get all Meetings in order of Rooms (liste filtrée)
     */
    List<Meeting> getMeetingsFilterRoom(String salle);

    List<Meeting> getMeetingsFilterDate(LocalDate date);

    void resetFilter();
}
