package lk.atig.sltourguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Keinda on 03/03/19.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tour_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Tour.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Tour.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertTour(Tour tour) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Tour.COLUMN_TITLE, tour.getTitle());
        values.put(Tour.COLUMN_SHORT_DESCRIPTION, tour.getShortdesc());
        values.put(Tour.COLUMN_RATING, tour.getRating());
        values.put(Tour.COLUMN_PRICE, tour.getPrice());
        values.put(Tour.COLUMN_IMAGE, tour.getImage());

        // insert row
        long id = db.insert(Tour.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Tour getTour(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Tour.TABLE_NAME,
                new String[]{
                        Tour.COLUMN_ID,
                        Tour.COLUMN_TITLE,
                        Tour.COLUMN_SHORT_DESCRIPTION,
                        Tour.COLUMN_RATING,
                        Tour.COLUMN_PRICE,
                        Tour.COLUMN_IMAGE
                },
                Tour.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Tour note = new Tour(
                cursor.getInt(cursor.getColumnIndex(Tour.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Tour.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(Tour.COLUMN_SHORT_DESCRIPTION)),
                cursor.getDouble(cursor.getColumnIndex(Tour.COLUMN_RATING)),
                cursor.getDouble(cursor.getColumnIndex(Tour.COLUMN_PRICE)),
                cursor.getInt(cursor.getColumnIndex(Tour.COLUMN_IMAGE))
        );

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Tour> getAllTours() {
        List<Tour> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Tour.TABLE_NAME + " ORDER BY " +
                Tour.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tour note = new Tour(
                        cursor.getInt(cursor.getColumnIndex(Tour.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Tour.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(Tour.COLUMN_SHORT_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(Tour.COLUMN_RATING)),
                        cursor.getDouble(cursor.getColumnIndex(Tour.COLUMN_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(Tour.COLUMN_IMAGE))
                );

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getTourCount() {
        String countQuery = "SELECT  * FROM " + Tour.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateTour(Tour tour) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Tour.COLUMN_TITLE, tour.getTitle());
        values.put(Tour.COLUMN_SHORT_DESCRIPTION, tour.getShortdesc());
        values.put(Tour.COLUMN_RATING, tour.getRating());
        values.put(Tour.COLUMN_PRICE, tour.getPrice());
        values.put(Tour.COLUMN_IMAGE, tour.getImage());

        // updating row
        return db.update(Tour.TABLE_NAME, values, Tour.COLUMN_ID + " = ?",
                new String[]{String.valueOf(tour.getId())});
    }

    public void deleteTour(Tour note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Tour.TABLE_NAME, Tour.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}
