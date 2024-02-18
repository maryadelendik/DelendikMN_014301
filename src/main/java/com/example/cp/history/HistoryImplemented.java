package com.example.cp.history;


import com.example.cp.DatabaseConnection;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryImplemented implements History {

    Connection connection = DatabaseConnection.getConnection();


    @Override
    public List<HistoryDB> getAll(Integer id) {
        List<HistoryDB> historyDB;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM history WHERE material ="+id;

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            historyDB = new ArrayList<>();

            while (resultSet.next()) {
                HistoryDB historyDBs = new HistoryDB();
                historyDBs.setId(resultSet.getInt("id"));

                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT login FROM users WHERE id ="+ resultSet.getInt("user");
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                if (resultSet2.next()) {
              //  while (resultSet2.next()) {
                    historyDBs.setUser(resultSet2.getString(1));
              //  }
                }else {
                    historyDBs.setUser("Пользователь удалён");
                }

                historyDBs.setDate_time(resultSet.getString("date_time"));
                historyDBs.setChanging_type(resultSet.getString("changing_type"));
                historyDBs.setMaterial(resultSet.getInt("material"));
                historyDBs.setOld_value(resultSet.getString("old_value"));
                historyDBs.setNew_value(resultSet.getString("new_value"));
                historyDB.add(historyDBs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historyDB;
    }


    @Override
    public void deleteById(Integer id) {
     /*   String sql  = "DELETE FROM materials " +
                "WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
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
    @Override
    public void save(HistoryDB historyDB) {
        String sqlRequest = "INSERT INTO history (user, date_time, changing_type, material, old_value, new_value) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, findIdUser(historyDB.getUser()));
            statement.setString(2, formatter.format(calendar.getTime()));
            statement.setString(3, "ИЗМЕНЕНИЕ");
            statement.setInt(4, historyDB.getMaterial());
            statement.setString(5, historyDB.getOld_value());
            statement.setString(6, historyDB.getNew_value());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(HistoryDB historyDB) {
     /*   String sql = "UPDATE materials SET" +
                " name = ?," +
                " number = ?," +
                " type = ?," +
                " stock_quantity = ? ," +
                " order_quantity = ? " +
                " WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, historyDB.getName());
            preparedStatement.setString(2, historyDB.getNumber());
            preparedStatement.setString(3, historyDB.getType());
            preparedStatement.setInt(4, historyDB.getStock_quantity());
            preparedStatement.setInt(5, historyDB.getOrder_quantity());
            preparedStatement.setInt(6, historyDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
