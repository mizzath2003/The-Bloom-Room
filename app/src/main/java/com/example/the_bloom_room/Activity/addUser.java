package com.example.the_bloom_room.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_bloom_room.Class.UserClass;
import com.example.the_bloom_room.DB.DBHelper;
import com.example.the_bloom_room.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class addUser extends AppCompatActivity {

    FloatingActionButton ActionButtonHome;
    TextView TextViewSignIn;

    //
    EditText EditTextUserName,EditTextPassword,EditTextConfPassword;
    Spinner SpinnerUserType;
    Button ButtonRegister,ButtonDelete;

    String UserType[]={"User","Admin"};

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        ////////////////////<
        ActionButtonHome=(FloatingActionButton) findViewById(R.id.btnAction_RP2);

        ActionButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome=new Intent(addUser.this,adminHome.class);
                startActivity(intentHome);
            }
        });
        ////////////////////////>
        EditTextUserName=(EditText) findViewById(R.id.txtUsername_RP2);
        EditTextPassword=(EditText) findViewById(R.id.txtPassword_RP2);
        EditTextConfPassword=(EditText) findViewById(R.id.txtConfirmPassword_RP2);
        SpinnerUserType=(Spinner) findViewById(R.id.spnUserType_RP2);
        ButtonRegister=(Button) findViewById(R.id.btnRegister_RP2);
        ButtonDelete=(Button) findViewById(R.id.btnDelete_RP);

        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_item,UserType);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerUserType.setAdapter(ad);

        ///////////////////////////
        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();


        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                    if (dbHelper.AddUser(userClass))
                    {
                        Toast.makeText(getApplicationContext(), "User added successful", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "registration failed", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


        ButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper.DeleteUser(EditTextUserName.getText().toString());

                if (EditTextUserName.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter username to delete the user", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getApplicationContext(), "Deleted " + EditTextUserName.getText().toString() + " Successfully", Toast.LENGTH_LONG).show();

                }



            }
        });

    }
}