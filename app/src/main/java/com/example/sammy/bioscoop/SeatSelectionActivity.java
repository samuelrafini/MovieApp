package com.example.sammy.bioscoop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sammy on 3/29/18.
 */

public class SeatSelectionActivity extends AppCompatActivity {

    private static final int ROW = 15;
    private static final int COL = 15;
    private ArrayList<Seat> seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);


        createTable();
    }

    private void createTable() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.TableSeats);
        final TextView titleTextView = (TextView)findViewById(R.id.textView);

        seats = new ArrayList<>();

        for (int i = 0; i < ROW; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT, 1.0f
            ));
            tableLayout.addView(tableRow);
            for (int j = 0; j < COL; j++){
                final int FINAL_ROW = i;
                final int FINAL_COL = j;

                final Seat seat = new Seat(this, ROW, COL );
                seat.setStatus(false);
                seat.setBackground();
                seat.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT, 1.0f
                ));

                seat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        seatButtonClicked(seat, FINAL_ROW, FINAL_COL);
                        addSeat(seat);

                        System.out.println(calculate(seats));
                        String result = Double.toString(calculate(seats));
                        titleTextView.setText("Totaal: " + result);
                    }

                });

                tableRow.addView(seat);
            }
        }




    }

    private void seatButtonClicked(Seat seat, int row, int col) {
//        Toast.makeText(this, "Button clicked " + row + " " + col, Toast.LENGTH_SHORT).show();
        seat.setBackground();


    }

    private void addSeat (Seat seat){
        if(!seat.getStatus()){
            seats.add(seat);
        }else {
            seats.remove(seat);
        }
    }

    private double calculate(ArrayList<Seat>seats){
        double price = 0;
        for(Seat seat: seats){
            price += seat.getPrice();
        }

        return price;
    }


}
