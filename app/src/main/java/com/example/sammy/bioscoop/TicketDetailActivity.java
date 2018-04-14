package com.example.sammy.bioscoop;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by Sammy on 4/5/18.
 */

public class TicketDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        Ticket ticket = (Ticket) getIntent().getSerializableExtra("MyFilmAdapter");


        String text= "row 1, num 3"; // Whatever you need to encode in the QR code

        ImageView imageView = findViewById(R.id.code);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        TextView titleTicket = (TextView) findViewById(R.id.titleFilmDetailed);
        titleTicket.setText(ticket.getFilmTitle());

        TextView seatDetail = (TextView) findViewById(R.id.giveSeatsDetailed);
        seatDetail.setText(ticket.getSeatInfo());

        TextView dateTime = (TextView) findViewById(R.id.dateTicketDetail);
        dateTime.setText("Date: " + ticket.getDate() + " Time: " + ticket.getTime());

        TextView idNum = (TextView) findViewById(R.id.idNum);
        idNum.setText(String.valueOf(ticket.getTicketID()));

    }


}
