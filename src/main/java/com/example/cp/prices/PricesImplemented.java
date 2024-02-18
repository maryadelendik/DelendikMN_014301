package com.example.cp.prices;


import com.example.cp.DatabaseConnection;

import java.sql.*;

public class PricesImplemented implements Prices {

    Connection connection = DatabaseConnection.getConnection();

    @Override
    public Float getForMatSup(PricesDB pricesDB) {
        float price = 0;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT price FROM material_supplier WHERE material="+ findIdMaterial(pricesDB.getMat_name())+" " +
                    "AND supplier =" + pricesDB.getSupplier();
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                price = resultSet.getFloat(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return price;
    }

    @Override
    public Boolean check(PricesDB pricesDB) {
        if(pricesDB.getSup_name()!=null){
            try {
                Statement statement = connection.createStatement();

                String sqlResponse = "SELECT price FROM material_supplier WHERE material="+ findIdMaterial(pricesDB.getMat_name())+" " +
                        "AND supplier =" + findIdSupplier(pricesDB.getSup_name());
                ResultSet resultSet = statement.executeQuery(sqlResponse);
                if (resultSet.next()) {
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT price FROM material_supplier WHERE material="+ findIdMaterial(pricesDB.getMat_name())+" " +
                    "AND supplier =" + pricesDB.getSupplier();
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}
        return false;
    }

    @Override
    public void save(PricesDB pricesDB) {
        String sql = "UPDATE material_supplier SET" +
                " price =  ?" +
                " WHERE id = ?";
        try {
            Statement statement2 = connection.createStatement();
            String sqlResponse2 = "SELECT id FROM warehouse.material_supplier WHERE material = " +
                    "(SELECT id FROM materials WHERE number = '"+ pricesDB.getMat_name()+"') and\n" +
                    "supplier= (SELECT id FROM suppliers WHERE id = "+ pricesDB.getSupplier()+")";
            ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);

            if(!resultSet2.next()){
                String sqlResponse3 = "INSERT INTO warehouse.material_supplier SET material = " +
                        "(SELECT id FROM materials WHERE number = '"+ pricesDB.getMat_name()+"'),\n" +
                        "supplier= (SELECT id FROM suppliers WHERE id = "+ pricesDB.getSupplier()+")";
                PreparedStatement statement3 = connection.prepareStatement(sqlResponse3);
                statement3.executeUpdate();
            }
            ResultSet resultSet22 = statement2.executeQuery(sqlResponse2);
            while (resultSet22.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setFloat(1, pricesDB.getPrice());
                preparedStatement.setInt(2, resultSet22.getInt(1));
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Integer findIdMaterial(String number) {
        int id = 0;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT id FROM materials WHERE number='"+number+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public Integer findIdSupplier(String name) {
        int id = 0;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT id FROM suppliers WHERE name='"+name+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
