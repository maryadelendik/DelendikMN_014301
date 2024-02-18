package com.example.cp.users;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UsersDBProperty {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty login;

    private final SimpleStringProperty first_password;
    private final SimpleStringProperty password;
    private final SimpleBooleanProperty role;



    public UsersDBProperty(UsersDB usersDB) {
        id = new SimpleIntegerProperty(usersDB.getId());
        login = new SimpleStringProperty(usersDB.getLogin());
        first_password = new SimpleStringProperty(usersDB.getFirst_password());
        password = new SimpleStringProperty(usersDB.getPassword());
        role = new SimpleBooleanProperty(usersDB.getRole());

    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getFirst_password() {
        return first_password.get();
    }

    public SimpleStringProperty first_passwordProperty() {
        return first_password;
    }

    public void setFirst_password(String first_password) {
        this.first_password.set(first_password);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isRole() {
        return role.get();
    }

    public SimpleBooleanProperty roleProperty() {
        return role;
    }

    public void setRole(boolean role) {
        this.role.set(role);
    }

   }
