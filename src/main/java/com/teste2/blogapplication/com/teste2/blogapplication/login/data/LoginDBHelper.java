package com.teste2.blogapplication.com.teste2.blogapplication.login.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class LoginDBHelper extends SQLiteOpenHelper {

    public  static  final  String LOG_TAG = LoginDBHelper.class.getSimpleName();

    private static  final String DATABASE_NAME = "Login.db";

    /**
     *
     * Версия. При изменении структуры базы - изменить версию.
     */
    private  static  final int DATABASE_VERISON = 1;




    public  LoginDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERISON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " + LoginContract.Login.TABLE_NAME + " ("
            + LoginContract.Login._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LoginContract.Login.COLUMN_LOGIN + " TEXT NOT NULL, "
            + LoginContract.Login.COLUMN_PASSWORD + " TEXT NOT NULL);";

    db.execSQL(SQL_CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
