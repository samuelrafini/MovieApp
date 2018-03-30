package com.example.sammy.bioscoop;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Sammy on 3/29/18.
 */

public class Film {

    private String titel;
    private String overview;
    private int runtime;
    //leeftijd kijkwijzer
    private String certification;
    private String Director;
    private ArrayList<String> actor;

    public Film(String titel, String overview, int runtime, String certification, String director, ArrayList<String> actor) {
        this.titel = titel;
        this.overview = overview;
        this.runtime = runtime;
        this.certification = certification;
        Director = director;
        this.actor = actor;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public ArrayList<String> getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        this.actor = actor;
    }
}
