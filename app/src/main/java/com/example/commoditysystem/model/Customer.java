package com.example.commoditysystem.model;

public class Customer {
    private int avatar;
    private String customer_name;
    private String customer_phone;
    private String customer_address;
    private String the_last_time;
    private String customer_create_date;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getThe_last_time() {
        return the_last_time;
    }

    public void setThe_last_time(String the_last_time) {
        this.the_last_time = the_last_time;
    }

    public String getCustomer_create_date() {
        return customer_create_date;
    }

    public void setCustomer_create_date(String customer_create_date) {
        this.customer_create_date = customer_create_date;
    }

    public Customer(int avatar, String customer_name, String customer_phone, String customer_address, String the_last_time, String customer_create_date) {
        this.avatar = avatar;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.the_last_time = the_last_time;
        this.customer_create_date = customer_create_date;
    }

    public Customer() {
    }
}
