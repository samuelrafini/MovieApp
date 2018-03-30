package com.example.sammy.bioscoop;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Sammy on 3/28/18.
 */

public class Seat extends android.support.v7.widget.AppCompatButton {
    private String ID;
    private int rowNum;
    private int colNum;
    private boolean status;
    private boolean isBooked;
    private final double price = 10.00;


    private static final String TAG = "Seat";


    public Seat(Context context) {
        super(context);
    }

    public Seat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Seat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Seat(Context context, int row, int col) {
        super(context);

        setRowNum(row);
        setColNum(col);
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public void setBackground(){

        if (this.isBooked) {
            Log.d(TAG, "setBackground: isbooked " + isBooked + " set this chair to booked");
            this.setBackgroundResource(R.drawable.seat_sold);
        }

        if(this.status) {
            Log.d(TAG, "setBackground: Status was true");
            this.setBackgroundResource(R.drawable.seat_selected);
            this.status = false;

        }else{
            Log.d(TAG, "setBackground: Status was false");

            this.setBackgroundResource(R.drawable.seat_sale);
            this.status = true;
        }


    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }
}
