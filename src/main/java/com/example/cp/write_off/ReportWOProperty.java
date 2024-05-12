package com.example.cp.write_off;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReportWOProperty {

    private final SimpleIntegerProperty id_material;
    private final SimpleIntegerProperty total_quantity;
    private final SimpleIntegerProperty rejected;

    private final SimpleStringProperty material;
    private final SimpleStringProperty lot;
    private final SimpleStringProperty date_to;
    private final SimpleFloatProperty avg_item_price;
    private final SimpleStringProperty date_from;
    private final SimpleStringProperty supplier;
    private final SimpleStringProperty abc;


    public ReportWOProperty(ReportWO reportWO) {
        id_material = new SimpleIntegerProperty(reportWO.getId_material());
        total_quantity = new SimpleIntegerProperty(reportWO.getTotal_quantity());
        rejected = new SimpleIntegerProperty(reportWO.getRejected());
        material = new SimpleStringProperty(reportWO.getMaterial());
        lot = new SimpleStringProperty(reportWO.getLot());
        date_to = new SimpleStringProperty(reportWO.getDate_to());
        date_from = new SimpleStringProperty(reportWO.getDate_from());
        supplier = new SimpleStringProperty(reportWO.getSupplier());
        avg_item_price = new SimpleFloatProperty(reportWO.getAvg_item_price());
        abc = new SimpleStringProperty(reportWO.getAbc());
    }

    public String getAbc() {
        return abc.get();
    }

    public SimpleStringProperty abcProperty() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc.set(abc);
    }

    public int getId_material() {
        return id_material.get();
    }

    public SimpleIntegerProperty id_materialProperty() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material.set(id_material);
    }

    public int getTotal_quantity() {
        return total_quantity.get();
    }

    public SimpleIntegerProperty total_quantityProperty() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity.set(total_quantity);
    }

    public int getRejected() {
        return rejected.get();
    }

    public SimpleIntegerProperty rejectedProperty() {
        return rejected;
    }

    public void setRejected(int rejected) {
        this.rejected.set(rejected);
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

    public String getLot() {
        return lot.get();
    }

    public SimpleStringProperty lotProperty() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot.set(lot);
    }

    public String getDate_to() {
        return date_to.get();
    }

    public SimpleStringProperty date_toProperty() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to.set(date_to);
    }

    public float getAvg_item_price() {
        return avg_item_price.get();
    }

    public SimpleFloatProperty avg_item_priceProperty() {
        return avg_item_price;
    }

    public void setAvg_item_price(float avg_item_price) {
        this.avg_item_price.set(avg_item_price);
    }

    public String getDate_from() {
        return date_from.get();
    }

    public SimpleStringProperty date_fromProperty() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from.set(date_from);
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
}
