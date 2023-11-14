package com.example.the_bloom_room.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_bloom_room.R;

public class adminHome extends AppCompatActivity {

    ImageView ImageLogOut,ImageCategory,ImageProduct,ImageFlower,ImageUser;
    TextView txtLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        ImageLogOut=(ImageView) findViewById(R.id.imgLogoutA);
        txtLogout=(TextView) findViewById(R.id.txtLogoutA);
        ImageCategory=(ImageView) findViewById(R.id.imgCategoryA);
        ImageProduct=(ImageView) findViewById(R.id.imgProductA);
        ImageUser=(ImageView) findViewById(R.id.imgUserA);
        ImageFlower=(ImageView) findViewById(R.id.imgFlowerA);

        ImageLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogout=new Intent(adminHome.this,MainActivity.class);
                startActivity(intentLogout);

            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogout=new Intent(adminHome.this,MainActivity.class);
                startActivity(intentLogout);

            }
        });

        ImageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCategory=new Intent(adminHome.this, categoryActivity.class);
                startActivity(intentCategory);

            }
        });

        ImageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentProduct=new Intent(adminHome.this, ViewProductActivity.class);
                startActivity(intentProduct);

            }
        });

        ImageFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentProduct=new Intent(adminHome.this, productActivity.class);
                startActivity(intentProduct);

            }
        });


        ImageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentProduct=new Intent(adminHome.this, addUser.class);
                startActivity(intentProduct);
            }
        });



    }
}