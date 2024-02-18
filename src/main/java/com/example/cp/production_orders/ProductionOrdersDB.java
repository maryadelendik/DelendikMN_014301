package com.example.cp.production_orders;

import java.io.Serializable;

public class ProductionOrdersDB implements Serializable {
    private Integer id;
    private Integer material;
    private String number_material;
    private Integer need_quantity;
    private Integer reject_quantity;
    private String date;
    private Boolean status;

    public ProductionOrdersDB(Integer id, Integer material, String number_material, Integer need_quantity, Integer reject_quantity, String date, Boolean status) {
        this.id = id;
        this.material = material;
        this.number_material = number_material;
        this.need_quantity = need_quantity;
        this.reject_quantity = reject_quantity;
        this.date = date;
        this.status = status;
    }

    public String getNumber_material() {
        return number_material;
    }

    public void setNumber_material(String number_material) {
        this.number_material = number_material;
    }

    public ProductionOrdersDB() {

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

    public Integer getNeed_quantity() {
        return need_quantity;
    }

    public void setNeed_quantity(Integer need_quantity) {
        this.need_quantity = need_quantity;
    }

    public Integer getReject_quantity() {
        return reject_quantity;
    }

    public void setReject_quantity(Integer reject_quantity) {
        this.reject_quantity = reject_quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductionOrdersDB{" +
                "id=" + id +
                ", material=" + material +
                ", need_quantity=" + need_quantity +
                ", reject_quantity=" + reject_quantity +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
