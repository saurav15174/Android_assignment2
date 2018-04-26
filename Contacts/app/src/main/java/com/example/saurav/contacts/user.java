package com.example.saurav.contacts;

/**
 * Created by saurav on 31/3/18.
 */

public class user {
    private  String name;
    private  String e_mail;
    private  String Phone;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public  String getE_mail() {
        return e_mail;
    }
}
