package com.example.cp.materials;


import com.example.cp.DatabaseConnection;
import com.example.cp.history.HistoryDB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MaterialsImplemented implements Materials {

    Connection connection = DatabaseConnection.getConnection();


    @Override
    public List<MaterialsDB> getAll() {
        List<MaterialsDB> materialsDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM materials";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            materialsDB = new ArrayList<>();

            while (resultSet.next()) {
                MaterialsDB materialsDBs = new MaterialsDB();
                materialsDBs.setId(resultSet.getInt("id"));
                materialsDBs.setName(resultSet.getString("name"));
                materialsDBs.setNumber(resultSet.getString("number"));
                materialsDBs.setType(resultSet.getString("type"));
                materialsDBs.setStock_quantity(resultSet.getInt("stock_quantity"));
                materialsDBs.setOrder_quantity(resultSet.getInt("order_quantity"));
                materialsDB.add(materialsDBs);
            }
            materialsDB.sort(Comparator.comparing(MaterialsDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materialsDB;
    }

    @Override
    public List<MaterialsDB> getAllByType(String type) {
        List<MaterialsDB> materialsDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM materials WHERE type ='"+type+"'";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            materialsDB = new ArrayList<>();

            while (resultSet.next()) {
                MaterialsDB materialsDBs = new MaterialsDB();
                materialsDBs.setId(resultSet.getInt("id"));
                materialsDBs.setName(resultSet.getString("name"));
                materialsDBs.setNumber(resultSet.getString("number"));
                materialsDBs.setType(resultSet.getString("type"));
                materialsDBs.setStock_quantity(resultSet.getInt("stock_quantity"));
                materialsDBs.setOrder_quantity(resultSet.getInt("order_quantity"));
                materialsDB.add(materialsDBs);
            }
            materialsDB.sort(Comparator.comparing(MaterialsDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materialsDB;
    }

    @Override
    public List<MaterialsDB> search(String string, Boolean has_type, String type) {
        List<MaterialsDB> materialsDB;

        try {
            Statement statement = connection.createStatement();
            String sqlResponse;
            if(has_type){
                sqlResponse = "SELECT * FROM materials where (name like '%"+string+"%' or " +
                        "number like '%"+string+"%') AND type = '"+type+"' " ;
            }
            else {
                sqlResponse = "SELECT * FROM materials where name like '%"+string+"%' or " +
                        "number like '%"+string+"%' or " +
                        "type like '%"+string+"%'" ;
            }

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            materialsDB = new ArrayList<>();

            while (resultSet.next()) {
                MaterialsDB materialsDBs = new MaterialsDB();
                materialsDBs.setId(resultSet.getInt("id"));
                materialsDBs.setName(resultSet.getString("name"));
                materialsDBs.setNumber(resultSet.getString("number"));
                materialsDBs.setType(resultSet.getString("type"));
                materialsDBs.setStock_quantity(resultSet.getInt("stock_quantity"));
                materialsDBs.setOrder_quantity(resultSet.getInt("order_quantity"));

                materialsDB.add(materialsDBs);
            }
            materialsDB.sort(Comparator.comparing(MaterialsDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return materialsDB;
    }

    @Override
    public HashSet<String> findTypes() {
        HashSet<String> types = new HashSet<>();

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT DISTINCT type FROM materials" ;

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                types.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return types;
    }

    @Override
    public boolean checkIfExists(String number) {
        boolean check;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM materials WHERE number ='"+number+"'";
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            check=resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    @Override
    public boolean checkForUpdate(MaterialsDB materialsDB) {
        boolean check;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM materials WHERE number ='"+materialsDB.getNumber()+"' AND" +
                    " id ="+materialsDB.getId();
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            check=resultSet.next();
            if(!check){
                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT * FROM materials WHERE number ='"+materialsDB.getNumber()+"' ";
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                if(!resultSet2.next()) check=true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public Integer findIdUser(String login) {
        int id = 0;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT id FROM users WHERE login='"+login+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                 id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
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

    @Override
    public void deleteById(Integer id) {
        String sql  = "DELETE FROM materials " +
                "WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HistoryDB> findChanges(MaterialsDB materialsDB) {
        List<HistoryDB> historyDB = new ArrayList<HistoryDB>();
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT * FROM materials WHERE id ="+materialsDB.getId();
            ResultSet resultSet = statement.executeQuery(sqlResponse);

            while (resultSet.next()) {
                if(!Objects.equals(materialsDB.getNumber(), resultSet.getString("number"))){
                    HistoryDB historyDBS = new HistoryDB();
                    historyDBS.setOld_value(resultSet.getString("number"));
                    historyDBS.setNew_value(materialsDB.getNumber());
                    historyDB.add(historyDBS);
                }
                if(!Objects.equals(materialsDB.getName(), resultSet.getString("name"))){
                    HistoryDB historyDBS = new HistoryDB();
                    historyDBS.setOld_value(resultSet.getString("name"));
                    historyDBS.setNew_value(materialsDB.getName());
                    historyDB.add(historyDBS);
                }
                if(!Objects.equals(materialsDB.getType(), resultSet.getString("type"))){
                    HistoryDB historyDBS = new HistoryDB();
                    historyDBS.setOld_value(resultSet.getString("type"));
                    historyDBS.setNew_value(materialsDB.getType());
                    historyDB.add(historyDBS);
                }
                if(materialsDB.getStock_quantity()!= resultSet.getInt("stock_quantity")){
                    HistoryDB historyDBS = new HistoryDB();
                    historyDBS.setOld_value(String.valueOf(resultSet.getInt("stock_quantity")));
                    historyDBS.setNew_value(String.valueOf(materialsDB.getStock_quantity()));
                    historyDB.add(historyDBS);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historyDB;
    }

    @Override
    public void save(MaterialsDB materialsDB) {
        String sqlRequest = "INSERT INTO materials (name, number, type, stock_quantity) " +
                "VALUES(?, ?, ?, ?)";
        String number;
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, materialsDB.getName());
            number=materialsDB.getNumber();
            statement.setString(2, materialsDB.getNumber());
            statement.setString(3, materialsDB.getType());
            statement.setInt(4, materialsDB.getStock_quantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // String message = formatter.format(calendar.getTime());

        String historyRequest = "INSERT INTO history (user, date_time, changing_type, material) " +
                "VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(historyRequest);
            statement.setInt(1, findIdUser(materialsDB.getUser_login()));
            statement.setString(2,formatter.format(calendar.getTime()));
            statement.setString(3, "СОЗДАНИЕ");
            statement.setInt(4, findIdMaterial(number));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashSet<String> findMaterials() {
        HashSet<String> materials = new HashSet<>();
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT DISTINCT number FROM materials" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                materials.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materials;
    }

    @Override
    public void update(MaterialsDB materialsDB) {
        String sql = "UPDATE materials SET" +
                " name = ?," +
                " number = ?," +
                " type = ?," +
                " stock_quantity = ? ," +
                " order_quantity = ? " +
                " WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, materialsDB.getName());
            preparedStatement.setString(2, materialsDB.getNumber());
            preparedStatement.setString(3, materialsDB.getType());
            preparedStatement.setInt(4, materialsDB.getStock_quantity());
            preparedStatement.setInt(5, materialsDB.getOrder_quantity());
            preparedStatement.setInt(6, materialsDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
