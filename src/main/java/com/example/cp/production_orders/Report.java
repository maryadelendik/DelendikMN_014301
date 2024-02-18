package com.example.cp.production_orders;

import java.io.Serializable;

public class Report implements Serializable{
    private String material;
    private Integer quantity;
    private Integer reject;
    private Integer quality_num;
    private String quality;
    private String supplier;
    private Float price;

    public Report(String material, Integer quantity, Integer reject, Integer quality_num, String quality, String supplier, Float price) {
        this.material = material;
        this.quantity = quantity;
        this.reject = reject;
        this.quality_num = quality_num;
        this.quality = quality;
        this.supplier = supplier;
        this.price = price;
    }

    public Integer getReject() {
        return reject;
    }

    public void setReject(Integer reject) {
        this.reject = reject;
    }

    public Integer getQuality_num() {
        return quality_num;
    }

    public void setQuality_num(Integer quality_num) {
        this.quality_num = quality_num;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Report() {

    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
