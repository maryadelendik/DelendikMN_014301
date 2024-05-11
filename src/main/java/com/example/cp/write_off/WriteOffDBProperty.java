package com.example.cp.write_off;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class WriteOffDBProperty {

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty lot_material;
    private final SimpleIntegerProperty quantity;
    private final SimpleIntegerProperty production_order;
    private final SimpleStringProperty date;
    private final SimpleFloatProperty price_item;
    private final SimpleFloatProperty total_price;
    private final SimpleStringProperty type;
    private final SimpleStringProperty lot;
    private final SimpleIntegerProperty reject;


    public WriteOffDBProperty(WriteOffDB writeOffDB) {
        id = new SimpleIntegerProperty(writeOffDB.getId());
        quantity = new SimpleIntegerProperty(writeOffDB.getQuantity());
        lot_material = new SimpleIntegerProperty(writeOffDB.getLot_material());
        total_price = new SimpleFloatProperty(writeOffDB.getTotal_price());
        date = new SimpleStringProperty(writeOffDB.getDate());
        price_item = new SimpleFloatProperty(writeOffDB.getPrice_item());
        production_order = new SimpleIntegerProperty(writeOffDB.getProduction_order());
        type = new SimpleStringProperty(writeOffDB.getType());
        lot = new SimpleStringProperty(writeOffDB.getLot());
        reject = new SimpleIntegerProperty(writeOffDB.getReject());
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

    public int getReject() {
        return reject.get();
    }

    public SimpleIntegerProperty rejectProperty() {
        return reject;
    }

    public void setReject(int reject) {
        this.reject.set(reject);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    public int getLot_material() {
        return lot_material.get();
    }

    public SimpleIntegerProperty lot_materialProperty() {
        return lot_material;
    }

    public void setLot_material(int lot_material) {
        this.lot_material.set(lot_material);
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

    public int getProduction_order() {
        return production_order.get();
    }

    public SimpleIntegerProperty production_orderProperty() {
        return production_order;
    }

    public void setProduction_order(int production_order) {
        this.production_order.set(production_order);
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

    public float getPrice_item() {
        return price_item.get();
    }

    public SimpleFloatProperty price_itemProperty() {
        return price_item;
    }

    public void setPrice_item(float price_item) {
        this.price_item.set(price_item);
    }

    public float getTotal_price() {
        return total_price.get();
    }

    public SimpleFloatProperty total_priceProperty() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price.set(total_price);
    }
}
