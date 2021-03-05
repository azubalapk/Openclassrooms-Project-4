package com.example.projet_3_oc_maru.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.projet_3_oc_maru.di.DI;

import java.time.LocalDateTime;

public class Meeting implements Parcelable {

    private int id;
    private String subject;
    private LocalDateTime dateTimeBegin;
    private LocalDateTime dateTimeEnd;
    private String participants;
    private RoomMeeting meetingRoom;
    /* boolean pour liste filtrée ou liste complète) */
    private boolean isMeetingInFilterList;

    public Meeting(int id, String subject,LocalDateTime dateTimeBegin, LocalDateTime dateTimeEnd , String participants, RoomMeeting meetingRoom){
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected Meeting(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        dateTimeBegin = (LocalDateTime) in.readSerializable();
        dateTimeEnd = (LocalDateTime) in.readSerializable();
        participants = in.readString();
        meetingRoom = in.readParcelable(RoomMeeting.class.getClassLoader());

    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
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



    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject);
        dest.writeSerializable(dateTimeBegin);
        dest.writeSerializable(dateTimeEnd);
        dest.writeString(participants);
        dest.writeParcelable(meetingRoom,flags);

    }
}
