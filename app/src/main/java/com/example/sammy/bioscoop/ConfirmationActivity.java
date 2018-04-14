package com.example.sammy.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;


public class ConfirmationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "ConfirmationActivity";

    int showId;
    ArrayList<String> seats;
    String allIDs = "";
    private Show show;
    private int countTicket;
    private String price;
    private FilmDatabase db;
    private Ticket ticket;
    String[] paymentMethod ={"iDeal","PayPal","Credit Card"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);



        final Film film = (Film) getIntent().getSerializableExtra("MyFilmNameConfirmation");
        showId = getIntent().getIntExtra("ShowIDConfirmation", 0);
        seats = getIntent().getExtras().getStringArrayList("seatIDList");
        price = getIntent().getStringExtra("TotalPrice");
        db = new FilmDatabase(this);
        countTicket = seats.size();

        Spinner spinner = (Spinner) findViewById(R.id.paymentMethods);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,paymentMethod);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);





        Log.d(TAG, "onCreate: ---------- Data from intent SeatSelection-------- " + film.getTitel() + " " + showId + seats);

        TextView filmTitle = findViewById(R.id.titleFilmConfirmation);
        filmTitle.setText(film.getTitel());

        for (String id: seats){

            String sampleString = id;
            String[] stringArray = sampleString.split(",");
            int[] intArray = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                String numberAsString = stringArray[i];
                intArray[i] = Integer.parseInt(numberAsString);
            }

            Log.d(TAG, "onCreate: Rij: " + intArray[0] + " Num: " + intArray[1]);
            allIDs += "Rij: " + (intArray[0] + 1 )+ " Num: " + (intArray[1] + 1) + "\n";
        }

        TextView textView = (TextView) findViewById(R.id.giveSeats);
        textView.setText(allIDs);

        TextView textViewCountTickets = (TextView) findViewById(R.id.ticketAmount);
        textViewCountTickets.setText("Aantal Tickets: " + countTicket);

        TextView textViewPrice = (TextView) findViewById(R.id.price);
        textViewPrice.setText(" " + price);

        Show show = db.getDateTime(showId);

        Log.d(TAG,"onCreate: ----------- Data from intent SeatSelection-------- " + show.getDate() + " " + show.getTime());

        TextView textViewDate = (TextView) findViewById(R.id.dateConfirmation);
        textViewDate.setText("Datum: " + show.getDate() + " Tijd " + show.getTime());


        ticket = new Ticket(allIDs, show.getDate(), show.getTime(), film.getTitel());
        Log.d(TAG, "onCreate: ticket print " + ticket.getFilmTitle());




        Button button = (Button) findViewById(R.id.betalen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.insertDataToTicket(ticket.getSeatInfo(), ticket.getDate(), ticket.getTime(), ticket.getFilmTitle());

                Log.d(TAG, "onClick: how big is array?------ " + seats.size());
                for (int i = 0; i < seats.size(); i++){
////
//                    System.out.println(i);
                    Log.d(TAG, "onClick: -----------insert into data reservation seat " + showId + " " + seats.get(i));
                    db.insertDataToReservation(showId, seats.get(i));
//                    seatIds.add(seats.get(i).getSeatID());
                    Log.d(TAG, "onClick: seats ids added" + seats.get(i));

                }


                    Intent itemDetailIntent = new Intent(view.getContext().getApplicationContext(), ThankYouActivity.class);

                    view.getContext().startActivity(itemDetailIntent);
                }
            }
        );



        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backToSeatsIntent = new Intent(view.getContext().getApplicationContext(),SeatSelectionActivity.class);
                backToSeatsIntent.putExtra("MyFilmTitle", (Serializable) film );
                backToSeatsIntent.putExtra("ShowID", showId);

                view.getContext().startActivity(backToSeatsIntent);

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), paymentMethod[i], Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
