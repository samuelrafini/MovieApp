package com.example.sammy.bioscoop;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sammy on 3/28/18.
 */

public class Seat extends android.support.v7.widget.AppCompatButton {
    private String ID; //hashcode //row col string //

    private int roomID;
    private String seatID;
    private int rowNum;
    private int colNum;
    private boolean isSelected;
    private boolean isBooked = false;
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
        this.rowNum = row;
        this.colNum = col;
        this.seatID = Integer.toString(this.rowNum) + "," + Integer.toString(this.colNum);
//        Log.d(TAG, "Seat: ----------------------------- ID = " + seatID);
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

    public boolean setBackground(){

        if (this.isBooked) {
            Log.d(TAG, "setBackground: isbooked " + isBooked + " set this chair to booked");
            this.setBackgroundResource(R.drawable.seat_sold);
            return false;
        }

        if(this.isSelected) {
            Log.d(TAG, "setBackground: Status was true");
            this.setBackgroundResource(R.drawable.seat_selected);
            this.isSelected = false;
            return true;
        }else{
            Log.d(TAG, "setBackground: Status was false");

            this.setBackgroundResource(R.drawable.seat_sale);
            this.isSelected = true;
            return true;
        }


    }

    public void setStatus(boolean status) {
        this.isSelected = status;
    }

    public boolean getStatus() {
        return isSelected;
    }

    public double getPrice() {
        return price;
    }

    public String getSeatID(){
        return this.seatID;
    }

    public void setIsBooked(){
        this.isBooked = true;
    }

    public boolean IsBooked(){
        return this.isBooked;
    }


}
