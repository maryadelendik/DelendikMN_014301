package com.example.cp.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UsersImplementedTest {

    private Users users;

    @BeforeEach
    public void initTest() {
        users = new UsersImplemented();
    }

    @Test
    public void testGetAll() {
        List<UsersDB> usersList = users.getAll();
        assertNotNull(usersList);
    }

    @Test
    public void testDeleteByLogin() {
        String loginToDelete = "TEST";
        users.deleteByLogin(loginToDelete);
    }

    @Test
    public void testSave() {
        UsersDB newUser = new UsersDB();
        newUser.setLogin("TEST");
        newUser.setFirst_password("TEST");
        newUser.setRole(false);
        users.save(newUser);
    }


}

