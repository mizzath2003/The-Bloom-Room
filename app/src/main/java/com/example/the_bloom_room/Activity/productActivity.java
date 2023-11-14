package com.example.the_bloom_room.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.the_bloom_room.Class.ProductClass;
import com.example.the_bloom_room.DB.DBHelper;
import com.example.the_bloom_room.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.Vector;

public class productActivity extends AppCompatActivity {

    FloatingActionButton ButtonActionBack;
    EditText EditTextProductId,EditTextProductName,EditTextDescription, EditTextPrice, EditTextQuantity;
    ImageView ImageViewAddImage;
    ImageView imageViewCamera, ImageViewGallery;
    Spinner SpinnerCategoryName;
    Button ButtonAddProduct,ButtonDelete;

    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ButtonActionBack=(FloatingActionButton) findViewById(R.id.btnAction_PR);

        ButtonActionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentActionBack=new Intent(productActivity.this,adminHome.class);
                startActivity(intentActionBack);
            }
        });

        ///////////////////////
        EditTextProductId=(EditText) findViewById(R.id.txtProductId_PR);
        EditTextProductName=(EditText) findViewById(R.id.txtProductName_PR);
        EditTextDescription=(EditText) findViewById(R.id.txtDescription_PR);
        EditTextPrice=(EditText) findViewById(R.id.txtPrice_PR);
        EditTextQuantity=(EditText) findViewById(R.id.txtQuantity_PR);
        SpinnerCategoryName=(Spinner) findViewById(R.id.spCategory_PR);
        ImageViewAddImage=(ImageView) findViewById(R.id.imgAdd_PR);
        ButtonAddProduct=(Button) findViewById(R.id.btnAdd_PR);
        ButtonDelete=(Button) findViewById(R.id.btnDelete_PR2);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        Vector<String> vecCategory=dbHelper.getCategory_Name();

        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_item,vecCategory);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCategoryName.setAdapter(ad);

        //function when the image icon is clicked
        ImageViewAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChooseProfilePicture();

            }
        });


        ButtonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EditTextProductName.getText().toString().isEmpty() || EditTextDescription.getText().toString().isEmpty() || EditTextPrice.getText().toString().isEmpty() || EditTextQuantity.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fields can't be blank", Toast.LENGTH_LONG).show();
                }
                else
                {


                    byte[] byteSSP=convertImageToByteArray(ImageViewAddImage);
                    String ProductId=EditTextProductId.getText().toString();
                    String imageName=EditTextProductName.getText().toString();
                    String description=EditTextDescription.getText().toString();
                    String CategoryId = dbHelper.getCategory_Id(SpinnerCategoryName.getSelectedItem().toString());
                    int Price= Integer.parseInt(EditTextPrice.getText().toString());
                    int Quantity= Integer.parseInt(EditTextQuantity.getText().toString());


                    ProductClass product = new ProductClass(EditTextProductId.getText().toString(),EditTextProductName.getText().toString(), EditTextDescription.getText().toString(), CategoryId, Integer.parseInt(EditTextPrice.getText().toString()), Integer.parseInt(EditTextQuantity.getText().toString()));

//                    ArrayList<ProductClass> ProductAvailable=dbHelper.ProductAvailable(EditTextProductName.getText().toString());

                    if (dbHelper.save(CategoryId,Quantity,Price,description,imageName,ProductId,byteSSP))
                    {
                        Toast.makeText(getApplicationContext(), "Product Inserted successfully", Toast.LENGTH_SHORT).show();
                    }
//                    else if (dbHelper.AddProduct(product))
//                    {
//                        Toast.makeText(getApplicationContext(), "Product Inserted successfully", Toast.LENGTH_LONG).show();
//                    }
//                    else if (ProductAvailable.size()!=0) {
//                        Toast.makeText(getApplicationContext(), "Product already exists. Please use different product name", Toast.LENGTH_SHORT).show();
//
//                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Failed to insert Product", Toast.LENGTH_LONG).show();
                    }


                }

            }
        });

        ButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbHelper.DeleteProduct(EditTextProductName.getText().toString());

                if (EditTextProductName.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter product name to delete the product", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getApplicationContext(), "Deleted " + EditTextProductName.getText().toString() + " Successfully", Toast.LENGTH_LONG).show();

                }

            }
        });



    }

    //used when retrieving the image
    private Bitmap convertByteArrayToBitmap(byte[] bytes)
    {
        return BitmapFactory.decodeByteArray(bytes,0, bytes.length);
    }

    //used when inserting the image
    private byte[] convertImageToByteArray(ImageView imageView)
    {
        Bitmap bitmap=((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void ChooseProfilePicture(){
        AlertDialog.Builder builder = new AlertDialog.Builder(productActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_picture,null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        imageViewCamera = dialogView.findViewById(R.id.imageViewDPPCamera);
        ImageViewGallery = dialogView.findViewById(R.id.imageViewDPPGallery);

        final  AlertDialog alertDialogProfilePicture = builder.create();
        alertDialogProfilePicture.show();


        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAndRequestPermission()){

                    takePictureFromCamera();
                    alertDialogProfilePicture.cancel();
                }
            }
        });

        ImageViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureFromGallery();
                alertDialogProfilePicture.cancel();
            }
        });

        /////
    }


    private boolean checkAndRequestPermission(){
        // 23 means API version on our device
        if (Build.VERSION.SDK_INT>23){
            //In here we gave permission on the manifest file
            int cameraPermission = ActivityCompat.checkSelfPermission(productActivity.this, Manifest.permission.CAMERA);

            if (cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(productActivity.this, new String[]{Manifest.permission.CAMERA},20);
                return false;
            }
        }
        return true;
    }

    private void takePictureFromCamera(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture,2);
        }
    }

    private void takePictureFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        //not a user defined method
        super.onActivityResult(requestCode,resultCode,data);
        {
            switch (requestCode)
            {
                case 1:
                    if (resultCode==RESULT_OK)
                    {
                        Uri selectImageUri=data.getData();
                        ImageViewAddImage.setImageURI(selectImageUri);
                    }
                    break;
                case 2:
                    if (resultCode ==RESULT_OK)
                    {
                        Bundle bundle=data.getExtras();
                        Bitmap bitmapImage=(Bitmap) bundle.get("data");
                        ImageViewAddImage.setImageBitmap(bitmapImage);
                    }
                    break;
            }

        }

    }




}