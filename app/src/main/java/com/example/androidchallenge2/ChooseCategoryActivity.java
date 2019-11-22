package com.example.androidchallenge2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseCategoryActivity extends AppCompatActivity {

    public static final String CATEGORY_NAME = "CATEGORY_NAME";

    ListView listViewCategories;
    DatabaseReference databaseCategories;
    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        listViewCategories = findViewById(R.id.listViewCategories);
        databaseCategories = FirebaseDatabase.getInstance().getReference("category");

        categories = new ArrayList<>();

        listViewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Category category = categories.get(i);

                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                intent.putExtra(CATEGORY_NAME,category.getCategoryName());
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        databaseCategories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categories.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting post
                    Category category = postSnapshot.getValue(Category.class);
                    //adding post to the list
                    categories.add(category);

                }

                PostList postAdapter = new PostList(MainActivity.this,categories);
                listViewCategories.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
