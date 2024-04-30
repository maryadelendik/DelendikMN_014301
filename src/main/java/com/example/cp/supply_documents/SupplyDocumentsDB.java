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
    private Float price_item;
    private Integer month_leftovers;
    private String lot;
    private Integer current_stock;



    public SupplyDocumentsDB(Integer id, Integer mat_sup, String number, String material, String supplier, Integer quantity, String date, Float price,
                             Float price_item,Integer month_leftovers,String lot,Integer current_stock) {
        this.id = id;
        this.mat_sup = mat_sup;
        this.number = number;
        this.material = material;
        this.supplier = supplier;
        this.quantity = quantity;
        this.date = date;
        this.price = price;
        this.price_item = price_item;
        this.month_leftovers = month_leftovers;
        this.lot = lot;
        this.current_stock = current_stock;
    }

    public SupplyDocumentsDB() {

    }

    public Float getPrice_item() {
        return price_item;
    }

    public void setPrice_item(Float price_item) {
        this.price_item = price_item;
    }

    public Integer getMonth_leftovers() {
        return month_leftovers;
    }

    public void setMonth_leftovers(Integer month_leftovers) {
        this.month_leftovers = month_leftovers;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Integer getCurrent_stock() {
        return current_stock;
    }

    public void setCurrent_stock(Integer current_stock) {
        this.current_stock = current_stock;
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
                ", price_item=" + price_item +
                ", month_leftovers=" + month_leftovers +
                ", lot='" + lot + '\'' +
                ", current_stock=" + current_stock +
                '}';
    }
}
