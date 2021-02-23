package com.example.projet_3_oc_maru.Models;

import androidx.annotation.NonNull;

import com.example.projet_3_oc_maru.DI.DI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

public class Meeting {

    private int id;
    private String subject;
    private LocalDateTime dateTimeBegin;
    private LocalDateTime dateTimeEnd;
    private String participants;
    private RoomMeeting meetingRoom;

    public Meeting(int id, String subject,LocalDateTime dateTimeBegin, LocalDateTime dateTimeEnd , String participants, RoomMeeting meetingRoom){
        this.id=id;
        this.subject=subject;
        this.dateTimeBegin=dateTimeBegin;
        this.dateTimeEnd=dateTimeEnd;
        this.participants=participants;
        this.meetingRoom=meetingRoom;

    }





    /**Getters */
    public int getId(){
        return id;
    }

    public String getSubject(){
        return subject;
    }

    public LocalDateTime getDateTimeBegin() {
        return dateTimeBegin;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public String getParticipants() {
        return participants;
    }

    public RoomMeeting getMeetingRoom() { return meetingRoom;}



    @NonNull
    public static Meeting getMeetingById(int id) {
        for (Meeting meeting : DI.getMeetingApiService().getMeetings()) {
            if (meeting.id == id)
                return meeting;
        }
        return null;
    }

    /**Setters */
    public void setId(int id) { this.id = id; }

    public void setSubject(String subject) { this.subject = subject; }

    public void setDateTimeBegin(LocalDateTime dateTimeBegin) {
        this.dateTimeBegin = dateTimeBegin;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public void setParticipants(String participants) { this.participants = participants; }

    public void setMeetingRoom(RoomMeeting meetingRoom) { this.meetingRoom = meetingRoom; }


}
