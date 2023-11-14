package com.example.the_bloom_room.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_bloom_room.Class.ProductClass;
import com.example.the_bloom_room.DB.DBHelper;
import com.example.the_bloom_room.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewProductActivityU extends AppCompatActivity {

    ListView ListViewProduct;

    FloatingActionButton ActionButton;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_u);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        ListViewProduct=(ListView) findViewById(R.id.lst_L_Product);
        ActionButton=(FloatingActionButton) findViewById(R.id.btnAction_FPU);

        ActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAction=new Intent(ViewProductActivityU.this,userHome.class);
                startActivity(intentAction);
            }
        });

        //Array to List the Products
        ArrayList<String> theList=new ArrayList<>();
        Cursor cursor=dbHelper.SearchAllProduct();

        if (cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "No Product Found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                theList.add(cursor.getString(0));

                ListAdapter listAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);//theList is the array name
                ListViewProduct.setAdapter(listAdapter);
            }
        }

        //To initialize the Function of the ListView
        ListViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) { //position is 0

                String productId = "P00" + String.valueOf(position+1);//0+1 = 1 = P001

                ArrayList<ProductClass> productList= dbHelper.SearchProduct(productId); //P001

                if (productList.size()!=0)
                {
                    Intent intentViewList = new Intent(ViewProductActivityU.this,ViewProductInfoActivityU.class);

                    ProductClass product= productList.get(0);
                    intentViewList.putExtra("productId",product.getProductId());
                    intentViewList.putExtra("productName",product.getProductName());//P001
                    intentViewList.putExtra("description",product.getProductDescription());//P001
                    intentViewList.putExtra("Price",String.valueOf(product.getPrice()));
                    intentViewList.putExtra("Quantity",String.valueOf(product.getQuantity()));

                    startActivity(intentViewList);

                }



            }
        });



    }
}