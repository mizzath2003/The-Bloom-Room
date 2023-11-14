package com.example.the_bloom_room.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.the_bloom_room.Class.CategoryClass;
import com.example.the_bloom_room.DB.DBHelper;
import com.example.the_bloom_room.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class categoryActivity extends AppCompatActivity {

    FloatingActionButton ActionCategory;
    EditText EditTextCategoryId,EditTextCategoryName;
    Button ButtonAddCategory;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ActionCategory=(FloatingActionButton) findViewById(R.id.btnAction_PR);
        EditTextCategoryId=(EditText) findViewById(R.id.txtCategoryId_CA);
        EditTextCategoryName=(EditText) findViewById(R.id.txtCategoryName_CA);
        ButtonAddCategory=(Button) findViewById(R.id.btnAdd_CA);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();


        ActionCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCategory=new Intent(categoryActivity.this,adminHome.class);
                startActivity(intentCategory);

            }
        });


        ButtonAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EditTextCategoryId.getText().toString().isEmpty()||EditTextCategoryName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Field's can't be blank", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    CategoryClass categoryClass=new CategoryClass(EditTextCategoryId.getText().toString(),EditTextCategoryName.getText().toString());

                    if (dbHelper.AddCategory(categoryClass)) {
                        Toast.makeText(getApplicationContext(), "Category added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Failed to add category", Toast.LENGTH_SHORT).show();
                    }
                    
                }
                
            }
        });



    }
}