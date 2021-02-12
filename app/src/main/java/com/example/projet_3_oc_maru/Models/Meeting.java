package com.example.projet_3_oc_maru.Models;

public class Meeting {

    private String id;
    private String subject;
    private String timeBegin;
    private String timeEnd;
    private String participants;
    private RoomMeeting meetingRoom;

    public Meeting(String id, String subject, String timeBegin, String timeEnd , String participants, RoomMeeting meetingRoom){
        this.id=id;
        this.subject=subject;
        this.timeBegin=timeBegin;
        this.timeEnd=timeEnd;
        this.participants=participants;
        this.meetingRoom=meetingRoom;

    }



    /**Getters */
    public String getId(){
        return id;
    }

    public String getSubject(){
        return subject;
    }

    public String getTimeBegin(){
        return timeBegin;
    }

    public String getTimeEnd(){
        return timeEnd;
    }

    public String getParticipants() {
        return participants;
    }

    public RoomMeeting getMeetingRoom() { return meetingRoom;}



        /**Setters */
    public void setId(String id) { this.id = id; }

    public void setSubject(String subject) { this.subject = subject; }

    public void setTimeBegin(String timeBegin) { this.timeBegin = timeBegin; }

    public void setTimeEnd(String timeEnd) { this.timeEnd = timeEnd; }

    public void setParticipants(String participants) { this.participants = participants; }

    public void setMeetingRoom(RoomMeeting meetingRoom) { this.meetingRoom = meetingRoom; }


}
