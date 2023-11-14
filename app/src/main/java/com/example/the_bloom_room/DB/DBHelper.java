package com.example.the_bloom_room.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.the_bloom_room.Class.CategoryClass;
import com.example.the_bloom_room.Class.InvoiceClass;
import com.example.the_bloom_room.Class.ProductClass;
import com.example.the_bloom_room.Class.UserClass;

import java.util.ArrayList;
import java.util.Vector;

public class DBHelper {

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context)
    {
        this.context=context;
    }


    public DBHelper OpenDB()
    {
        DBConnector dbCon=new DBConnector(context);
        sqLiteDatabase=dbCon.getWritableDatabase();
        sqLiteDatabase = dbCon.getReadableDatabase();
        return this;
    }


    //Register User
    public boolean RegisterUser(UserClass userClass)
     {
         try {
             sqLiteDatabase.execSQL("Insert into tb_user (UserName,Password,UserType) values('"+userClass.getUserName()+"','"+userClass.getPassword()+"','User')");
             return true;
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
             return false;
         }

     }

    //Add User
    public boolean AddUser(UserClass userClass)
    {
        try {
            sqLiteDatabase.execSQL("Insert into tb_user (UserName,Password,UserType) values('"+userClass.getUserName()+"','"+userClass.getPassword()+"','"+userClass.getUserType()+"')");
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }

    }

     //Check if username available
     public ArrayList<UserClass> UsernameAvailable(String UserName)
     {
         ArrayList<UserClass> accountList=new ArrayList<>();

         try
         {
             Cursor cursor=sqLiteDatabase.rawQuery(" Select * from tb_user where UserName='"+UserName+"'", null );
             if (cursor.moveToFirst())
             {
                 do
                 {
                     UserClass user=new UserClass();
                     user.setUserName(cursor.getString(1));
                     user.setUserType(cursor.getString(3));
                     accountList.add(user);
                 }
                 while (cursor.moveToNext());
             }
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
         }
         return accountList;
     }




     //Login User
    public ArrayList<UserClass> ValidLogin(String UserName,String Password)
    {
        ArrayList<UserClass> userList=new ArrayList<>();

        try
        {
            Cursor cursor=sqLiteDatabase.rawQuery(" Select * from tb_user where UserName='"+UserName+"' and Password='"+Password+"'", null );
            if (cursor.moveToFirst())
            {
                do
                {
                    UserClass user=new UserClass();
                    user.setUserName(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setUserType(cursor.getString(3));
                    userList.add(user);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return userList;
    }

    //Inserting Category
    public boolean AddCategory(CategoryClass categoryClass)
    {

        try
        {
            sqLiteDatabase.execSQL("Insert into tb_category values ('"+ categoryClass.getCategoryID()+"','"+ categoryClass.getCategoryName()+"')");
            return true;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }

    }

    //Get the Category name for the Spinner of Product
    public Vector<String> getCategory_Name()
    {
        Vector<String> vecCategory=new Vector<>();

        try
        {
            Cursor cursor=sqLiteDatabase.rawQuery("Select categoryName from tb_category",null);

            if (cursor.moveToFirst())
            {
                do {
                    vecCategory.add(cursor.getString(0));
                }while (cursor.moveToNext());
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return vecCategory;
    }

    //Get the category Id using the category name
    public String getCategory_Id(String CategoryName)
    {
        String CategoryId=null;

        try
        {
            Cursor cursor=sqLiteDatabase.rawQuery("Select categoryId from tb_category where categoryName='"+CategoryName+"'",null);

            if (cursor.moveToFirst())
            {
                CategoryId=cursor.getString(0);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return CategoryId;

    }

//    public boolean AddProduct(ProductClass productClass)
//    {
//        try
//        {
//            sqLiteDatabase.execSQL(" Insert into tb_product (productName,description,Price,Quantity,categoryId) values ('"+ productClass.getProductName()+"','"+ productClass.getProductDescription()+"',"+productClass.getPrice()+","+productClass.getQuantity()+",'"+productClass.getCategoryID()+"') ");
//            return true;
//
//
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//            return false;
//        }
//
//    }

//Add product
    //save the image to the database
    public boolean save(String CategoryId,int Quantity,int Price,String description,String imageName,String ProductId ,byte[] pp)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put("productId",ProductId);
            cv.put("productName",imageName);
            cv.put("description",description);
            cv.put("Price",Price);
            cv.put("Quantity",Quantity);
            cv.put("Image",pp);
            cv.put("categoryId",CategoryId);
            sqLiteDatabase.insert("tb_product",null,cv);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

//    //Check if username available
//    public ArrayList<ProductClass> ProductAvailable(String ProductName)
//    {
//        ArrayList<ProductClass> productList=new ArrayList<>();
//
//        try
//        {
//            Cursor cursor=sqLiteDatabase.rawQuery(" Select * from tb_product where productName='"+ProductName+"'", null );
//            if (cursor.moveToFirst())
//            {
//                do
//                {
//                    ProductClass product=new ProductClass();
//                    product.setProductName(cursor.getString(1));
//                    productList.add(product);
//                }
//                while (cursor.moveToNext());
//            }
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return productList;
//    }



    public ArrayList<ProductClass> SearchProduct(String ProductID) //P001
    {
        ArrayList<ProductClass>productList=new ArrayList<ProductClass>();
        try
        {
            Cursor cursor=sqLiteDatabase.rawQuery("Select * from tb_product where productId='"+ProductID+"' ",null);
            if(cursor.moveToFirst())//F
            {
                do {
                    ProductClass product=new ProductClass();
                    product.setProductId(cursor.getString(0));//p001
                    product.setProductName(cursor.getString(1));//book1
                    product.setProductDescription(cursor.getString(2));//book1
                    product.setPrice(cursor.getInt(3));//100
                    product.setQuantity(cursor.getInt(4));//20
                    product.setCategoryID(cursor.getString(6));//c001
                    productList.add(product);

                }while(cursor.moveToNext());//F
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return productList;
    }

    //Searching Product
    public Cursor SearchAllProduct()
    {
        Cursor cursor = null ;

        try
        {
            cursor=sqLiteDatabase.rawQuery("Select productName from tb_product",null);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return cursor;

    }

    //Insert Invoice
    public boolean InsertInvoice(InvoiceClass invoice)
    {
        try
        {
            sqLiteDatabase.execSQL("insert into tb_invoice(Quantity,Total,productId) values("+invoice.getQty()+","+invoice.getTotal()+",'"+invoice.getProductId()+"')");
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }


    //BuyFlower
    public void BuyProduct(String ProductName,int Qty)
    {
        try
        {
            sqLiteDatabase.execSQL("update tb_product set Quantity = Quantity - " + Qty + " where productName = '"+ProductName+"' ");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //Update the products quantity after buying
    public void DeleteUser(String userName)
    {
        try
        {
            sqLiteDatabase.execSQL("Delete from tb_user where UserName = '"+userName+"' ");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void DeleteProduct(String productName)
    {
        try
        {
            sqLiteDatabase.execSQL("Delete from tb_product where productName = '"+productName+"' ");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


}
