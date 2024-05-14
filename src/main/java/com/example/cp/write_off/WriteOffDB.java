package com.example.cp.write_off;

import java.io.Serializable;

public class WriteOffDB implements Serializable {
    private Integer id;
    private Integer lot_material;
    private Integer quantity;
    private Integer production_order;
    private String date;
    private Float total_price;
    private Float price_item;
    private String type;
    private String lot;
    private Integer reject;
    private String name_material;
    private String number_material;
    private String unit_material;

    public WriteOffDB(Integer id, Integer lot_material, Integer quantity, Integer production_order, String date, Float total_price, Float price_item, String type, String lot, Integer reject, String name_material, String number_material, String unit_material) {
        this.id = id;
        this.lot_material = lot_material;
        this.quantity = quantity;
        this.production_order = production_order;
        this.date = date;
        this.total_price = total_price;
        this.price_item = price_item;
        this.type = type;
        this.lot = lot;
        this.reject = reject;
        this.name_material = name_material;
        this.number_material = number_material;
        this.unit_material = unit_material;
    }

    public WriteOffDB() {

    }

    public String getName_material() {
        return name_material;
    }

    public void setName_material(String name_material) {
        this.name_material = name_material;
    }

    public String getNumber_material() {
        return number_material;
    }

    public void setNumber_material(String number_material) {
        this.number_material = number_material;
    }

    public String getUnit_material() {
        return unit_material;
    }

    public void setUnit_material(String unit_material) {
        this.unit_material = unit_material;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Integer getReject() {
        return reject;
    }

    public void setReject(Integer reject) {
        this.reject = reject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLot_material() {
        return lot_material;
    }

    public void setLot_material(Integer lot_material) {
        this.lot_material = lot_material;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProduction_order() {
        return production_order;
    }

    public void setProduction_order(Integer production_order) {
        this.production_order = production_order;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public Float getPrice_item() {
        return price_item;
    }

    public void setPrice_item(Float price_item) {
        this.price_item = price_item;
    }

    @Override
    public String toString() {
        return "WriteOffDB{" +
                "id=" + id +
                ", lot_material=" + lot_material +
                ", quantity=" + quantity +
                ", production_order=" + production_order +
                ", date='" + date + '\'' +
                ", total_price=" + total_price +
                ", price_item=" + price_item +
                ", type='" + type + '\'' +
                ", lot='" + lot + '\'' +
                ", reject=" + reject +
                '}';
    }
}
