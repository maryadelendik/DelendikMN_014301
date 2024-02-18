package com.example.cp.production_orders;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductionOrdersDBProperty {

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty material;
    private final SimpleStringProperty date;
    private final SimpleStringProperty number_material;
    private final SimpleBooleanProperty status;
    private final SimpleIntegerProperty need_quantity;
    private final SimpleIntegerProperty reject_quantity;


    public ProductionOrdersDBProperty(ProductionOrdersDB productionOrdersDB) {
        id = new SimpleIntegerProperty(productionOrdersDB.getId());
        material = new SimpleIntegerProperty(productionOrdersDB.getMaterial());
        date = new SimpleStringProperty(productionOrdersDB.getDate());
        status = new SimpleBooleanProperty(productionOrdersDB.getStatus());
        need_quantity = new SimpleIntegerProperty(productionOrdersDB.getNeed_quantity());
        reject_quantity = new SimpleIntegerProperty(productionOrdersDB.getReject_quantity());
        number_material=new SimpleStringProperty(productionOrdersDB.getNumber_material());
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

    public String getNumber_material() {
        return number_material.get();
    }

    public SimpleStringProperty number_materialProperty() {
        return number_material;
    }

    public void setNumber_material(String number_material) {
        this.number_material.set(number_material);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public boolean isStatus() {
        return status.get();
    }

    public SimpleBooleanProperty statusProperty() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public int getNeed_quantity() {
        return need_quantity.get();
    }

    public SimpleIntegerProperty need_quantityProperty() {
        return need_quantity;
    }

    public void setNeed_quantity(int need_quantity) {
        this.need_quantity.set(need_quantity);
    }

    public int getReject_quantity() {
        return reject_quantity.get();
    }

    public SimpleIntegerProperty reject_quantityProperty() {
        return reject_quantity;
    }

    public void setReject_quantity(int reject_quantity) {
        this.reject_quantity.set(reject_quantity);
    }
}
