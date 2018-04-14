package com.example.sammy.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by Sammy on 3/31/18.
 */

public class FilmDetailActivity extends AppCompatActivity {
    private static final String TAG = "FilmDetailActivity";
    private Film filmIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "On create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        Log.d(TAG,"onCreate: Started.");



        getIntent().getSerializableExtra("MyClass");

        intentGetter();

        Button button = (Button) findViewById(R.id.seatselectionButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), ReservationActivity.class);

                itemDetailIntent.putExtra("MyFilmTitle", (Serializable) filmIntent);


//                itemDetailIntent.putExtra("imageURL", listItem.getImageURL());
//                itemDetailIntent.putExtra("cameraName", listItem.getCameraName());

                view.getContext().startActivity(itemDetailIntent);
            }
        });


    }



    private void intentGetter(){
        Log.d(TAG, "Get Intent");

        Film film = (Film) getIntent().getSerializableExtra("MyClass");
        setFilmInfo(film);
        filmIntent = film;
    }

    private void setFilmInfo(Film film){
        Log.d(TAG, "Set image url and camera name");

        ImageView image = findViewById(R.id.imgViewDetailActivity);
        Picasso.with(this).load(film.getPosterPath()).into(image);

        TextView overview = findViewById(R.id.textOverview);
        overview.setText(film.getOverview());

        TextView title = findViewById(R.id.titleDetailText);
        title.setText(film.getTitel());

        TextView genre = findViewById(R.id.genreDetailText);
        genre.setText(film.getGenres());

        TextView actors = findViewById(R.id.actorsDetailText);
        actors.setText(film.getActor());

        TextView director = findViewById(R.id.directorDetailText);
        director.setText(film.getDirector());

    }
}
