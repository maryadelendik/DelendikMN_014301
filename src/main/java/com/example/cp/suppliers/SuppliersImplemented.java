package com.example.cp.suppliers;


import com.example.cp.DatabaseConnection;
import com.example.cp.materials.MaterialsDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class SuppliersImplemented implements Suppliers {

    Connection connection = DatabaseConnection.getConnection();


    @Override
    public List<SuppliersDB> getAll() {
        List<SuppliersDB> suppliersDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM suppliers";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            suppliersDB = new ArrayList<>();

            while (resultSet.next()) {
                SuppliersDB suppliersDBs = new SuppliersDB();
                suppliersDBs.setId(resultSet.getInt("id"));
                suppliersDBs.setName(resultSet.getString("name"));
                suppliersDBs.setPhone_number(resultSet.getString("phone_number"));
                suppliersDBs.setEmail(resultSet.getString("email"));
                suppliersDBs.setAddress(resultSet.getString("address"));

                suppliersDB.add(suppliersDBs);
            }
            suppliersDB.sort(Comparator.comparing(SuppliersDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return suppliersDB;
    }

    @Override
    public List<SuppliersDB> search(String string) {
        List<SuppliersDB> suppliersDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM suppliers where name like '%"+string+"%' or " +
                    "address like '%"+string+"%' or " +
                    "phone_number like '%"+string+"%' or " +
                    "email like '%"+string+"%'" ;

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            suppliersDB = new ArrayList<>();

            while (resultSet.next()) {
                SuppliersDB suppliersDBs = new SuppliersDB();
                suppliersDBs.setId(resultSet.getInt("id"));
                suppliersDBs.setName(resultSet.getString("name"));
                suppliersDBs.setPhone_number(resultSet.getString("phone_number"));
                suppliersDBs.setEmail(resultSet.getString("email"));
                suppliersDBs.setAddress(resultSet.getString("address"));

                suppliersDB.add(suppliersDBs);
            }
            suppliersDB.sort(Comparator.comparing(SuppliersDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return suppliersDB;
    }

    @Override
    public HashSet<String> findSuppliers() {
        HashSet<String> suppliers = new HashSet<>();
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT DISTINCT name FROM suppliers" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                suppliers.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suppliers;
    }

    @Override
    public void deleteById(Integer id) {
        String sql  = "DELETE FROM suppliers " +
                "WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkIfExists(String name) {
        boolean check;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM suppliers WHERE name ='"+name+"'";
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            check=resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    @Override
    public boolean checkForUpdate(SuppliersDB suppliersDB) {
        boolean check;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM suppliers WHERE name ='"+suppliersDB.getName()+"' AND" +
                    " id ="+suppliersDB.getId();
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            check=resultSet.next();
            if(!check){
                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT * FROM suppliers WHERE name ='"+suppliersDB.getName()+"' ";
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                if(!resultSet2.next()) check=true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    @Override
    public void save(SuppliersDB suppliersDB) {
        String sqlRequest = "INSERT INTO suppliers (name, phone_number, email, address) " +
                "VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, suppliersDB.getName());
            statement.setString(2, suppliersDB.getPhone_number());
            statement.setString(3, suppliersDB.getEmail());
            statement.setString(4, suppliersDB.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(SuppliersDB suppliersDB) {
        String sql = "UPDATE suppliers SET" +
                " name = ?," +
                " phone_number = ?," +
                " email = ?," +
                " address = ? " +
                " WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, suppliersDB.getName());
            preparedStatement.setString(2, suppliersDB.getPhone_number());
            preparedStatement.setString(3, suppliersDB.getEmail());
            preparedStatement.setString(4, suppliersDB.getAddress());
            preparedStatement.setInt(5, suppliersDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
