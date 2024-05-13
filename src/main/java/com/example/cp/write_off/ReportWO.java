package com.example.cp.write_off;

import java.io.Serializable;
import java.util.Objects;

public class ReportWO implements Serializable{
    private String material;
    private Integer id_material;
    private String supplier;
    private Integer total_quantity;
    private Float avg_item_price;
    private String lot;
    private Integer rejected;
    private String date_to;
    private String date_from;
    private String abc;
    private String percent;
    private String total_perc;

    public ReportWO(String material, Integer id_material, String supplier, Integer total_quantity, Float avg_item_price, String lot, Integer rejected, String date_to, String date_from, String abc, String percent, String total_perc) {
        this.material = material;
        this.id_material = id_material;
        this.supplier = supplier;
        this.total_quantity = total_quantity;
        this.avg_item_price = avg_item_price;
        this.lot = lot;
        this.rejected = rejected;
        this.date_to = date_to;
        this.date_from = date_from;
        this.abc = abc;
        this.percent = percent;
        this.total_perc = total_perc;
    }

    public ReportWO() {

    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getTotal_perc() {
        return total_perc;
    }

    public void setTotal_perc(String total_perc) {
        this.total_perc = total_perc;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

    public Integer getId_material() {
        return Objects.requireNonNullElse(id_material, 0);
    }

    public void setId_material(Integer id_material) {
        this.id_material = Objects.requireNonNullElse(id_material, 0);
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(Integer total_quantity) {
        this.total_quantity = total_quantity;
    }

    public Float getAvg_item_price() {
        return Objects.requireNonNullElse(avg_item_price, 0f);
    }

    public void setAvg_item_price(Float avg_item_price) {
        this.avg_item_price = Objects.requireNonNullElse(avg_item_price, 0f);
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Integer getRejected() {
        return Objects.requireNonNullElse(rejected, 0);
    }

    public void setRejected(Integer rejected) {
        this.rejected = Objects.requireNonNullElse(rejected, 0);
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    @Override
    public String toString() {
        return "ReportWO{" +
                "material='" + material + '\'' +
                ", id_material=" + id_material +
                ", supplier='" + supplier + '\'' +
                ", total_quantity=" + total_quantity +
                ", avg_item_price=" + avg_item_price +
                ", lot='" + lot + '\'' +
                ", rejected=" + rejected +
                ", date_to='" + date_to + '\'' +
                ", date_from='" + date_from + '\'' +
                ", abc='" + abc + '\'' +
                ", percent='" + percent + '\'' +
                ", total_perc='" + total_perc + '\'' +
                '}';
    }
}
