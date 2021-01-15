package com.example.projet_3_oc_maru.Models;

import java.util.List;

public class RoomMeet {
    /** Identifier Letters*/
    private String id;

    /**Subject */
    private String subject;

    /**Hours of RoomMeet */
    private String hour;

    /**Avatar colors for RoomMeet */
    private String colorsAvatar;

    /**List of participants'emails */
    private List<String>ListEmails;

    public RoomMeet (String id,String subject,String hour ,String colorsAvatar,List<String>ListEmails){
        this.id=id;
        this.subject=subject;
        this.hour=hour;
        this.colorsAvatar=colorsAvatar;
        this.ListEmails=ListEmails;
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

    public List<String> getListEmails() {
        return ListEmails;
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

    public void setListEmails(List<String> listEmails) {
        ListEmails = listEmails;
    }
}
