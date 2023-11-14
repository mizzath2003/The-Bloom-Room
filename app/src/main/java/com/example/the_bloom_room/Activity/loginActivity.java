package com.example.the_bloom_room.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.the_bloom_room.Class.UserClass;
import com.example.the_bloom_room.DB.DBHelper;
import com.example.the_bloom_room.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class loginActivity extends AppCompatActivity {

    FloatingActionButton ActionButtonHome;
    TextView TextViewRegister;

    EditText EditTextUserName,EditTextPassword;
    Button ButtonSignIn;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionButtonHome=(FloatingActionButton) findViewById(R.id.btnActionHome_LP);
        TextViewRegister=(TextView) findViewById(R.id.txtRegister_LP);

        ActionButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(loginActivity.this,MainActivity.class);
                startActivity(intentHome);
            }
        });

        TextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(loginActivity.this,registerActivity.class);
                startActivity(intentHome);
            }
        });
        ///////////////

        EditTextUserName=(EditText) findViewById(R.id.txtUsername_LP);
        EditTextPassword=(EditText) findViewById(R.id.txtPassword_LP);
        ButtonSignIn=(Button) findViewById(R.id.btnSignIn_LP);

        //to Access the database
        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        ButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<UserClass> userDetails=dbHelper.ValidLogin(EditTextUserName.getText().toString(),EditTextPassword.getText().toString());

                if (userDetails.size()!=0)
                {
                    UserClass user=userDetails.get(0);
                    String userType=user.getUserType();

                    Toast.makeText(getApplicationContext(),userType + " Successfully Signed In",Toast.LENGTH_LONG).show();
                    if (userType.equals("Admin"))
                    {
                        Intent intentAdminHome=new Intent(loginActivity.this,adminHome.class);
                        startActivity(intentAdminHome);

                    }
                    else
                    {
                        Intent intentUserHome=new Intent(loginActivity.this,userHome.class);
                        startActivity(intentUserHome);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}