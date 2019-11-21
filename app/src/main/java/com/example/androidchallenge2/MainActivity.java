package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button createCategory, createPost;
    ListView listViewPosts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCategory = findViewById(R.id.createCategory);
        createPost = findViewById(R.id.createPost);
        listViewPosts = findViewById(R.id.listViewPosts);

        listViewPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);

            }
        });

    }

    public void addPost(View v){
        Intent intent = new Intent(this, CreatePost.class);
        startActivityForResult(intent, 1);
    }

    public void addCategory(View v){
        Intent intent = new Intent(this, CreateCategoryActivity.class);
        startActivityForResult(intent, 2);
    }

    public void sortByDate(View v){

        //read everything from the db
        //sort based on time stamp
    }

    public void sortByCategory(View v){
        //read everything from the db
        //display a list of all categories which user can click
        //match string to all entries in the db
        //sort based on time stamp
    }
}
