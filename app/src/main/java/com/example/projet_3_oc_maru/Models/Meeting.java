package com.example.projet_3_oc_maru.Models;

import java.util.List;

public class Meeting {
    /** Identifier Letters*/
    private String id;

    /**Subject */
    private String subject;

    /**Hours of Meeting */
    private String hour;

    /**Avatar colors for Meeting */
    private String colorsAvatar;

    /**Place for Meeting */
    private Integer numberRoom;

    /**List of participants'emails */
    private String ListEmails;

    public Meeting(String id, String subject, String hour , String colorsAvatar, String ListEmails, Integer numberRoom){
        this.id=id;
        this.subject=subject;
        this.hour=hour;
        this.colorsAvatar=colorsAvatar;
        this.ListEmails=ListEmails;
        this.numberRoom=numberRoom;
    }

    /**Getters */
    public String getId(){
        return id;
    }

    public String getSubject(){
        return subject;
    }

    public String getHour(){
        return hour;
    }

    public String getColorsAvatar(){
        return colorsAvatar;
    }

    public String getListEmails() {
        return ListEmails;
    }

    public Integer getNumberRoom() {
        return numberRoom;
    }

    /**Setters */
    public void setId(String id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setColorsAvatar(String colorsAvatar) {
        this.colorsAvatar = colorsAvatar;
    }

    public void setListEmails(String listEmails) {
        ListEmails = listEmails;
    }

    public void setNumberRoom(Integer numberRoom) {
        this.numberRoom = numberRoom;
    }
}
