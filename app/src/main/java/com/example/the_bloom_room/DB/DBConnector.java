package com.example.the_bloom_room.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper {

    public DBConnector(Context context){super(context,"bloom_room3",null,1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table tb_user(userId INTEGER  PRIMARY KEY AUTOINCREMENT, UserName VARCHAR, Password VARCHAR,UserType VARCHAR)");
        sqLiteDatabase.execSQL("create table tb_category (categoryId VARCHAR PRIMARY KEY not null, categoryName VARCHAR)");
        sqLiteDatabase.execSQL("create table tb_product (productId VARCHAR PRIMARY KEY  not null, productName VARCHAR, description VARCHAR, Price INTEGER,Quantity INTEGER, Image BLOB, categoryId INTEGER, FOREIGN KEY (categoryId) REFERENCES tb_category (categoryId))");
        sqLiteDatabase.execSQL("create table tb_invoice (invoiceId INTEGER PRIMARY KEY AUTOINCREMENT not null ,Quantity INTEGER, Total INTEGER, productId VARCHAR, FOREIGN KEY(productId) REFERENCES tb_product(productId))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
