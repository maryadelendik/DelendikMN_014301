package com.example.cp.users;

import java.io.Serializable;

public class UsersDB implements Serializable {
    private Integer id;
    private String login;
    private String first_password;
    private String password;
    private Boolean role;


    public UsersDB(String login, String first_password, String password, Boolean role) {
        this.login = login;
        this.first_password = first_password;
        this.password = password;
        this.role = role;
    }

    public UsersDB(Integer id, String login, String first_password, String password, Boolean role) {
        this.id = id;
        this.login = login;
        this.first_password = first_password;
        this.password = password;
        this.role = role;
    }

   public UsersDB() {

    }
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "UsersDB{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", first_password='" + first_password + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
