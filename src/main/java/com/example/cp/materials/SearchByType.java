package com.example.cp.materials;

import java.io.Serializable;

public class SearchByType implements Serializable {
    private String type;
    private Boolean has_type;
    private String string;

    public SearchByType(String type, Boolean has_type, String string) {
        this.type = type;
        this.has_type = has_type;
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public SearchByType() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getHas_type() {
        return has_type;
    }

    public void setHas_type(Boolean has_type) {
        this.has_type = has_type;
    }

    @Override
    public String toString() {
        return "SearchByType{" +
                "type='" + type + '\'' +
                ", has_type=" + has_type +
                ", string='" + string + '\'' +
                '}';
    }
}
