package com.example.sammy.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;


public class ReservationActivity extends AppCompatActivity{

    private static final String TAG = "ReservationActivity";
    private FilmDatabase filmDatabase;
    private ArrayList<Show> showArrayList;
    private Show show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        getIntent().getSerializableExtra("MyFilmTitle");
        final Film film = (Film) getIntent().getSerializableExtra("MyFilmTitle");
        System.out.println(film.getTitel());

        ImageView image = findViewById(R.id.backdropImg);
        Picasso.with(this).load(film.getBackDropPath()).into(image);

        TextView filmTitle = findViewById(R.id.titleFilmReservation);
        filmTitle.setText(film.getTitel());

        filmDatabase = new FilmDatabase(this);

        filmDatabase.insertDataToShow(film.getTitel(), "10:45", "07-04-18");
        filmDatabase.insertDataToShow(film.getTitel(), "13:45", "12-04-18");
        filmDatabase.insertDataToShow(film.getTitel(), "21:50", "18-04-18");

        showArrayList = filmDatabase.getShows(film.getTitel());


        for(Show show: showArrayList){
            System.out.println(show.getDate());
        }


        String buttonDate = "Datum: " + showArrayList.get(0).getDate() + " Tijd: " + showArrayList.get(0).getTime();
        Button button = (Button)findViewById(R.id.showDate0);
        final int showId = showArrayList.get(0).getShow_id();
        button.setText(buttonDate);

        String buttonDate1 = "Datum: " + showArrayList.get(1).getDate() + " Tijd: " + showArrayList.get(1).getTime();
        Button button1 = (Button)findViewById(R.id.showDate1);
        final int showId1 = showArrayList.get(1).getShow_id();
        button1.setText(buttonDate1);

        String buttonDate2 = "Datum: " + showArrayList.get(2).getDate() + " Tijd: " + showArrayList.get(2).getTime();
        Button button2 = (Button)findViewById(R.id.showDate2);
        final int showId2 = showArrayList.get(2).getShow_id();
        button2.setText(buttonDate2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), SeatSelectionActivity.class);

                itemDetailIntent.putExtra("MyFilmTitle", (Serializable) film);
                itemDetailIntent.putExtra("ShowID", showId);


                Log.d(TAG, "onClick: passing show id to intent" + showArrayList.get(0).getShow_id());

                view.getContext().startActivity(itemDetailIntent);
            }


        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), SeatSelectionActivity.class);

                itemDetailIntent.putExtra("MyFilmTitle", (Serializable) film);
                itemDetailIntent.putExtra("ShowID", showId1);


                Log.d(TAG, "onClick: passing show id to intent" + showArrayList.get(1).getShow_id());

                view.getContext().startActivity(itemDetailIntent);
            }


        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), SeatSelectionActivity.class);

                itemDetailIntent.putExtra("MyFilmTitle", (Serializable) film);
                itemDetailIntent.putExtra("ShowID", showId2);


                Log.d(TAG, "onClick: passing show id to intent" + showArrayList.get(2).getShow_id());

                view.getContext().startActivity(itemDetailIntent);
            }


        });


    }

}
