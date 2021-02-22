package com.example.projet_3_oc_maru.Models;

import androidx.annotation.NonNull;

import com.example.projet_3_oc_maru.DI.DI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Meeting {

    private int id;
    private String subject;
    private LocalDate date;
    private LocalTime timeEnd;
    private String participants;
    private LocalTime timeBegin;
    private RoomMeeting meetingRoom;

    public Meeting(int id, String subject, LocalDate date,LocalTime timeBegin, LocalTime timeEnd , String participants, RoomMeeting meetingRoom){
        this.id=id;
        this.subject=subject;
        this.date=date;
        this.timeBegin=timeBegin;
        this.timeEnd=timeEnd;
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

    public LocalDate getDate() { return date; }

    public LocalTime getTimeEnd(){
        return timeEnd;
    }

    public String getParticipants() {
        return participants;
    }

    public RoomMeeting getMeetingRoom() { return meetingRoom;}

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

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

    public void setDate(LocalDate date) {this.date = date;}

    public void setTimeEnd(LocalTime timeEnd) { this.timeEnd = timeEnd; }

    public void setParticipants(String participants) { this.participants = participants; }

    public void setMeetingRoom(RoomMeeting meetingRoom) { this.meetingRoom = meetingRoom; }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }
}
