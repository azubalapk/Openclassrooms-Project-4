package com.example.projet_3_oc_maru.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

public class Meeting implements Parcelable {

    private int id;
    private String subject;
    private DateTime dateTimeBegin;
    private DateTime dateTimeEnd;
    private List<String> participants;
    private RoomMeeting meetingRoom;
    /* boolean pour liste filtrée ou liste complète) */
    private boolean isMeetingInFilterList;

    public Meeting(int id, String subject, DateTime dateTimeBegin, DateTime dateTimeEnd , List<String> participants, RoomMeeting meetingRoom){
        this.id=id;
        this.subject=subject;
        this.dateTimeBegin=dateTimeBegin;
        this.dateTimeEnd=dateTimeEnd;
        this.participants=participants;
        this.meetingRoom=meetingRoom;
        this.isMeetingInFilterList = false;

    }
    public boolean isMeetingInFilterList() {
        return isMeetingInFilterList;
    }

    public void setMeetingInFilterList(boolean isMeetingInFilterList) {
        this.isMeetingInFilterList = isMeetingInFilterList;
    }


    protected Meeting(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        dateTimeBegin = (DateTime) in.readSerializable();
        dateTimeEnd = (DateTime) in.readSerializable();
        participants = (List<String>) in.readSerializable();
        meetingRoom = in.readParcelable(RoomMeeting.class.getClassLoader());
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    /**Getters */
    public int getId(){
        return id;
    }

    public String getSubject(){
        return subject;
    }

    public DateTime getDateTimeBegin() {
        return dateTimeBegin;
    }

    public DateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public RoomMeeting getMeetingRoom() { return meetingRoom;}

    /**Setters */
    public void setId(int id) { this.id = id; }

    public void setSubject(String subject) { this.subject = subject; }

    public void setDateTimeBegin(DateTime dateTimeBegin) {
        this.dateTimeBegin = dateTimeBegin;
    }

    public void setDateTimeEnd(DateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public void setParticipants(List<String> participants) { this.participants = participants; }

    public void setMeetingRoom(RoomMeeting meetingRoom) { this.meetingRoom = meetingRoom; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject);
        dest.writeSerializable(dateTimeBegin);
        dest.writeSerializable(dateTimeEnd);
        dest.writeSerializable((Serializable) participants);
        dest.writeParcelable(meetingRoom,flags);
    }
}
