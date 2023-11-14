package com.example.the_bloom_room.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.the_bloom_room.Class.UserClass;
import com.example.the_bloom_room.DB.DBHelper;
import com.example.the_bloom_room.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class registerActivity extends AppCompatActivity {

    FloatingActionButton ActionButtonHome;
    TextView TextViewSignIn;

    //
    EditText EditTextUserName,EditTextPassword,EditTextConfPassword;
    Spinner SpinnerUserType;
    Button ButtonRegister;

    String UserType[]={"User","Admin"};

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ////////////////////<
        ActionButtonHome=(FloatingActionButton) findViewById(R.id.btnActionHome_RP);
        TextViewSignIn=(TextView) findViewById(R.id.txtSignIn_RP);

        ActionButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(registerActivity.this,MainActivity.class);
                startActivity(intentHome);
            }
        });

        TextViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(registerActivity.this,loginActivity.class);
                startActivity(intentHome);
            }
        });
        ////////////////////////>


        EditTextUserName=(EditText) findViewById(R.id.txtUsername_RP);
        EditTextPassword=(EditText) findViewById(R.id.txtPassword_RP);
        EditTextConfPassword=(EditText) findViewById(R.id.txtConfirmPassword_RP);
        SpinnerUserType=(Spinner) findViewById(R.id.spnUserType_RP);
        ButtonRegister=(Button) findViewById(R.id.btnRegister_RP);

        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_item,UserType);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerUserType.setAdapter(ad);


        ///////////////////////////
        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();


        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<UserClass> usernameAvailable=dbHelper.UsernameAvailable(EditTextUserName.getText().toString());


                if (EditTextUserName.getText().toString().isEmpty()||EditTextPassword.getText().toString().isEmpty()||EditTextConfPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Field's can't be empty", Toast.LENGTH_SHORT).show();
                } else if (EditTextPassword.getText().toString().length()<5)
                {
                    Toast.makeText(getApplicationContext(), "Password must have minimum 5 characters", Toast.LENGTH_SHORT).show();
                } else if (!EditTextPassword.getText().toString().equals(EditTextConfPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password and confirm password does not match", Toast.LENGTH_SHORT).show();
                } else if (usernameAvailable.size()!=0) {
                    Toast.makeText(getApplicationContext(), "Username already exists. Please use different username to register", Toast.LENGTH_SHORT).show();
                } else
                {

                    UserClass userClass=new UserClass(EditTextUserName.getText().toString(),EditTextPassword.getText().toString(),SpinnerUserType.getSelectedItem().toString());

                    if (dbHelper.RegisterUser(userClass))
                    {
                        Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intentUserHome=new Intent(registerActivity.this,userHome.class);
                        startActivity(intentUserHome);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "registration failed", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}