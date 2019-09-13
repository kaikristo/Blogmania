package com.teste2.blogapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teste2.blogapplication.com.teste2.blogapplication.login.data.PostContract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReadPost extends AppCompatActivity {
    private int id;
    private TextView description;
    private ImageView imageView;
    private TextView title;
    private TextView date;
    private TextView text;
    private TextView owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_post);

        description = findViewById(R.id.read_description);
        imageView = findViewById(R.id.read_image);
        title = findViewById(R.id.read_title);
        date = findViewById(R.id.read_date);
        text = findViewById(R.id.read_text);
        owner = findViewById(R.id.read_user);
        if(getIntent().getExtras()!=null)
        {
           int id = getIntent().getIntExtra("id",-1);
            if (id!=-1) {
                SQLiteDatabase database = MainActivity.mPostDBHelper.getReadableDatabase();

                if (database != null) {


                    String[] projection = {
                            PostContract.Post._ID,
                            PostContract.Post.COLUMN_OWNER,
                            PostContract.Post.COLUMN_TITLE,
                            PostContract.Post.COLUMN_IMAGE,
                            PostContract.Post.COLUMN_DESCRIPTION,
                            PostContract.Post.COLUMN_TEXT,
                            PostContract.Post.COLUMN_DATE
                    };

                    Cursor cursor = database.query(
                            PostContract.Post.TABLE_NAME,
                            projection,
                            null,
                            null,
                            null,
                            null,
                            null);


                    if (cursor.getCount() > 0) {
                        try {
                            int dbID = cursor.getColumnIndex(PostContract.Post._ID);
                            int dbOwner = cursor.getColumnIndex(PostContract.Post.COLUMN_OWNER);
                            int dbTitle = cursor.getColumnIndex(PostContract.Post.COLUMN_TITLE);
                            int dbImage = cursor.getColumnIndex(PostContract.Post.COLUMN_IMAGE);
                            int dbDescription = cursor.getColumnIndex(PostContract.Post.COLUMN_DESCRIPTION);
                            int dbText = cursor.getColumnIndex(PostContract.Post.COLUMN_TEXT);
                            int dbDate = cursor.getColumnIndex(PostContract.Post.COLUMN_DATE);
                            cursor.move(id);

                            int Id = cursor.getInt(dbID);
                            String Owner = cursor.getString(dbOwner);
                            String Title = cursor.getString(dbTitle);
                            byte[] Image =null;
                            if(cursor.getBlob(dbImage)!=null)
                            Image = cursor.getBlob(dbImage);
                            String Description = cursor.getString(dbDescription);
                            String Text = cursor.getString(dbText);
                            long Date = cursor.getLong(dbDate);

                            owner.setText(Owner);
                            title.setText(Title);
                            description.setText(Description);
                            text.setText(Text);
                            if(Image!=null) {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
                                imageView.setImageBitmap(bitmap);
                            }
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
                            Date curDate = new Date(Date);
                            String ConvertedDate = simpleDateFormat.format(curDate);
                            date.setText(ConvertedDate);


                        } finally {


                            cursor.close();
                        }
                    }

                }
            }
            else
                Toast.makeText(ReadPost.this, "Error id", Toast.LENGTH_LONG);
        }

    }
}
