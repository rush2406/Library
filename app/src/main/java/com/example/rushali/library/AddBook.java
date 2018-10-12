package com.example.rushali.library;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rushali.library.data.BookContract;
import com.example.rushali.library.data.BookDbHelper;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Button b = (Button)findViewById(R.id.addbook);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBook();
                finish();
            }
        });
    }

    void insertBook()
    {
        // Create database helper
        BookDbHelper mDbHelper = new BookDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ArrayList<String> a = new ArrayList<>();
        a.add("CSE");
        a.add("ECE");
        ArrayList<Integer> b=new ArrayList<>();
        b.add(new Integer(26735));
        b.add(new Integer(67832));
        Gson gson = new Gson();

        String res= gson.toJson(a);

        String r=gson.toJson(b);
        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_NAME,"Harry Potter and the Deathly hallows");
        values.put(BookContract.BookEntry.COLUMN_AUTHOR,"JK Rowling");
        values.put(BookContract.BookEntry.COLUMN_CURQUANT, 10);
        values.put(BookContract.BookEntry.COLUMN_TOTALQUANT,10);
        values.put(BookContract.BookEntry.COLUMN_BOOKID,3546);
        values.put(BookContract.BookEntry.COLUMN_RESIDS,r);
        values.put(BookContract.BookEntry.COLUMN_TAGS,res);
        values.put(BookContract.BookEntry.COLUMN_PUBLISHER,"Bloomsbury");

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(BookContract.BookEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Book added with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}
