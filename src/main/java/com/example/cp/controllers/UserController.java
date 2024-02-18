package com.example.cp.controllers;

import com.example.cp.DatabaseConnection;
import com.example.cp.authorization.Authorization;
import com.example.cp.materials.Materials;
import com.example.cp.materials.MaterialsImplemented;
import com.example.cp.suppliers.Suppliers;
import com.example.cp.suppliers.SuppliersDB;
import com.example.cp.suppliers.SuppliersImplemented;
import com.example.cp.users.Users;
import com.example.cp.users.UsersDB;
import com.example.cp.users.UsersImplemented;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/check_first_password")
    public ResponseEntity<Authorization> checkFirstPassword(@RequestBody Authorization auth) {
        System.out.println("Выполняется проверка пароля....");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        Connection connection = DatabaseConnection.getConnection();
        String find_password = "SELECT first_password FROM users WHERE login = '"
                +auth.getLogin()+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(find_password);
            while (queryResult.next()){
                if (encoder.matches(auth.getFirst_password(),queryResult.getString(1))){
                    return new ResponseEntity<>( HttpStatus.OK);

                } else if(queryResult.getString(1)==null){
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                else {
                    return new ResponseEntity<>( HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, null);
    }
    @PostMapping("/authorization")
    public ResponseEntity<Authorization> authorize(@RequestBody Authorization auth) {
        System.out.println("Выполняется авторизация пользователя....");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        Connection connection = DatabaseConnection.getConnection();
        String verifyLogin = "SELECT count(1) FROM users WHERE login = '"
                +auth.getLogin()+"' AND password != 'null' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    String password = "SELECT password FROM users WHERE login = '" + auth.getLogin() + "' ";
                    try {
                        Statement statement1 = connection.createStatement();
                        ResultSet queryResult1 = statement1.executeQuery(password);
                        while (queryResult1.next()) {
                            if (encoder.matches(auth.getPassword(), queryResult1.getString(1))) {
                                String role = "SELECT role FROM users WHERE login = '"
                                        + auth.getLogin() + "' ";
                                try {
                                    Statement statement2 = connection.createStatement();
                                    ResultSet queryResult2 = statement2.executeQuery(role);
                                    while (queryResult2.next()) {
                                        System.out.println("Role: " + queryResult2.getString(1));
                                        Authorization auth_role = new Authorization();
                                        auth_role.setRole(queryResult2.getBoolean(1));
                                        return new ResponseEntity<>(auth_role, HttpStatus.OK);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }  else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, null);
    }
    @PostMapping("/new_password")
    public ResponseEntity<Authorization> newPassword(@RequestBody Authorization auth) {
        System.out.println("Выполняется смена пароля....");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        Connection connection = DatabaseConnection.getConnection();
        String verifyLogin = "SELECT first_password, role, password FROM users WHERE login = '"
                +auth.getLogin()+"' ";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()){
                if (!encoder.matches(auth.getPassword(),queryResult.getString(1))&&
                        !encoder.matches(auth.getPassword(),queryResult.getString(3))){
                    String update = "UPDATE users SET password = '"+encoder.encode(auth.getPassword())+"' " +
                            ", first_password ="+ null+" WHERE login = '"
                            +auth.getLogin()+"' ";
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(update);
                        preparedStatement.executeUpdate();
                        if(queryResult.getInt(2) == 1){
                            return new ResponseEntity<>(HttpStatus.OK);
                        }else {
                            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                        }

                    }  catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping ("/check_if_exists/{login}")
    public ResponseEntity<Integer> checkIfExists(@PathVariable String login) {
        System.out.println("Выполняется проверка логина....");
        Connection connection = DatabaseConnection.getConnection();
        String find_login = "SELECT * FROM users WHERE login = '"
                +login+"'";
        System.out.println(login);
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(find_login);
            if (queryResult.next()) {
                return new ResponseEntity<>(0, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(1, HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/save")
    public ResponseEntity<Integer> saveUser(@RequestBody UsersDB usersDB) {
        System.out.println("Выполняется добавление пользователя...");
        Users users = new UsersImplemented();
        users.save(usersDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/delete/{login}")
    public ResponseEntity<Integer> deleteUser(@PathVariable String login) {
        System.out.println("Выполняется удаление пользователя...");
        Users users=new UsersImplemented();
        users.deleteByLogin(login);
        return new ResponseEntity<>(0, HttpStatus.OK);

    }
}

