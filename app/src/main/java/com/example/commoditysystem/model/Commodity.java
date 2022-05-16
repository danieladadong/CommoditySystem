package com.example.commoditysystem.model;

public class Commodity {
    private int avatar;
    private String commodity_name;
    private String commodity_number;
    private String commodity_price;
    private String create_date;
    private String commodity_suppers;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_number() {
        return commodity_number;
    }

    public void setCommodity_number(String commodity_number) {
        this.commodity_number = commodity_number;
    }

    public String getCommodity_price() {
        return commodity_price;
    }

    public void setCommodity_price(String commodity_price) {
        this.commodity_price = commodity_price;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCommodity_suppers() {
        return commodity_suppers;
    }

    public void setCommodity_suppers(String commodity_suppers) {
        this.commodity_suppers = commodity_suppers;
    }

    public Commodity(int avatar, String commodity_name, String commodity_number, String commodity_price, String create_date, String commodity_suppers) {
        this.avatar = avatar;
        this.commodity_name = commodity_name;
        this.commodity_number = commodity_number;
        this.commodity_price = commodity_price;
        this.create_date = create_date;
        this.commodity_suppers = commodity_suppers;
    }

    public Commodity() {
    }
}
