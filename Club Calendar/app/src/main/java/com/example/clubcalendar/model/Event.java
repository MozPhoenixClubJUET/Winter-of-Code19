package com.example.clubcalendar.model;

public class Event {
    private String eventname;
    private String date;
    private String description;
    private String venue;
    private String clubName;

    public Event() {
    }

    public Event(String eventname, String date, String description, String venue, String clubName) {
        this.eventname = eventname;
        this.date = date;
        this.description = description;
        this.venue = venue;
        this.clubName = clubName;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
