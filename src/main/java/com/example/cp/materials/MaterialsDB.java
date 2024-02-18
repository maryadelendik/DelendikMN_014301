package com.example.cp.materials;

import java.io.Serializable;

public class MaterialsDB implements Serializable {
    private Integer id;
    private String name;
    private String number;
    private String type;
    private Integer stock_quantity;
    private Integer order_quantity;
    private String user_login;

    public MaterialsDB(String user_login) {
        this.user_login = user_login;
    }

    public MaterialsDB(Integer id, String name, String number, String type, Integer stock_quantity, Integer order_quantity) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.type = type;
        this.stock_quantity = stock_quantity;
        this.order_quantity = order_quantity;
    }

   public MaterialsDB() {

    }

    @Override
    public String toString() {
        return "MaterialsDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", stock_quantity=" + stock_quantity +
                ", order_quantity=" + order_quantity +
                ", user_login='" + user_login + '\'' +
                '}';
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public Integer getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(Integer order_quantity) {
        this.order_quantity = order_quantity;
    }
}
