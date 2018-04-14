package com.example.sammy.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Sammy on 4/5/18.
 */

public class TicketActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Ticket> tickets;
    private FilmDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        db = new FilmDatabase(this);
        tickets = db.getAllTickets();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_ticket);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new TicketAdapter(tickets, this);
        mRecyclerView.setAdapter(mAdapter);


        Button homeButton = (Button) findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customerIntent = new Intent(v.getContext().getApplicationContext(),MainActivity.class);
                v.getContext().startActivity(customerIntent);
            }
        });


        Button infoButton = (Button) findViewById(R.id.ticketButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customerIntent = new Intent(v.getContext().getApplicationContext(),TicketActivity.class);
                v.getContext().startActivity(customerIntent);
            }
        });

        Button contactButton = (Button) findViewById(R.id.contact);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customerIntent = new Intent(v.getContext().getApplicationContext(),ContactActivity.class);
                v.getContext().startActivity(customerIntent);
            }
        });

    }





}
