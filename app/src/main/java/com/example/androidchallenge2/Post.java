package com.example.androidchallenge2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private String content;
    private String category;
    private String title;
    private String id;
    private Date date;
    final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Post(){

    }
    public Post(String id, String title, String category, Date date, String content){
        this.id = id;
        this.title = title;
        this.category = category;
        this.date = date;
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public String getCategory(){
        return  category;
    }

    public String getTitle(){
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
}
