package com.example.sammy.bioscoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sammy on 4/5/18.
 */

public class ThankYouActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        Button toHome = (Button) findViewById(R.id.toHome);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHomeIntent = new Intent(v.getContext().getApplicationContext(),MainActivity.class);
                v.getContext().startActivity(toHomeIntent);
            }
        });


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
