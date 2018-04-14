package com.example.sammy.bioscoop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    private TextView contact;
    private TextView route;
    private TextView adres;
    private TextView tijden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Contact item = new Contact("Contact:\n" +
                "Telefoonnummer: 076-12345\n" +
                "Mail: informatie@despijkerbios.nl\n", "De bioscoop is makkelijk te bereiken \n" +
                "met het openbaar vervoer.\n" +
                "Vanaf station Breda, kunt u vanaf \n" +
                "bus perron R, met het openbaar\n" +
                "vervoer makkelijk uitstappen bij \n" +
                "de Lovendijkstraat. Met de auto\n" +
                "moet u afslag Breda Noord nemen \n" +
                "op de A58, vervolgens moet u \n" +
                "de gele borden volgen en na de \n" +
                "rotonde bevindt de bioscoop\n" +
                "aan uw rechterzijde.\n", "De Spijker bioscoop\n" +
                "Lovendijkstraat 61\n" +
                "4818 AJ Breda\n", "Openingstijden: \n" +
                "Maandag: \t12:00 – 23:00\n" +
                "Dinsdag:\t12:00 – 23:00\n" +
                "Woensdag:\t12:00 – 23:00\n" +
                "Donderdag:\t12:00 – 23:00\n" +
                "Vrijdag: \t12:00 – 01:00\n" +
                "Zaterdag:\t12:00 – 01:00\n" +
                "Zondag: \t12:00 – 23:00\n");

        contact = (TextView)findViewById(R.id.ContactId);
        route = (TextView)findViewById(R.id.RouteId);
        adres = (TextView)findViewById(R.id.AdresId);
        tijden = (TextView)findViewById(R.id.OpeningsId);

        contact.setText(item.getContact());
        route.setText(item.getRoute());
        adres.setText(item.getAdres());
        tijden.setText(item.getOpeningstijden());


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
