package com.example.cp.materials;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialsDBProperty {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;

    private final SimpleStringProperty number;
    private final SimpleStringProperty type;
    private final SimpleIntegerProperty stock_quantity;
    private final SimpleIntegerProperty order_quantity;



    public MaterialsDBProperty(MaterialsDB materialsDB) {
        id = new SimpleIntegerProperty(materialsDB.getId());
        name = new SimpleStringProperty(materialsDB.getName());
        number = new SimpleStringProperty(materialsDB.getNumber());
        type = new SimpleStringProperty(materialsDB.getType());
        stock_quantity = new SimpleIntegerProperty(materialsDB.getStock_quantity());
        order_quantity = new SimpleIntegerProperty(materialsDB.getOrder_quantity());

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

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
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

    public int getStock_quantity() {
        return stock_quantity.get();
    }

    public SimpleIntegerProperty stock_quantityProperty() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity.set(stock_quantity);
    }

    public int getOrder_quantity() {
        return order_quantity.get();
    }

    public SimpleIntegerProperty order_quantityProperty() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity.set(order_quantity);
    }
}
