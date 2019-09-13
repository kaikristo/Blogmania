package com.teste2.blogapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teste2.blogapplication.com.teste2.blogapplication.login.data.LoginContract;
import com.teste2.blogapplication.com.teste2.blogapplication.login.data.LoginDBHelper;
import com.teste2.blogapplication.com.teste2.blogapplication.login.data.PostDBHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    public static LoginDBHelper mLoginDBHelper;
    public static PostDBHelper mPostDBHelper;

    public class User {
        private String Name;
        private int ID;


        public User(String name, int id) {
            Name = name;
            ID = id;
        }

        public String getName() {
            return Name;
        }
    }

    public static User curUser = null;

    private Button LoginButton;
    private EditText LoginEdit;
    private EditText PasswordEdit;
    private Button ToCreateButton;


    private void ToCreateNewAccount() {
        Intent intent = new Intent(MainActivity.this, CreateAccount.class);
        startActivity(intent);
    }


    private User Login(String login, String password) {


        SQLiteDatabase database = mLoginDBHelper.getReadableDatabase();


        String[] projection = {LoginContract.Login._ID, LoginContract.Login.COLUMN_LOGIN, LoginContract.Login.COLUMN_PASSWORD};

        Cursor cursor = database.query(
                LoginContract.Login.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        int error = 0;
        try {
            int dbid = cursor.getColumnIndex(LoginContract.Login._ID);
            int dblogin = cursor.getColumnIndex(LoginContract.Login.COLUMN_LOGIN);
            int dbpassword = cursor.getColumnIndex(LoginContract.Login.COLUMN_PASSWORD);

            while (cursor.moveToNext()) {


                Integer curId = cursor.getInt(dbid);
                String curLogin = cursor.getString(dblogin);
                String curPassword = cursor.getString(dbpassword);
                if (curLogin.compareToIgnoreCase(login) == 0)
                    if (curPassword.compareTo(password) == 0) {
                        cursor.close();
                        {
                            return  new User(curLogin,curId);
                        }

                    } else {
                        error = 1;
                    }
            }


        } finally {
            cursor.close();
        }
        if (error == 0) {
            toastMessage("Error: login not found");
        } else if (error == 1) {
            toastMessage("Error: invalid password");

        }
        return null;
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mLoginDBHelper = new LoginDBHelper(this);
        mPostDBHelper = new PostDBHelper(this);


        LoginButton = findViewById(R.id.LoginButton);
        LoginEdit = findViewById(R.id.LoginEdit);
        PasswordEdit = findViewById((R.id.PasswordEdit));
        ToCreateButton = findViewById(R.id.ToCreateButton);


        ToCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToCreateNewAccount();
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               curUser = Login(LoginEdit.getText().toString(), PasswordEdit.getText().toString());

                            if (curUser != null) {
                                LoginEdit.setText("");
                                PasswordEdit.setText("");

                                Intent intent = new Intent(MainActivity.this, BlogMain.class);
                                startActivity(intent);


                            }
                         else
                             {
                            toastMessage("Login not found");
                        }

            }
        });





        }
}

