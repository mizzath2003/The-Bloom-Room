package com.example.the_bloom_room.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.the_bloom_room.R;

public class MainActivity extends AppCompatActivity {


    Button ButtonRegister,ButtonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonRegister=(Button) findViewById(R.id.btnRegister_home);
        ButtonSignIn=(Button) findViewById(R.id.btnSignIn_home);

        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister=new Intent(MainActivity.this,registerActivity.class);
                startActivity(intentRegister);
            }
        });

        ButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignIn=new Intent(MainActivity.this,loginActivity.class);
                startActivity(intentSignIn);
            }
        });

    }
}