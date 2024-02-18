package com.example.cp.suppliers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SuppliersDBProperty {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;

    private final SimpleStringProperty phone_number;
    private final SimpleStringProperty email;
    private final SimpleStringProperty address;



    public SuppliersDBProperty(SuppliersDB suppliersDB) {
        id = new SimpleIntegerProperty(suppliersDB.getId());
        name = new SimpleStringProperty(suppliersDB.getName());
        phone_number = new SimpleStringProperty(suppliersDB.getPhone_number());
        email = new SimpleStringProperty(suppliersDB.getEmail());
        address = new SimpleStringProperty(suppliersDB.getAddress());

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public SimpleStringProperty phone_numberProperty() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.set(phone_number);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}
