package com.example.sammy.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sammy on 3/29/18.
 */

public class SeatSelectionActivity extends AppCompatActivity {

    private static final int ROW = 15;
    private static final int COL = 15;
    private ArrayList<Seat> seats;
    private ArrayList<String> seatIds = new ArrayList<>();
    private FilmDatabase filmDatabase;
    private Room room;
    private static final String TAG = "SeatSelectionActivity";
    int showId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        filmDatabase = new FilmDatabase(this);
        getIntent().getSerializableExtra("MyFilmTitle");
        final Film film = (Film) getIntent().getSerializableExtra("MyFilmTitle");
        showId = getIntent().getIntExtra("ShowID", 0);

        System.out.println(film.getTitel());

        createTable();

        Button button = (Button) findViewById(R.id.reserveerButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seats = room.getSeats();

                Log.d(TAG, "onClick: how big is array?------ " + seats.size());
                for (int i = 0; i < seats.size(); i++){
////
//                    System.out.println(i);
//                    Log.d(TAG, "onClick: -----------insert into data reservation seat " + showId + " " + seats.get(i).getSeatID());
//                    filmDatabase.insertDataToReservation(showId, seats.get(i).getSeatID());
                    seatIds.add(seats.get(i).getSeatID());
                    Log.d(TAG, "onClick: seats ids added" + seatIds.get(i));

                }

                if (seats.size() == 0) {

                    Toast.makeText(view.getContext(), "No seat selected", Toast.LENGTH_SHORT).show();

                } else {

                    Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), ConfirmationActivity.class);

                    itemDetailIntent.putExtra("MyFilmNameConfirmation", (Serializable) film);
                    itemDetailIntent.putExtra("ShowIDConfirmation", showId);
                    itemDetailIntent.putStringArrayListExtra("seatIDList", seatIds);
                    itemDetailIntent.putExtra("TotalPrice", Double.toString(room.calculate(seats)));



                    view.getContext().startActivity(itemDetailIntent);
                }
            }
        });





    }
    private void createTable() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.TableSeats);
        final TextView titleTextView = (TextView) findViewById(R.id.textView);

        room = new Room(this,tableLayout, titleTextView, ROW, COL, filmDatabase.getSeats(showId));
//
    }

}
