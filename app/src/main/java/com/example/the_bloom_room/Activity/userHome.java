package com.example.the_bloom_room.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_bloom_room.R;

public class userHome extends AppCompatActivity {

    ImageView ImageLogOut,ImageFlower;
    TextView txtLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        ImageLogOut=(ImageView) findViewById(R.id.imgLogoutU);
        ImageFlower=(ImageView) findViewById(R.id.imgViewFlowerU);
        txtLogout=(TextView) findViewById(R.id.txtLogoutU);

        ImageLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogout=new Intent(userHome.this,MainActivity.class);
                startActivity(intentLogout);

            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogout=new Intent(userHome.this,MainActivity.class);
                startActivity(intentLogout);

            }
        });

        ImageFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFlower=new Intent(userHome.this,ViewProductActivityU.class);
                startActivity(intentFlower);
            }
        });




    }
}