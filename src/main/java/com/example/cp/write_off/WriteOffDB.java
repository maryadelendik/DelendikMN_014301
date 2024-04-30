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

    public WriteOffDB(Integer id, Integer lot_material, Integer quantity, Integer production_order, String date, Float total_price, Float price_item) {
        this.id = id;
        this.lot_material = lot_material;
        this.quantity = quantity;
        this.production_order = production_order;
        this.date = date;
        this.total_price = total_price;
        this.price_item = price_item;
    }

    public WriteOffDB() {

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
                '}';
    }
}