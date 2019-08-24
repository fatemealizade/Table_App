package com.example.tableapp.model;

public class Item {

    private int id;
    private String itemName;
    private String itemFamily;
    private int itemAge;
    private int itemPhone;
    private String dateItem;

    public Item() {
    }

    public Item(String itemName, String itemFamily, int itemAge, int itemPhone, String dateItem) {
        this.itemName = itemName;
        this.itemFamily = itemFamily;
        this.itemAge = itemAge;
        this.itemPhone = itemPhone;
        this.dateItem = dateItem;
    }

    public Item(int id, String itemName, String itemFamily, int itemAge, int itemPhone, String dateItem) {
        this.id = id;
        this.itemName = itemName;
        this.itemFamily = itemFamily;
        this.itemAge = itemAge;
        this.itemPhone = itemPhone;
        this.dateItem = dateItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemFamily() {
        return itemFamily;
    }

    public void setItemFamily(String itemFamily) {
        this.itemFamily = itemFamily;
    }

    public int getItemAge() {
        return itemAge;
    }

    public void setItemAge(int itemAge) {
        this.itemAge = itemAge;
    }

    public int getItemPhone() {
        return itemPhone;
    }

    public void setItemPhone(int itemPhone) {
        this.itemPhone = itemPhone;
    }
    public void setDateItem(String formattedDate){
        this.dateItem = dateItem;
    }

    public String getDateItem(){
        return dateItem;
    }

}

