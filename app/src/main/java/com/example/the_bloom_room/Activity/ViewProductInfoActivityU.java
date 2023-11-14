package com.example.the_bloom_room.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_bloom_room.Class.InvoiceClass;
import com.example.the_bloom_room.DB.DBHelper;
import com.example.the_bloom_room.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewProductInfoActivityU extends AppCompatActivity {

    TextView  TextViewProductName, TextViewDescription, TextViewPrice, TextViewQuantity;
    EditText EditTextBuyQuantity;
    Button ButtonBuyProduct;

    FloatingActionButton ActionButton;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_info_u);

        ActionButton=(FloatingActionButton) findViewById(R.id.btnAction_FP4);

        ActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAction=new Intent(ViewProductInfoActivityU.this,ViewProductActivityU.class);
                startActivity(intentAction);
            }
        });

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        TextViewProductName= (TextView) findViewById(R.id.tv_VP_ProductNameU);
        TextViewDescription= (TextView) findViewById(R.id.tv_VP_ProductDescriptionU);
        TextViewPrice= (TextView) findViewById(R.id.tv_VP_PriceU);
        TextViewQuantity= (TextView) findViewById(R.id.tv_VP_QuantityU);
        EditTextBuyQuantity=(EditText) findViewById(R.id.txt_I_BuyQty);
        ButtonBuyProduct=(Button) findViewById(R.id.btn_I_Buy);



        Intent intent =this.getIntent();

        TextViewProductName.setText( intent.getStringExtra("productName"));
        TextViewDescription.setText(intent.getStringExtra("description"));
        TextViewPrice.setText(intent.getStringExtra("Price"));
        TextViewQuantity.setText(intent.getStringExtra("Quantity"));


        ButtonBuyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper.BuyProduct(TextViewProductName.getText().toString(),Integer.parseInt(EditTextBuyQuantity.getText().toString()));

                //calculating the Total
                int Total=Integer.parseInt(EditTextBuyQuantity.getText().toString()) * Integer.parseInt(TextViewPrice.getText().toString());

                //calculating the Quantity remaining
                int qty=Integer.parseInt(EditTextBuyQuantity.getText().toString());
                //the output in the DataBase
                InvoiceClass invoice=new InvoiceClass(TextViewProductName.getText().toString(),qty,Total);

                if (dbHelper.InsertInvoice(invoice))
                {
                    Toast.makeText(getApplicationContext(), "Buy : " + TextViewProductName.getText().toString() + "  Your Total is : "+ Total, Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}