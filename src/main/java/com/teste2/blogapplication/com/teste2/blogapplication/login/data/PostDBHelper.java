package com.teste2.blogapplication.com.teste2.blogapplication.login.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PostDBHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = PostDBHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "Posts.db";
    private static final int DATABASE_VERISON = 1;

    public PostDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERISON);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_POST_TABLE = "CREATE TABLE " + PostContract.Post.TABLE_NAME + " ("
                + PostContract.Post._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PostContract.Post.COLUMN_OWNER + " TEXT, "
                + PostContract.Post.COLUMN_TITLE + " TEXT, "
                + PostContract.Post.COLUMN_IMAGE + " BLOB, "
                + PostContract.Post.COLUMN_DESCRIPTION + " TEXT, "
                + PostContract.Post.COLUMN_TEXT + " TEXT, "
                + PostContract.Post.COLUMN_DATE + " INT)";

        db.execSQL(SQL_CREATE_POST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
