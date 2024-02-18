package com.example.cp.history;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HistoryDBProperty {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty user;
    private final SimpleStringProperty date_time;
    private final SimpleStringProperty changing_type;
    private final SimpleIntegerProperty material;
    private final SimpleStringProperty old_value;
    private final SimpleStringProperty new_value;


    public HistoryDBProperty(HistoryDB historyDB) {
        id = new SimpleIntegerProperty(historyDB.getId());
        user = new SimpleStringProperty(historyDB.getUser());
        date_time = new SimpleStringProperty(historyDB.getDate_time());
        changing_type = new SimpleStringProperty(historyDB.getChanging_type());
        material = new SimpleIntegerProperty(historyDB.getMaterial());
        old_value = new SimpleStringProperty(historyDB.getOld_value());
        new_value = new SimpleStringProperty(historyDB.getNew_value());

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getDate_time() {
        return date_time.get();
    }

    public SimpleStringProperty date_timeProperty() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time.set(date_time);
    }

    public String getChanging_type() {
        return changing_type.get();
    }

    public SimpleStringProperty changing_typeProperty() {
        return changing_type;
    }

    public void setChanging_type(String changing_type) {
        this.changing_type.set(changing_type);
    }

    public int getMaterial() {
        return material.get();
    }

    public SimpleIntegerProperty materialProperty() {
        return material;
    }

    public void setMaterial(int material) {
        this.material.set(material);
    }

    public String getOld_value() {
        return old_value.get();
    }

    public SimpleStringProperty old_valueProperty() {
        return old_value;
    }

    public void setOld_value(String old_value) {
        this.old_value.set(old_value);
    }

    public String getNew_value() {
        return new_value.get();
    }

    public SimpleStringProperty new_valueProperty() {
        return new_value;
    }

    public void setNew_value(String new_value) {
        this.new_value.set(new_value);
    }
}
