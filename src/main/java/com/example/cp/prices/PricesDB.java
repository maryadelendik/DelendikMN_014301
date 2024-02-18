package com.example.cp.prices;

import java.io.Serializable;

public class PricesDB implements Serializable {
    private Integer id;
    private Integer material;
    private Integer supplier;
    private String mat_name;
    private String sup_name;
    private Float price;


    public PricesDB(Integer id, Integer material, Integer supplier, String mat_name, String sup_name, Float price) {
        this.id = id;
        this.material = material;
        this.supplier = supplier;
        this.mat_name = mat_name;
        this.sup_name = sup_name;
        this.price = price;
    }

    public PricesDB() {

    }

    @Override
    public String toString() {
        return "PricesDB{" +
                "id=" + id +
                ", material=" + material +
                ", supplier=" + supplier +
                ", mat_name='" + mat_name + '\'' +
                ", sup_name='" + sup_name + '\'' +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    public String getMat_name() {
        return mat_name;
    }

    public void setMat_name(String mat_name) {
        this.mat_name = mat_name;
    }

    public String getSup_name() {
        return sup_name;
    }

    public void setSup_name(String sup_name) {
        this.sup_name = sup_name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
