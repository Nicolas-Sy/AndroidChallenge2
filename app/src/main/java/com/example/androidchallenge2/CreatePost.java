package com.example.androidchallenge2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreatePost extends AppCompatActivity {

    public static final String POST_TITLE = "POST_TITLE";
    public static final String POST_ID = "POST_ID";
    public static final String POST_CATEGORY = "POST_CATEGORY";
    public static final String POST_CONTENT = "POST_CONTENT";

    String categoriesString = "", category_mem = "";
    EditText createTitle, createContent;
    List<Post> posts;
    List<CheckBox> categoryCheckBox = new ArrayList<>();
    DatabaseReference databasePosts, databaseCategories;
    Date currentTime = Calendar.getInstance().getTime();
    ArrayList<Category> categories = new ArrayList<>();
    LinearLayout checkBoxContainer;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        databasePosts = FirebaseDatabase.getInstance().getReference("posts");
        databaseCategories = FirebaseDatabase.getInstance().getReference("category");
        createTitle = findViewById(R.id.createTitle);
        createContent = findViewById(R.id.createContent);
        checkBoxContainer = findViewById(R.id.checkBoxContainer);
        scrollView = findViewById(R.id.scrollView);
        posts = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseCategories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categories.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting post
                    Category per_category = postSnapshot.getValue(Category.class);
                    //adding post to the list
                    categories.add(per_category);
                }
                onCheckboxClicked();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


    }


    public void onCheckboxClicked() {
        /*Creates Checkbox dynamically*/

        for(int i = 0; i<categories.size(); i++){
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(categories.get(i).getCategoryName());
            checkBoxContainer.addView(cb);
            categoryCheckBox.add(cb);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j<categories.size(); j++){
                        if(categoryCheckBox.get(j).isChecked())
                            categoriesString = categories.get(j).getCategoryName() + " ";
                    }
                    category_mem = category_mem + categoriesString;
                    System.out.println(currentTime.toString());
                }
            });
        }
    }

    public void createPost(View v) {
        String title = createTitle.getText().toString();
        String content = createContent.getText().toString();
                if (!(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))) {
                    String id = databasePosts.push().getKey();
                    Post post = new Post(id, title, category_mem, currentTime, content);
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

    public void Back(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    }

