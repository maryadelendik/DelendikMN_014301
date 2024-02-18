package com.example.cp.users;



import java.util.List;

public interface Users {

    List<UsersDB> getAll();

    void deleteByLogin(String login);

    void save(UsersDB usersDB);

    void update(UsersDB usersDB);
}
