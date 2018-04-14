package com.example.sammy.bioscoop;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sammy on 3/29/18.
 */

public class NowPlayingFilmID implements FilmIDAvailable {
    private final String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=9356cb1f42f053e63a72c6bf6ca12171&language=en-US&page=1";
//    private final String url = "https://api.themoviedb.org/3/movie/now_playing?region=NL&page=1&language=en-US&api_key=9356cb1f42f053e63a72c6bf6ca12171";


    private ArrayList<Integer> moviesID;

    private static final String TAG = "NowPlayingFilmID";
    private FilmIDCallback listener = null;


    public NowPlayingFilmID(FilmIDCallback listener){
        this.listener = listener;
        moviesID = new ArrayList<>();
        fetchMoviesID();
    }

    public void fetchMoviesID(){
        Log.d(TAG, "fetchMoviesID: is called... fetching movies");

        FilmIDTask task = new FilmIDTask(this);
        String[] urls = new String[] {url};
        task.execute(urls);
    }

    public ArrayList<Integer> getMoviesIDs(){
        Log.d(TAG, "getMoviesIDs: is called");
        
        return moviesID;
    }

    public void addMoviesID(int id){
        Log.d(TAG, "addMoviesID: is called. Add moviesID" + id);
        this.moviesID.add(id);
    }

    public void callFilmID(){

        listener.filmIDCallback(getMoviesIDs());
    }



}
