package com.example.cp.supply_documents;

import java.io.Serializable;

public class SupplyDocumentsDB implements Serializable {
    private Integer id;
    private Integer mat_sup;
    private String number;
    private String material;
    private String supplier;
    private Integer quantity;
    private String date;
    private Float price;


    public SupplyDocumentsDB(Integer id, Integer mat_sup, String number, String material, String supplier, Integer quantity, String date, Float price) {
        this.id = id;
        this.mat_sup = mat_sup;
        this.number = number;
        this.material = material;
        this.supplier = supplier;
        this.quantity = quantity;
        this.date = date;
        this.price = price;
    }

    public SupplyDocumentsDB() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMat_sup() {
        return mat_sup;
    }

    public void setMat_sup(Integer mat_sup) {
        this.mat_sup = mat_sup;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SupplyDocumentsDB{" +
                "id=" + id +
                ", mat_sup=" + mat_sup +
                ", number='" + number + '\'' +
                ", material='" + material + '\'' +
                ", supplier='" + supplier + '\'' +
                ", quantity=" + quantity +
                ", date='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}
