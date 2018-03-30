package com.example.sammy.bioscoop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FilmIDCallback {
    private static final String TAG = "MainActivity";
    private ArrayList<Integer> allID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NowPlayingFilmID nowPlayingFilmID = new NowPlayingFilmID(this);

        System.out.println("check check mofo");


    }

    @Override
    public void filmIDCallback(ArrayList<Integer> moviesID) {
        Log.d(TAG, "filmIDCallback: all movies ids" + moviesID);
        allID = moviesID;
    }
}

