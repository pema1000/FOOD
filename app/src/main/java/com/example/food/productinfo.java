package com.example.food;


public class productinfo {

    String ProductName, ProductAmount, ProductDoe,Idnumber;
    String ProductImage;

    public productinfo() {

    }

    public productinfo(String productName, String productAmount, String productDoe, String idnumber,String productImage) {
        ProductName = productName;
        ProductAmount = productAmount;
        ProductDoe = productDoe;
        Idnumber = idnumber;
        ProductImage=productImage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductAmount() {
        return ProductAmount;
    }

    public void setProductAmount(String productAmount) {
        ProductAmount = productAmount;
    }

    public String getProductDoe() {
        return ProductDoe;
    }

    public void setProductDoe(String productDoe) {
        ProductDoe = productDoe;
    }

    public String getIdnumber() {
        return Idnumber;
    }

    public void setIdnumber(String idnumber) {
        Idnumber = idnumber;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }
 }

