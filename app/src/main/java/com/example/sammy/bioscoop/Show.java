package com.example.sammy.bioscoop;

/**
 * Created by Pranawa Somvedi on 4/3/2018.
 */

public class Show {

    private int show_id;
    private String time;
    private String date;
    private int room_id;

    public Show(int show_id, String time, String date) {
        this.show_id = show_id;
        this.time = time;
        this.date = date;
//        this.room_id = room_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getTime() {

        return time;
    }

    public String getDate() {
        return date;
    }

    public int getRoom_id() {
        return room_id;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }
}