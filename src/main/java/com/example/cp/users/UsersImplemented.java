package com.example.cp.users;


import com.example.cp.DatabaseConnection;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UsersImplemented implements Users {

    Connection connection = DatabaseConnection.getConnection();

/*
    @Override
    public List<UsersDB> getAll() {
        List<UsersDB> usersDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM teachers";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            usersDB = new ArrayList<>();

            while (resultSet.next()) {
                UsersDB usersDBs = new UsersDB();
                usersDBs.setId(resultSet.getInt("idTeachers"));
                usersDBs.setName(resultSet.getString("name"));
                usersDBs.setSurname(resultSet.getString("surname"));
                usersDBs.setLastname(resultSet.getString("lastname"));
                usersDBs.setIdentificator(resultSet.getString("identificator"));

                usersDB.add(usersDBs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usersDB;
    }
*/
  /*  @Override
    public void deleteById(UsersDB usersDB) {
        String sql  = "DELETE FROM teachers " +
                "WHERE idTeachers = " + usersDB.getId();

        String sql1  = "DELETE FROM UserAccounts " +
                "WHERE login = '" + usersDB.getIdentificator() + "' ";
        String sql2  = "DELETE FROM subject " +
                "WHERE id_teacher = " + usersDB.getId();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


    @Override
    public List<UsersDB> getAll() {
        return null;
    }

    @Override
    public void deleteByLogin(String login) {
        String sql  = "DELETE FROM users " +
                "WHERE login = '" + login+"'";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(UsersDB usersDB) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String sqlRequest = "INSERT INTO users (login, first_password, role) " +
                "VALUES(?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, usersDB.getLogin());
            statement.setString(2, encoder.encode(usersDB.getFirst_password()));
            statement.setBoolean(3, usersDB.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UsersDB usersDB) {

    }

  /*  @Override
    public void update(UsersDB usersDB) {
        String sql = "UPDATE teachers SET" +
                " name = ?," +
                " surname = ?," +
                " lastname = ?," +
                " identificator = ? " +
                " WHERE idTeachers = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, usersDB.getName());
            preparedStatement.setString(2, usersDB.getSurname());
            preparedStatement.setString(3, usersDB.getLastname());
            preparedStatement.setString(4, usersDB.getIdentificator());
            preparedStatement.setInt(5, usersDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/




}
