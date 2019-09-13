package com.teste2.blogapplication;

import java.io.Serializable;

public class BlogPost   {
    private String owner,title,description,text;
    private byte[] image;
    private  long date;
    private  int ID;

    public BlogPost(String owner, String title, String description, String text, byte[] image, long date, int ID) {

        this.owner = owner;
        this.title = title;
        this.description = description;
        this.text = text;
        this.image = image;
        this.date = date;
        this.ID = ID;
    }
    public BlogPost()
    {

    }

    public  byte[] getImage(){
        return  image;
    }

    public String getOwner() {
        return owner;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public int getID() {
        return ID;
    }

}
