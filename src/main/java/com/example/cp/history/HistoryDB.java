package com.example.cp.history;

import java.io.Serializable;

public class HistoryDB implements Serializable {
    private Integer id;
    private String user;
    private String date_time;
    private String changing_type;
    private Integer material;
    private String old_value;
    private String new_value;

    public HistoryDB(Integer id, String user, String date_time, String changing_type, Integer material, String old_value, String new_value) {
        this.id = id;
        this.user = user;
        this.date_time = date_time;
        this.changing_type = changing_type;
        this.material = material;
        this.old_value = old_value;
        this.new_value = new_value;
    }

    public HistoryDB() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getChanging_type() {
        return changing_type;
    }

    public void setChanging_type(String changing_type) {
        this.changing_type = changing_type;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public String getOld_value() {
        return old_value;
    }

    public void setOld_value(String old_value) {
        this.old_value = old_value;
    }

    public String getNew_value() {
        return new_value;
    }

    public void setNew_value(String new_value) {
        this.new_value = new_value;
    }

    @Override
    public String toString() {
        return "HistoryDB{" +
                "id=" + id +
                ", user=" + user +
                ", date_time='" + date_time + '\'' +
                ", changing_type='" + changing_type + '\'' +
                ", material=" + material +
                ", old_value='" + old_value + '\'' +
                ", new_value='" + new_value + '\'' +
                '}';
    }
}
