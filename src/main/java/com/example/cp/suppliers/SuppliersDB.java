package com.example.cp.suppliers;

import java.io.Serializable;

public class SuppliersDB implements Serializable {
    private Integer id;
    private String name;
    private String phone_number;
    private String email;
    private String address;


    public SuppliersDB(String name, String phone_number, String email, String address) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
    }

    public SuppliersDB(Integer id,String name, String phone_number, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
    }

   public SuppliersDB() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuppliersDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
