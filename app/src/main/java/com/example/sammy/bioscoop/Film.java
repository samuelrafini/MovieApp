package com.example.sammy.bioscoop;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Sammy on 3/29/18.
 */

public class Film implements Serializable{

    //New checken film heeft meerdere voorsytelling
    private String titel;
    //img
    private String posterPath;
    private String backDropPath;
    private String overview;
    private int runtime;
    //leeftijd kijkwijzer
    private String certification;
    private String genres;
    private String director;
    private String actor;

    public Film() {

    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getPosterPath() {
        return posterPath;
    }


    public void setPosterPath(String posterPath) {

        this.posterPath = "https://image.tmdb.org/t/p/w300" +
        posterPath;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = "https://image.tmdb.org/t/p/w300" + backDropPath;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {

        String genreList = "";

        for (int i = 0; i < genres.size(); i++) {


            genreList += genres.get(i);
            if ((genres.size() - i) > 1){
                genreList += " | ";
            }
            this.genres = "Genre: " + genreList;
        }
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

        if(certification.equals("")){
            certification = "--";
        }
        this.certification = certification;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {

        this.director = "Director: " + director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        String actorList = "";

        for (String s: actor) {

            actorList += s + " | ";
            this.actor = actorList;

        }

    }




    @Override
    public String toString() {
        return titel + " " + genres + " " + director + " " + actor;
    }
}
