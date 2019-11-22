package com.example.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateCategoryActivity extends AppCompatActivity {

    TextView textViewCategoryName;
    EditText editTextCategory;
    Button buttonAdd, buttonBack;

    DatabaseReference databaseCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        databaseCategories = FirebaseDatabase.getInstance().getReference("category");
        textViewCategoryName = (TextView) findViewById(R.id.textViewCategoryName);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
            }
        });

    }


    public void addCategory(){

        String name = editTextCategory.getText().toString();

        //If edit text is not empty then get the value, up sa db, and display a Toast
        if(!TextUtils.isEmpty(name)){

            String id = databaseCategories.push().getKey();

            Category category = new Category(name);

            databaseCategories.child(id).setValue(category);

            editTextCategory.setText("");

            Toast.makeText(this, "Category Added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Please enter a category", Toast.LENGTH_LONG).show();
        }
    }
}
