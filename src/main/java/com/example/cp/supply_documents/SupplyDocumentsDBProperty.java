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
    private final SimpleFloatProperty price_item;
    private final SimpleIntegerProperty month_leftovers;
    private final SimpleStringProperty lot;
    private final SimpleIntegerProperty current_stock;
    private final SimpleIntegerProperty write_off;


    public SupplyDocumentsDBProperty(SupplyDocumentsDB supplyDocumentsDB) {
        id = new SimpleIntegerProperty(supplyDocumentsDB.getId());
        quantity = new SimpleIntegerProperty(supplyDocumentsDB.getQuantity());
        mat_sup = new SimpleIntegerProperty(supplyDocumentsDB.getMat_sup());
        number = new SimpleStringProperty(supplyDocumentsDB.getNumber());
        price = new SimpleFloatProperty(supplyDocumentsDB.getPrice());
        material = new SimpleStringProperty(supplyDocumentsDB.getMaterial());
        supplier = new SimpleStringProperty(supplyDocumentsDB.getSupplier());
        date = new SimpleStringProperty(supplyDocumentsDB.getDate());
        price_item = new SimpleFloatProperty(supplyDocumentsDB.getPrice_item());
        month_leftovers = new SimpleIntegerProperty(supplyDocumentsDB.getMonth_leftovers());
        lot = new SimpleStringProperty(supplyDocumentsDB.getLot());
        current_stock = new SimpleIntegerProperty(supplyDocumentsDB.getCurrent_stock());
        write_off= new SimpleIntegerProperty(supplyDocumentsDB.getWrite_off());
    }

    public int getWrite_off() {
        return write_off.get();
    }

    public SimpleIntegerProperty write_offProperty() {
        return write_off;
    }

    public void setWrite_off(int write_off) {
        this.write_off.set(write_off);
    }

    public float getPrice_item() {
        return price_item.get();
    }

    public SimpleFloatProperty price_itemProperty() {
        return price_item;
    }

    public void setPrice_item(float price_item) {
        this.price_item.set(price_item);
    }

    public int getMonth_leftovers() {
        return month_leftovers.get();
    }

    public SimpleIntegerProperty month_leftoversProperty() {
        return month_leftovers;
    }

    public void setMonth_leftovers(int month_leftovers) {
        this.month_leftovers.set(month_leftovers);
    }

    public String getLot() {
        return lot.get();
    }

    public SimpleStringProperty lotProperty() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot.set(lot);
    }

    public int getCurrent_stock() {
        return current_stock.get();
    }

    public SimpleIntegerProperty current_stockProperty() {
        return current_stock;
    }

    public void setCurrent_stock(int current_stock) {
        this.current_stock.set(current_stock);
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
