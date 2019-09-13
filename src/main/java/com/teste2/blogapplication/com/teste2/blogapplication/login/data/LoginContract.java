package com.teste2.blogapplication.com.teste2.blogapplication.login.data;

import android.provider.BaseColumns;

public final class LoginContract {
        private LoginContract()
        {

        };

        public  static final  class Login implements BaseColumns {
            public  final  static  String TABLE_NAME = "Logins";

            public  final  static  String _ID = BaseColumns._ID;
            public  final  static  String COLUMN_LOGIN = "Login";
            public  final  static  String COLUMN_PASSWORD = "PASSWORD";



        }
}

