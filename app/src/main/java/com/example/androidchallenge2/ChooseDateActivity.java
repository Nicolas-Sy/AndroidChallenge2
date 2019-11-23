package com.example.androidchallenge2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**INCOMPLETE
public class ChooseDateActivity extends AppCompatActivity {

    EditText dateText;
    ListView listViewPosts;
    DatabaseReference databasePosts;
    Date parsedDate;
    List<Post> posts;
    final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);
        dateText = findViewById(R.id.dateText);
        databasePosts = FirebaseDatabase.getInstance().getReference("posts");
        listViewPosts = findViewById(R.id.listViewPosts);


        posts = new ArrayList<>();

    }

    public void sortByTimeStamp(View v){

        try {
            String dateToParse = dateText.getText().toString();
            System.out.println(dateToParse);
            parsedDate = formatter.parse(dateToParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        databasePosts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting post
                    Post post = postSnapshot.getValue(Post.class);
                    //adding post to the list
                    System.out.println(parsedDate);
                    System.out.println(post.getDate());


                    if(parsedDate.equals(post.getDate()))
                        posts.add(post);
                }

                Collections.sort(posts, new ChooseDateActivity.TimeStampComparator());
                PostList postAdapter = new PostList(ChooseDateActivity.this,posts);
                listViewPosts.setAdapter(postAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public class TimeStampComparator implements Comparator<Post> {
        @Override
        public int compare(Post earlypost, Post latepost) {
            return latepost.getDate().compareTo(earlypost.getDate());
        }

    }
}
*/