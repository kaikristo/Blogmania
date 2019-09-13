package com.teste2.blogapplication;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.teste2.blogapplication.com.teste2.blogapplication.login.data.PostContract;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private  RecyclerView recyclerView;
    public   List<BlogPost> blogPostList;
    private  BlogAdapter blogAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        blogPostList = new ArrayList<>();
        blogAdapter = new BlogAdapter(blogPostList);
        recyclerView = view.findViewById(R.id.blog_post_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(blogAdapter);

        SQLiteDatabase database = MainActivity.mPostDBHelper.getReadableDatabase();

        if (database !=null)
        {


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

            int i = 0;
            if (cursor.getCount()>0)
            try{
                int dbID = cursor.getColumnIndex(PostContract.Post._ID);
                int dbOwner = cursor.getColumnIndex(PostContract.Post.COLUMN_OWNER);
                int dbTitle = cursor.getColumnIndex(PostContract.Post.COLUMN_TITLE);
                int dbImage = cursor.getColumnIndex(PostContract.Post.COLUMN_IMAGE);
                int dbDescription = cursor.getColumnIndex(PostContract.Post.COLUMN_DESCRIPTION);
                int dbText = cursor.getColumnIndex(PostContract.Post.COLUMN_TEXT);
                int dbDate = cursor.getColumnIndex(PostContract.Post.COLUMN_DATE);
                cursor.moveToLast();

                do {

                    int Id = cursor.getInt(dbID);
                    String Owner = cursor.getString(dbOwner);
                    String Title = cursor.getString(dbTitle);
                    byte[] Image = null;
                    if (cursor.getBlob(dbImage) != null) {
                        Image = cursor.getBlob(dbImage);
                    }

                    String Description = cursor.getString(dbDescription);
                    String Text = cursor.getString(dbText);
                    long Date = cursor.getLong(dbDate);

                    BlogPost bp = new BlogPost(Owner,Title,Description,Text,Image,Date, Id);
                    blogPostList.add(bp);
                    blogAdapter.notifyDataSetChanged();



                }
                while ((cursor.moveToPrevious()));
            }
            finally {



                cursor.close();
            }

        }






        return view;


    }

}
