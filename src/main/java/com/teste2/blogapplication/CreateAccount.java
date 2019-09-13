package com.teste2.blogapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.teste2.blogapplication.com.teste2.blogapplication.login.data.LoginContract;

public class CreateAccount extends AppCompatActivity {


private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        toolbar = findViewById(R.id.create_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create new account");


        Button CreateButton = findViewById(R.id.CreateLoginButton);
        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText LoginEdit = findViewById(R.id.CreateLoginEdit);
                EditText PasswordEdit =findViewById(R.id.CreatePasswordEdit);

                String login  = LoginEdit.getText().toString();
                String password = PasswordEdit.getText().toString();

              if(AddData(login,password))
              {
                  LoginEdit.setText("");
                  PasswordEdit.setText("");
                  ToMainActivity();
              }


            }
        });


    }

    private void ToMainActivity() {
        finish();
    }

    public boolean AddData(String login, String password)
    {
        if(LoginAlreadyExist(login)) return  false;

        SQLiteDatabase db = MainActivity.mLoginDBHelper.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(LoginContract.Login.COLUMN_LOGIN, login);
        values.put(LoginContract.Login.COLUMN_PASSWORD, password);

        long newRowID = db.insert(LoginContract.Login.TABLE_NAME, null, values);
        db.close();


        return  (newRowID!=-1);

    }

    private  boolean LoginAlreadyExist(String LoginToCheck) {

        SQLiteDatabase database = MainActivity.mLoginDBHelper.getReadableDatabase();

        String[] projection = {LoginContract.Login._ID, LoginContract.Login.COLUMN_LOGIN, LoginContract.Login.COLUMN_PASSWORD};

        Cursor cursor = database.query(
                LoginContract.Login.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {
            int dblogin = cursor.getColumnIndex(LoginContract.Login.COLUMN_LOGIN);
            while (cursor.moveToNext()) {

                String curLogin = cursor.getString(dblogin);

                if (curLogin.compareToIgnoreCase(LoginToCheck)==0) {
                    toastMessage("Login already exist");
                    cursor.close();
                    return  true;
                }
            }


        } finally {
            cursor.close();
        }
        return  false;
    }
    private void ShowDatabase()
    {


        SQLiteDatabase database = MainActivity.mLoginDBHelper.getReadableDatabase();

        String[] projection = {LoginContract.Login._ID, LoginContract.Login.COLUMN_LOGIN,LoginContract.Login.COLUMN_PASSWORD};

        Cursor cursor = database.query(
                LoginContract.Login.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try{
            int dbid = cursor.getColumnIndex(LoginContract.Login._ID);
            int dblogin = cursor.getColumnIndex(LoginContract.Login.COLUMN_LOGIN);
            int dbpassword = cursor.getColumnIndex(LoginContract.Login.COLUMN_PASSWORD);
            while (cursor.moveToNext())
            {

                Integer curId = cursor.getInt(dbid);
                String curLogin = cursor.getString(dblogin);
                String curPassword = cursor.getString(dbpassword);
                String s =curId.toString()+":"+ curLogin +":"+ curPassword +"\n";
                toastMessage(s);

            }


        }finally {
            cursor.close();
        }
    }
    private  void toastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_LONG ).show();
    }

}
