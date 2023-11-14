package com.example.the_bloom_room.Class;

public class ProductClass {

    private String ProductId;
    private String ProductName;
    private String ProductDescription;
    private String CategoryID;
    private int Price;
    private int Quantity;

    public ProductClass(){}

    public ProductClass(String productId, String productName, String productDescription, String categoryID, int price, int quantity) {
        ProductId = productId;
        ProductName = productName;
        ProductDescription = productDescription;
        CategoryID = categoryID;
        Price = price;
        Quantity = quantity;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
