package com.teste2.blogapplication.com.teste2.blogapplication.login.data;

import android.provider.BaseColumns;

public final class PostContract {
    public  PostContract()
    {

    }
    public  static final  class Post implements BaseColumns {
    public  static  final String TABLE_NAME ="Posts";

        public  final  static  String _ID = BaseColumns._ID;
        public  final  static  String COLUMN_OWNER = "Owner";
        public  final  static  String COLUMN_TITLE = "Title";
        public  final  static  String COLUMN_DESCRIPTION = "Description";
        public  final  static  String COLUMN_TEXT = "Text";
        public  final  static  String COLUMN_IMAGE = "Image";
        public  final  static  String COLUMN_DATE = "Data";




    }
}
