package com.example.rushali.library;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.example.rushali.library.data.BookContract;
import com.example.rushali.library.data.BookDbHelper;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    FloatingActionButton add;
    BookDbHelper mDbHelper;
    BookCursorAdapter mCursorAdapter;
    public static final int LOADER_ID = 0;
    String[] PROJECTION = {
            BookContract.BookEntry._ID,
            BookContract.BookEntry.COLUMN_NAME,
            BookContract.BookEntry.COLUMN_BOOKID
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=(FloatingActionButton)findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(MainActivity.this,AddBook.class);
               startActivity(i);
               mCursorAdapter.notifyDataSetChanged();
            }
        });

      mDbHelper = new BookDbHelper(this);
      ListView list = (ListView)findViewById(R.id.list);
      mCursorAdapter = new BookCursorAdapter(this,null);
      list.setAdapter(mCursorAdapter);

        getSupportLoaderManager().initLoader(LOADER_ID,null,this);

    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this,
                BookContract.BookEntry.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor c) {
        mCursorAdapter.swapCursor(c);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

}
