package com.example.projet_3_oc_maru.Models;

import androidx.annotation.NonNull;

public class RoomMeeting {
    private String mNameRoomMeeting;
    private int mRoomMeetingColor;
    private final  Integer id;


    public RoomMeeting(Integer id ,String mNameRoomMeeting, int mRoomMeetingColor) {
        this.id = id;
        this.mNameRoomMeeting = mNameRoomMeeting;
        this.mRoomMeetingColor = mRoomMeetingColor;
    }

    public String getmNameRoomMeeting() {
        return mNameRoomMeeting;
    }


    public int getmRoomMeetingColor() {
        return mRoomMeetingColor;
    }

    @NonNull
    public static RoomMeeting getRoomMeetingById(Integer id) {
        for (RoomMeeting roomMeeting : getAllRoomMeetings()) {
            if (roomMeeting.id == id)
                return roomMeeting;
        }
        return null;
    }

    @NonNull
    public static RoomMeeting[] getAllRoomMeetings() {
        return new RoomMeeting[]{
                new RoomMeeting(1, "Birdo",0xFF6D726F),
                new RoomMeeting(2, "Daisy",0xFF5D210C),
                new RoomMeeting(3, "Donkey Kong",0xFFDF3CB8),
                new RoomMeeting(4, "Harmonie",0xFF813CDF),
                new RoomMeeting(5, "Luigi",0xFF1527DD),
                new RoomMeeting(6, "Mario",0xFF3CBCDF),
                new RoomMeeting(7, "Peach",0xFF23CAC3),
                new RoomMeeting(8, "Toad",0xFF15DD42),
                new RoomMeeting(9, "Wario",0xFFDD2415),
                new RoomMeeting(10, "Yoshi",0xFFF1F114),
        };
    }

}
