package com.example.cp.supply_documents;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SupplyDocumentsDBProperty {

    private final SimpleIntegerProperty quantity;
    private final SimpleIntegerProperty mat_sup;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty number;
    private final SimpleFloatProperty price;
    private final SimpleStringProperty material;
    private final SimpleStringProperty supplier;
    private final SimpleStringProperty date;



    public SupplyDocumentsDBProperty(SupplyDocumentsDB supplyDocumentsDB) {
        id = new SimpleIntegerProperty(supplyDocumentsDB.getId());
        quantity = new SimpleIntegerProperty(supplyDocumentsDB.getQuantity());
        mat_sup = new SimpleIntegerProperty(supplyDocumentsDB.getMat_sup());
        number = new SimpleStringProperty(supplyDocumentsDB.getNumber());
        price = new SimpleFloatProperty(supplyDocumentsDB.getPrice());
        material = new SimpleStringProperty(supplyDocumentsDB.getMaterial());
        supplier = new SimpleStringProperty(supplyDocumentsDB.getSupplier());
        date = new SimpleStringProperty(supplyDocumentsDB.getDate());

    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getMat_sup() {
        return mat_sup.get();
    }

    public SimpleIntegerProperty mat_supProperty() {
        return mat_sup;
    }

    public void setMat_sup(int mat_sup) {
        this.mat_sup.set(mat_sup);
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

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public float getPrice() {
        return price.get();
    }

    public SimpleFloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public String getMaterial() {
        return material.get();
    }

    public SimpleStringProperty materialProperty() {
        return material;
    }

    public void setMaterial(String material) {
        this.material.set(material);
    }

    public String getSupplier() {
        return supplier.get();
    }

    public SimpleStringProperty supplierProperty() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier.set(supplier);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
