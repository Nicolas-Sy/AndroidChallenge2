package com.example.androidchallenge2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String POST_TITLE = "POST_TITLE";
    public static final String POST_ID= "POST_ID";
    public static final String POST_CATEGORY = "POST_CATEGORY";
    public static final String POST_CONTENT = "POST_CONTENT";
    public static final String POST_TIME = "POST_TIME";

    Button createCategory, createPost,buttonSortDate;
    ListView listViewPosts;
    DatabaseReference databasePosts;
    List<CheckBox> categoryCheckBox = new ArrayList<CheckBox>();

    List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasePosts = FirebaseDatabase.getInstance().getReference("posts");
        createCategory = findViewById(R.id.createCategory);
        createPost = findViewById(R.id.createPost);
        buttonSortDate = findViewById(R.id.buttonSortDate);
        listViewPosts = findViewById(R.id.listViewPosts);
        final Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        posts = new ArrayList<>();

        listViewPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Post post = posts.get(i);
                String timestamp_string = formatter.format(post.getDate());

                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                intent.putExtra(POST_ID,post.getId());
                intent.putExtra(POST_TITLE, post.getTitle());
                intent.putExtra(POST_CONTENT,post.getContent());
                intent.putExtra(POST_CATEGORY,post.getCategory());
                intent.putExtra(POST_TIME, timestamp_string);
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

    public void sortByTimeStamp(View v){
        Collections.sort(posts, new MainActivity.TimeStampComparator());
        PostList postAdapter = new PostList(MainActivity.this,posts);
        listViewPosts.setAdapter(postAdapter);

    }
    public class TimeStampComparator implements Comparator<Post> {
        @Override
        public int compare(Post earlypost, Post latepost) {
            return latepost.getDate().compareTo(earlypost.getDate());
        }

    }
    public void sortByCategory(View v){
        Intent intent = new Intent(this, ChooseCategoryActivity.class);
        startActivity(intent);

    }
}
