package com.example.the_bloom_room.Class;

public class InvoiceClass {

    private String ProductId;
    private int Qty;
    private int Total;

    public InvoiceClass(String productId, int qty, int total) {
        ProductId = productId;
        Qty = qty;
        Total = total;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }
}
