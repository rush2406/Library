package com.example.rushali.library;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.rushali.library.data.BookContract;

/**
 * Created by rushali on 11/10/18.
 */

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView id = (TextView)view.findViewById(R.id.bid);

        String productName = cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME));
        int bid = cursor.getInt(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOKID));
        nameTextView.setText(productName);
        id.setText(String.valueOf(bid));
    }
}
