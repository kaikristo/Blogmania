package com.teste2.blogapplication;


import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.teste2.blogapplication.com.teste2.blogapplication.login.data.PostContract;



import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import java.io.InputStream;


public class AddActivity extends AppCompatActivity {

    private TextView TitleEdit;
    private TextView DiscriptionEdit;
    private TextView TextEdit;
    private  ImageView Image;
    private byte[] ConvertedImage = null;
    private Toolbar toolbar;
    private Button AddImageBtn;
    private Button ConfirmBtn;

    private  final  int RESULT_LOAD_IMG=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        toolbar = findViewById(R.id.addtoolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        getSupportActionBar().setTitle("Add new post");

        TitleEdit =findViewById(R.id.add_title);
        DiscriptionEdit = findViewById(R.id.add_description);
        TextEdit = findViewById(R.id.add_text);
        Image = findViewById(R.id.add_image);
        AddImageBtn = findViewById(R.id.add_image_btn);

        //pick image from gallery
        AddImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);


            }
        });


        ConfirmBtn = findViewById(R.id.add_confirm_btn);
        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertedImage = null;
                if (Image.getDrawable() != null) {
                    Bitmap bmp = ((BitmapDrawable) Image.getDrawable()).getBitmap();
                    Bitmap resizeBmp = getResizedBitmap(bmp, 300);

                ConvertedImage = getBitmapAsByteArray(resizeBmp);
                }
                    if (AddNewPost(MainActivity.curUser.getName(),
                            TitleEdit.getText().toString(),
                            ConvertedImage,
                            DiscriptionEdit.getText().toString(),
                            TextEdit.getText().toString())) {

                    }
                    Intent intent = new Intent(AddActivity.this, BlogMain.class);
                    intent.putExtra("Update","true");
                    startActivity(intent);
                    finish();
                }
        });



    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                ToastMessage("Something went wrong");
            }

        }else {
            ToastMessage("You haven't picked Image");
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void ToastMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }
    private boolean AddNewPost(String Owner, String Title, byte[] Image, String Description, String
        Text)
    {

            SQLiteDatabase db = MainActivity.mPostDBHelper.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(PostContract.Post.COLUMN_OWNER, Owner);
        values.put(PostContract.Post.COLUMN_DESCRIPTION, Description);
        long date = System.currentTimeMillis();
        values.put(PostContract.Post.COLUMN_DATE, date);
        if (Image != null )
        values.put(PostContract.Post.COLUMN_IMAGE, Image);
        values.put(PostContract.Post.COLUMN_TEXT, Text);
        values.put(PostContract.Post.COLUMN_TITLE, Title);


        long newRowID = db.insert(PostContract.Post.TABLE_NAME,null,values);
        db.close();
        return (newRowID!=-1);





    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


}
