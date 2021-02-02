package com.example.projet_3_oc_maru.Models;

public class RoomMeeting {
    private String mNameRoomMeeting;
    private int mRoomMeetingColor;


    public RoomMeeting(String mNameRoomMeeting, int mRoomMeetingColor) {
        this.mNameRoomMeeting = mNameRoomMeeting;
        this.mRoomMeetingColor = mRoomMeetingColor;
    }

    public String getmNameRoomMeeting() {
        return mNameRoomMeeting;
    }


    public int getmRoomMeetingColor() {
        return mRoomMeetingColor;
    }

}
