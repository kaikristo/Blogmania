package com.teste2.blogapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;


public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {





    private Context context;
    public List<BlogPost> blogPostList;

    public BlogAdapter(List<BlogPost> blogPostList) {
        this.blogPostList = blogPostList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_item,parent,false);
        context = parent.getContext();



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String desc_data = blogPostList.get(position).getDescription();


        holder.setDescriptionText(desc_data);
        int id = blogPostList.get(position).getID();

        if (blogPostList.get(position).getImage()!=null) {
            byte[] image = blogPostList.get(position).getImage();
            holder.setImage(image);
        }
        String title_data = blogPostList.get(position).getTitle();
        holder.setTitle(title_data);

        String user_data = blogPostList.get(position).getOwner();
        holder.setOwner(user_data);

        long date_data = blogPostList.get(position).getDate();
        holder.setDate(date_data);

        String text_data = blogPostList.get(position).getText();
        holder.setText(text_data);


        holder.setID(id);





    }

    @Override
    public int getItemCount() {
        return blogPostList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private int id;
        private TextView description;
        private ImageView imageView;
        private TextView title;
        private TextView date;
        private TextView text;
        private TextView owner;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            mView = itemView;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ReadPost();
                }
            });


        }

        public void setID(int id)
        {
        this.id =id;
        }

        public  void  setOwner(String owner){
            this.owner = mView.findViewById(R.id.item_user);
            this.owner.setText(owner);

        }

        public  void  setDescriptionText(String description){
            this.description = mView.findViewById(R.id.item_description);
            this.description.setText(description);

        }
        public void  setImage(byte[] image)
        {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imageView = mView.findViewById(R.id.item_image);
            imageView.setImageBitmap(bitmap);


        }
        public void setTitle(String title)
        {
            this.title = mView.findViewById(R.id.item_title);
            this.title.setText(title);
        }
        public void setText(String text)
        {
            this.text = mView.findViewById(R.id.item_text);
            this.text.setText(text);
        }
        public  void  setDate(long date)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
            Date curDate = new Date(date);

           String ConvertedDate = simpleDateFormat.format(curDate);

           this.date = mView.findViewById(R.id.item_date);
           this.date.setText(ConvertedDate);
        }

        public void  ReadPost()
        {


            Intent post = new Intent(context, ReadPost.class );
           post.putExtra("id",id);
          context.startActivity(post);

        }


    }


}
