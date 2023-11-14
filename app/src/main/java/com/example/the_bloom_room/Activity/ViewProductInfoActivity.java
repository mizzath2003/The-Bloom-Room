package com.example.the_bloom_room.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_bloom_room.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewProductInfoActivity extends AppCompatActivity {

    TextView TextViewProductId, TextViewProductName,TextViewProductDescription, TextViewCategoryId, TextViewPrice, TextViewQuantity;
    FloatingActionButton ActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_info);

        ActionButton=(FloatingActionButton) findViewById(R.id.btnAction_FP2);

        ActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAction=new Intent(ViewProductInfoActivity.this,ViewProductActivity.class);
                startActivity(intentAction);
            }
        });



        TextViewProductId= (TextView) findViewById(R.id.tv_VP_ProductId2);
        TextViewProductName= (TextView) findViewById(R.id.tv_VP_ProductName2);
        TextViewProductDescription= (TextView) findViewById(R.id.tv_VP_ProductDescription2);
        TextViewPrice= (TextView) findViewById(R.id.tv_VP_Price2);
        TextViewQuantity= (TextView) findViewById(R.id.tv_VP_Quantity2);
        TextViewCategoryId= (TextView) findViewById(R.id.tv_VP_CategoryId2);

        Intent intent =this.getIntent();

        TextViewProductId.setText(intent.getStringExtra("productId"));
        TextViewProductName.setText( intent.getStringExtra("productName"));
        TextViewProductDescription.setText(intent.getStringExtra("description"));
        TextViewPrice.setText(intent.getStringExtra("Price"));
        TextViewQuantity.setText(intent.getStringExtra("Quantity"));
        TextViewCategoryId.setText( intent.getStringExtra("categoryId"));


    }
}