package com.example.androidchallenge2;

public class Post {
    private String content;
    private String category;
    private String title;

    public Post(String content, String category, String title){
        this.content = content;
        this.category = category;
        this.title = title;
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


}
