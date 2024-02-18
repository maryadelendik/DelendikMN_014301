package com.example.cp.authorization;

import java.io.Serializable;

public class Authorization implements Serializable {
    private String login;
    private String password;
    private String first_password;
    private Boolean role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getFirst_password() {
        return first_password;
    }
    public void setFirst_password(String first_password) {
        this.first_password = first_password;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "login='" + login + '\'' +
                ", first_password='" + first_password + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
