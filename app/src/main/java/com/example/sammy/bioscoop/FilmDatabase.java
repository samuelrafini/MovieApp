package com.example.sammy.bioscoop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Pranawa Somvedi on 4/3/2018.
 */

public class FilmDatabase extends SQLiteOpenHelper {

    private static final String TAG = "FilmDatabase";

    private static final String DATABASE_NAME = "cinema.db";
    private static final String TABLE_RESERVATION = "reservation";
    private static final String TABLE_TICKET = "ticket";
    private static final String TABLE_SHOW = "show";
    private static final String TABLE_ROOM = "room";
    private SQLiteDatabase db = this.getWritableDatabase();






    public FilmDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_TICKET + " (ticket_id INTEGER PRIMARY KEY AUTOINCREMENT, seat_info VARCHAR(512),date VARCHAR(8), time VARCHAR(5), title VARCHAR(256));");
        sqLiteDatabase.execSQL("create table " + TABLE_RESERVATION + " (reservation_id INTEGER PRIMARY KEY AUTOINCREMENT, seat_id VARCHAR(4), show_id INTEGER); ");
        sqLiteDatabase.execSQL("create table " + TABLE_SHOW + " (show_id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(256), time VARCHAR(5), date VARCHAR(8), room_id INTEGER);");
        sqLiteDatabase.execSQL("create table " + TABLE_ROOM + " (room_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(256));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKET + " (ticket_id INTEGER PRIMARY KEY AUTOINCREMENT, seat_info VARCHAR(512)), date VARCHAR(8), time VARCHAR(5), title VARCHAR(256);");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION + " (reservation_id INTEGER PRIMARY KEY AUTOINCREMENT, seat_id VARCHAR(4), show_id INTEGER); ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOW + " (show_id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(256), time VARCHAR(5), date VARCHAR(8), room_id INTEGER);");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM + " (room_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(256));");

        onCreate(sqLiteDatabase);

    }

    public boolean insertDataToReservation(int show_id, String seat_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("seat_id", seat_id);
        contentValues.put("show_id", show_id);
        long result = db.insert(TABLE_RESERVATION, null, contentValues);

        Log.d(TAG, "insertDataToShow: inserted" + " " + show_id +  " " + seat_id);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertDataToTicket(String seat_info, String date, String time, String title) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("seat_info",seat_info);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("title",title);

        Log.d(TAG,"insertDataToTicket: inserted " + seat_info + " " + date + " " + time + " " + title);

        long result = db.insert(TABLE_TICKET, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean insertDataToShow(String title, String time, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("time", time);
        contentValues.put("date", date);
//        contentValues.put("room_id", room_id);

        Log.d(TAG, "insertDataToShow: inserted" + " " + title +  " " + time + " " + date);

        long result = db.insert(TABLE_SHOW, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean insertDataToRoom(String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);

        long result = db.insert(TABLE_ROOM, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Show> getShows(String title) {
        String sql = "SELECT * FROM show WHERE title LIKE '" + title + "';";

        ArrayList<Show> showList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            int show_id = cursor.getInt(cursor.getColumnIndex("show_id"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
//            int room_id = cursor.getInt(cursor.getColumnIndex("room_id"));

            Show show = new Show(show_id, time, date);
            showList.add(show);
            cursor.moveToNext();
        }

        Log.d(TAG, "getShows: " + showList);


        return showList;
    }

    public ArrayList<String> getSeats(int show_id) {

        String sql = "SELECT * FROM reservation WHERE show_id LIKE '" + show_id + "';";

        ArrayList<String> seatList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            String seatId = cursor.getString(cursor.getColumnIndex("seat_id"));
            int showId = cursor.getInt(cursor.getColumnIndex("show_id"));

            seatList.add(seatId);
            cursor.moveToNext();

        }
        return seatList;
    }


    public Show getDateTime(int show_id) {

        String sql = "SELECT * FROM show WHERE show_id LIKE '" + show_id + "';";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        Show show = new Show(show_id, cursor.getString(cursor.getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("date")));

        return show;

    }

    public ArrayList<Ticket> getAllTickets() {

        String sql = "SELECT * FROM ticket;";

        ArrayList<Ticket> ticketArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {


            int ticketID =  cursor.getInt(cursor.getColumnIndex("ticket_id"));
            String seatinfo = cursor.getString(cursor.getColumnIndex("seat_info"));
            String date =  cursor.getString(cursor.getColumnIndex("date"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String filmtitle = cursor.getString(cursor.getColumnIndex("title"));

            Ticket ticket = new Ticket(seatinfo, date, time, filmtitle);
            ticket.setTicketID(ticketID);
            ticketArrayList.add(ticket);
            cursor.moveToNext();

        }
        return ticketArrayList;
    }
}
