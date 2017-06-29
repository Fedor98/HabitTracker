package com.example.fedor.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fedor.habittracker.data.HabitContract.HabitEntry;
import com.example.fedor.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);
    }

    private Cursor readStoreCursor() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_TYPE,
                HabitEntry.COLUMN_HABIT_FREQUENCY,
                HabitEntry.COLUMN_HABIT_GROUP
        };

        // Perform a query on the habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,  // The table to query
                projection,             // The columns to return
                null,                   // The columns for the WHERE clause
                null,                   // The values for the WHERE clause
                null,                   // Don't group the rows
                null,                   // Don't filter by row groups
                null);                    // The sort order

        return cursor;
    }

        private void fillCursor(Cursor cursor) {
            try {
                // Figure out the index of each column
                int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
                int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
                int typeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TYPE);
                int frequencyColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_FREQUENCY);
                int groupColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_GROUP);

                // Iterate through all the returned rows in the cursor
                while (cursor.moveToNext()) {
                    // Use that index to extract the String or Int value of the word
                    // at the current row the cursor is on.
                    int currentID = cursor.getInt(idColumnIndex);
                    String currentName = cursor.getString(nameColumnIndex);
                    String currentType = cursor.getString(typeColumnIndex);
                    int currentFrequency = cursor.getInt(frequencyColumnIndex);
                    int currentGroup = cursor.getInt(groupColumnIndex);
                }
            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                cursor.close();
            }
        }

    /**
     * Helper method to insert hardcoded habit data into the database. For debugging purposes only.
     */
    private void insertHabit() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Taking my medicine habit attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Taking my medicine");
        values.put(HabitEntry.COLUMN_HABIT_TYPE, "medical");
        values.put(HabitEntry.COLUMN_HABIT_FREQUENCY, HabitEntry.FREQUENCY_DAILY);
        values.put(HabitEntry.COLUMN_HABIT_GROUP, HabitEntry.GROUP_NO);

        // Insert a new row for Taking my medicine in the database, returning the ID of that new row.
        // The first argument for db.insert() is the habits table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }
}
