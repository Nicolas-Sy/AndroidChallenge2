package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {

    public static final String POST_TITLE = "POST_TITLE";
    public static final String POST_ID= "POST_ID";
    public static final String POST_CATEGORY = "POST_CATEGORY";
    public static final String POST_CONTENT = "POST_CONTENT";
    public static final String POST_TIME = "POST_TIME";

    TextView postContent, postTitle, postCategory, postTime;
    DatabaseReference databasePosts;
    String title,content,category, time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent intent = getIntent();
        title = intent.getStringExtra(POST_TITLE);
        content = intent.getStringExtra(POST_CONTENT);
        category = intent.getStringExtra(POST_CATEGORY);
        time = intent.getStringExtra(POST_TIME);

        databasePosts = FirebaseDatabase.getInstance().getReference("posts").child(intent.getStringExtra(MainActivity.POST_ID));

        postContent = findViewById(R.id.postContent);
        postTitle = findViewById(R.id.postTitle);
        postCategory = findViewById(R.id.postCategory);
        postTime = findViewById(R.id.TimeStamp);

        postTitle.setText(title);
        postContent.setText(content);
        postCategory.setText(category);
        postTime.setText(time);

    }

    public void Back(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Delete(View v) {
        Intent intent = getIntent();
        //getting the specified artist reference
        databasePosts = FirebaseDatabase.getInstance().getReference("posts").child(intent.getStringExtra(MainActivity.POST_ID));

        //removing artist
        databasePosts.removeValue();

        Intent intent2 = new Intent(this,MainActivity.class);
        startActivity(intent2);
        finish();
    }
}
