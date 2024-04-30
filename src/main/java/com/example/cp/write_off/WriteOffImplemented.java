package com.example.cp.write_off;


import com.example.cp.DatabaseConnection;
import com.example.cp.prices.Prices;
import com.example.cp.prices.PricesDB;
import com.example.cp.prices.PricesImplemented;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class WriteOffImplemented implements WriteOff {

    Connection connection = DatabaseConnection.getConnection();


    @Override
    public List<WriteOffDB> getAll() {
    /*   List<WriteOffDB> writeOffDB;
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT * FROM supply_documents";
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            writeOffDB = new ArrayList<>();

            while (resultSet.next()) {
                WriteOffDB writeOffDBs = new WriteOffDB();
                writeOffDBs.setId(resultSet.getInt("id"));
                writeOffDBs.setNumber(resultSet.getString("number"));
                writeOffDBs.setMat_sup(resultSet.getInt("mat_sup"));

                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT M.number, S.name\n" +
                        "FROM warehouse.materials M \n" +
                        "INNER JOIN warehouse.material_supplier MS ON M.id=MS.material\n" +
                        "INNER JOIN warehouse.suppliers S ON MS.supplier=S.id\n" +
                        "WHERE MS.id="+resultSet.getInt("mat_sup");
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                while (resultSet2.next()) {
                    writeOffDBs.setMaterial(resultSet2.getString(1));
                    writeOffDBs.setSupplier(resultSet2.getString(2));
                }
                writeOffDBs.setQuantity(resultSet.getInt("quantity"));

                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                java.util.Date date = oldDateFormat.parse(resultSet.getString("date"));
                String result = newDateFormat.format(date);
                writeOffDBs.setDate(result);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                //supplyDocumentsDBs.setDate(String.format(resultSet.getString("date"), formatter));
               // System.out.println(LocalDate.parse(resultSet.getString("date"), formatter));
                //supplyDocumentsDBs.setDate(String.valueOf(LocalDate.parse(resultSet.getString("date"), DateTimeFormatter.BASIC_ISO_DATE)));
                //supplyDocumentsDBs.setDate(resultSet.getString("date"));
                writeOffDBs.setPrice(resultSet.getFloat("price"));
                writeOffDB.add(writeOffDBs);
            }
            writeOffDB.sort(Comparator.comparing(WriteOffDB::getId).reversed());
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return writeOffDB;*/
        return null;
    }

    @Override
    public List<WriteOffDB> search(String string) {
    /*    List<WriteOffDB> writeOffDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM warehouse.supply_documents WHERE mat_sup IN (SELECT id FROM warehouse.material_supplier\n" +
                    "WHERE supplier IN (SELECT id FROM warehouse.suppliers where name like '%"+string+"%') OR material IN\n" +
                    "(SELECT id FROM warehouse.materials where number like '%"+string+"%')) OR date like '%"+string+"%' or number like '%"+string+"%'" ;

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            writeOffDB = new ArrayList<>();

            while (resultSet.next()) {
                WriteOffDB writeOffDBs = new WriteOffDB();
                writeOffDBs.setId(resultSet.getInt("id"));
                writeOffDBs.setNumber(resultSet.getString("number"));
                writeOffDBs.setMat_sup(resultSet.getInt("mat_sup"));

                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT M.number, S.name\n" +
                        "FROM warehouse.materials M \n" +
                        "INNER JOIN warehouse.material_supplier MS ON M.id=MS.material\n" +
                        "INNER JOIN warehouse.suppliers S ON MS.supplier=S.id\n" +
                        "WHERE MS.id="+resultSet.getInt("mat_sup");
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                while (resultSet2.next()) {
                    writeOffDBs.setMaterial(resultSet2.getString(1));
                    writeOffDBs.setSupplier(resultSet2.getString(2));
                }
                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                java.util.Date date = oldDateFormat.parse(resultSet.getString("date"));
                String result = newDateFormat.format(date);
                writeOffDBs.setDate(result);
                writeOffDBs.setQuantity(resultSet.getInt("quantity"));
          //      supplyDocumentsDBs.setDate(resultSet.getString("date"));
                writeOffDBs.setPrice(resultSet.getFloat("price"));
                writeOffDB.add(writeOffDBs);
            }
            writeOffDB.sort(Comparator.comparing(WriteOffDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return writeOffDB;*/
        return null;
    }

    @Override
    public void deleteById(Integer id) {/*
        String get_material = "SELECT MS.material, D.quantity FROM warehouse.supply_documents D " +
                "INNER JOIN warehouse.material_supplier MS ON MS.id=D.mat_sup " +
                "WHERE D.id=" + id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get_material);
            while (resultSet.next()) {
                    String reduce_order_quantity = "UPDATE materials SET stock_quantity = stock_quantity - " + resultSet.getInt(2)+
                            " WHERE id = " + resultSet.getInt(1);
                    PreparedStatement preparedStatement = connection.prepareStatement(reduce_order_quantity);
                    preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql  = "DELETE FROM supply_documents " +
                "WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }



    @Override
    public void save(WriteOffDB writeOffDB) {/*
        String sqlRequest = "INSERT INTO supply_documents (number, mat_sup, quantity, date, price) " +
                "VALUES(?, ?, ?, ?, ?)";
        String sql = "UPDATE materials SET" +
                " stock_quantity = stock_quantity + ?" +
                " WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, writeOffDB.getNumber());

            Statement statement2 = connection.createStatement();
            String sqlResponse2 = "SELECT id FROM warehouse.material_supplier WHERE material = " +
                    "(SELECT id FROM materials WHERE number = '"+ writeOffDB.getMaterial()+"') and\n" +
                    "supplier= (SELECT id FROM suppliers WHERE name = '"+ writeOffDB.getSupplier()+"')";
            ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);

            if(!resultSet2.next()){
                String sqlResponse3 = "INSERT INTO warehouse.material_supplier SET material = " +
                        "(SELECT id FROM materials WHERE number = '"+ writeOffDB.getMaterial()+"'),\n" +
                        "supplier= (SELECT id FROM suppliers WHERE name = '"+ writeOffDB.getSupplier()+"')";
                PreparedStatement statement3 = connection.prepareStatement(sqlResponse3);
                statement3.executeUpdate();
            }
            ResultSet resultSet22 = statement2.executeQuery(sqlResponse2);
            while (resultSet22.next()) {
                statement.setInt(2, resultSet22.getInt(1));
            }

            statement.setInt(3, writeOffDB.getQuantity());
            statement.setString(4, writeOffDB.getDate());
            statement.setFloat(5, writeOffDB.getPrice());
            statement.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, writeOffDB.getQuantity());
            preparedStatement.setInt(2, findIdMaterial(writeOffDB.getMaterial()));
            preparedStatement.executeUpdate();
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
        return id;*/
    }
    @Override
    public void update(WriteOffDB writeOffDB) {/*
        String sqlRequest = "UPDATE supply_documents SET number = ?, mat_sup = ?, quantity = ?, date = ?, price = ? " +
                " WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, writeOffDB.getNumber());

            Statement statement2 = connection.createStatement();
            String sqlResponse2 = "SELECT id FROM warehouse.material_supplier WHERE material = " +
                    "(SELECT id FROM materials WHERE number = '"+ writeOffDB.getMaterial()+"') and\n" +
                    "supplier= (SELECT id FROM suppliers WHERE name = '"+ writeOffDB.getSupplier()+"')";
            ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);

            if(!resultSet2.next()){
                String sqlResponse3 = "INSERT INTO warehouse.material_supplier SET material = " +
                        "(SELECT id FROM materials WHERE number = '"+ writeOffDB.getMaterial()+"'),\n" +
                        "supplier= (SELECT id FROM suppliers WHERE name = '"+ writeOffDB.getSupplier()+"')";
                PreparedStatement statement3 = connection.prepareStatement(sqlResponse3);
                statement3.executeUpdate();
            }
            ResultSet resultSet22 = statement2.executeQuery(sqlResponse2);
            while (resultSet22.next()) {
                statement.setInt(2, resultSet22.getInt(1));
            }

            statement.setInt(3, writeOffDB.getQuantity());
            statement.setString(4, writeOffDB.getDate());
            statement.setFloat(5, writeOffDB.getPrice());
            statement.setInt(6, writeOffDB.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public Float count(WriteOffDB writeOffDB) {/*
        PricesDB pricesDB = new PricesDB();
        pricesDB.setSupplier(findIdSupplier(writeOffDB.getSupplier()));
        pricesDB.setMat_name(writeOffDB.getMaterial());
        Prices prices = new PricesImplemented();
        System.out.println(writeOffDB.getSupplier());
        System.out.println(writeOffDB.getMaterial());
        System.out.println(findIdSupplier(writeOffDB.getSupplier()));
        return writeOffDB.getQuantity()*prices.getForMatSup(pricesDB);*/
        return null;
    }

    public Integer findIdSupplier(String name) {/*
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
        return id;*/
        return null;
    }

}
