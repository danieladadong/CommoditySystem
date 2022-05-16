package com.example.commoditysystem.model;

public class Order {
    private int order_avatar;
    private String order_commodity;
    private String order_count;
    private String order_price;
    private String order_money;
    private String order_customer;
    private String create_date;

    public int getOrder_avatar() {
        return order_avatar;
    }

    public void setOrder_avatar(int order_avatar) {
        this.order_avatar = order_avatar;
    }

    public String getOrder_commodity() {
        return order_commodity;
    }

    public void setOrder_commodity(String order_commodity) {
        this.order_commodity = order_commodity;
    }

    public String getOrder_count() {
        return order_count;
    }

    public void setOrder_count(String order_count) {
        this.order_count = order_count;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public String getOrder_customer() {
        return order_customer;
    }

    public void setOrder_customer(String order_customer) {
        this.order_customer = order_customer;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public Order() {
    }



    public Order(int order_avatar, String order_commodity, String order_count, String order_price, String order_money, String order_customer, String create_date) {
        this.order_avatar = order_avatar;
        this.order_commodity = order_commodity;
        this.order_count = order_count;
        this.order_price = order_price;
        this.order_money = order_money;
        this.order_customer = order_customer;
        this.create_date = create_date;
    }
}
