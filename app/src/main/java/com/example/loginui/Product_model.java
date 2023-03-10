package com.example.loginui;

public class Product_model {
    String product_name, product_desc, product_date, product_price, product_quantity, imageid;

    public Product_model(String product_name, String product_desc, String product_date, String product_price, String product_quantity, String imageid) {
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_date = product_date;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.imageid = imageid;
    }

    public Product_model() {
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_date() {
        return product_date;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }
}