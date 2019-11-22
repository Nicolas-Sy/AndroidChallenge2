package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreatePost extends AppCompatActivity {

    public static final String POST_TITLE = "POST_TITLE";
    public static final String POST_ID = "POST_ID";
    public static final String POST_CATEGORY = "POST_CATEGORY";
    public static final String POST_CONTENT = "POST_CONTENT";

    String categories = "";
    EditText createTitle, createContent;
    List<Post> posts;
    CheckBox personalCategory, profCategory, importantCategory;
    List<CheckBox> categoryCheckBox = new ArrayList<CheckBox>();
    DatabaseReference databasePosts;
    Date currentTime = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        databasePosts = FirebaseDatabase.getInstance().getReference("posts");
        createTitle = findViewById(R.id.createTitle);
        createContent = findViewById(R.id.createContent);
        personalCategory = findViewById(R.id.personalCategory);
        profCategory = findViewById(R.id.profCategory);
        importantCategory = findViewById(R.id.importantCategory);

        categoryCheckBox.add(personalCategory);
        categoryCheckBox.add(profCategory);
        categoryCheckBox.add(importantCategory);
        posts = new ArrayList<>();

    }

    public void onCheckboxClicked(View v) {
        // Is the view now checked?
        categories = "";

        for (CheckBox category: categoryCheckBox){
            if(category.isChecked())
                categories = categories + category.getText().toString();
        }
        System.out.println(categories);
        System.out.println(currentTime.toString());
    }

    public void createPost(View v) {
        String title = createTitle.getText().toString();
        String content = createContent.getText().toString();

                if (!(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))) {
                    String id = databasePosts.push().getKey();
                    Post post = new Post(id, title, categories, currentTime.toString(), content);
                    databasePosts.child(id).setValue(post);

                    //posts.add(post);

                    createTitle.setText("");
                    createContent.setText("");

                    Toast.makeText(this, "Post added", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra(POST_TITLE, title);
                    intent.putExtra(POST_ID, id);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Please enter a name and content", Toast.LENGTH_LONG).show();
                }


        }
    }

