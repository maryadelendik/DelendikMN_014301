package com.example.cp.prices;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PricesDBProperty {

    private final SimpleIntegerProperty material;
    private final SimpleIntegerProperty supplier;
    private final SimpleIntegerProperty id;

    private final SimpleFloatProperty price;
    private final SimpleStringProperty mat_name;
    private final SimpleStringProperty sup_name;



    public PricesDBProperty(PricesDB pricesDB) {
        id = new SimpleIntegerProperty(pricesDB.getId());
        material = new SimpleIntegerProperty(pricesDB.getMaterial());
        supplier = new SimpleIntegerProperty(pricesDB.getSupplier());
        price = new SimpleFloatProperty(pricesDB.getPrice());
        mat_name = new SimpleStringProperty(pricesDB.getMat_name());
        sup_name = new SimpleStringProperty(pricesDB.getSup_name());

    }

    public int getMaterial() {
        return material.get();
    }

    public SimpleIntegerProperty materialProperty() {
        return material;
    }

    public void setMaterial(int material) {
        this.material.set(material);
    }

    public int getSupplier() {
        return supplier.get();
    }

    public SimpleIntegerProperty supplierProperty() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier.set(supplier);
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

    public float getPrice() {
        return price.get();
    }

    public SimpleFloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public String getMat_name() {
        return mat_name.get();
    }

    public SimpleStringProperty mat_nameProperty() {
        return mat_name;
    }

    public void setMat_name(String mat_name) {
        this.mat_name.set(mat_name);
    }

    public String getSup_name() {
        return sup_name.get();
    }

    public SimpleStringProperty sup_nameProperty() {
        return sup_name;
    }

    public void setSup_name(String sup_name) {
        this.sup_name.set(sup_name);
    }
}
