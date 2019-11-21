package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {

    public static final String POST_TITLE = "POST_TITLE";
    public static final String POST_ID= "POST_ID";
    public static final String POST_CONTENT = "POST_CONTENT";

    TextView postContent, postTitle;
    DatabaseReference databasePosts;
    String title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        title = intent.getStringExtra(POST_TITLE);
        content = intent.getStringExtra(POST_CONTENT);

        databasePosts = FirebaseDatabase.getInstance().getReference("posts").child(intent.getStringExtra(MainActivity.POST_ID));
        postContent = findViewById(R.id.postContent);
        postTitle = findViewById(R.id.postTitle);

        postTitle.setText(title);
        postContent.setText(content);
    }

    public void Back(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
