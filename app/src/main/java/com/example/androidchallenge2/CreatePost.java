package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePost extends AppCompatActivity {
    EditText createTitle, createContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        createTitle = findViewById(R.id.createTitle);
        createContent = findViewById(R.id.createContent);
    }


}
