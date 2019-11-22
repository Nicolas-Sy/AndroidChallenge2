package com.example.androidchallenge2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String POST_TITLE = "POST_TITLE";
    public static final String POST_ID= "POST_ID";
    public static final String POST_CATEGORY = "POST_CATEGORY";
    public static final String POST_CONTENT = "POST_CONTENT";
    public static final String POST_TIME = "POST_TIME";

    Button createCategory, createPost;
    ListView listViewPosts;
    DatabaseReference databasePosts;

    List<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        databasePosts = FirebaseDatabase.getInstance().getReference("posts");
        createCategory = findViewById(R.id.createCategory);
        createPost = findViewById(R.id.createPost);
        listViewPosts = findViewById(R.id.listViewPosts);

        posts = new ArrayList<>();

        listViewPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Post post = posts.get(i);

                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                intent.putExtra(POST_ID,post.getId());
                intent.putExtra(POST_TITLE, post.getTitle());
                intent.putExtra(POST_CONTENT,post.getContent());
                intent.putExtra(POST_CATEGORY,post.getCategory());
                intent.putExtra(POST_TIME, post.getDate());
                startActivity(intent);
            }
        });

    }

    protected void onStart() {
        super.onStart();
        databasePosts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting post
                    Post post = postSnapshot.getValue(Post.class);
                    //adding post to the list
                    posts.add(post);

                }

                PostList postAdapter = new PostList(MainActivity.this,posts);
                listViewPosts.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addPost(View v){
        Intent intent = new Intent(this, CreatePost.class);
        startActivity(intent);
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
