package com.example.androidchallenge2;

import java.util.Date;

public class Post {
    private String content;
    private String category;
    private String title;
    private String id;
    private String date;

    public Post(){

    }
    public Post(String id, String title, String category, String date, String content){
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

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
}
