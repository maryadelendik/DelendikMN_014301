package com.example.cp.authorization;

import com.example.cp.DatabaseConnection;
import com.example.cp.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorizationTest {

    @Mock
    private DatabaseConnection databaseConnection;

    @InjectMocks
    private UserController userController;

    @Test
    public void testNewPassword() throws Exception {
        MockitoAnnotations.initMocks(this);
        Connection connection = mock(Connection.class);
        when(databaseConnection.getConnection()).thenReturn(connection);
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(1)).thenReturn("HASH");
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        Authorization auth = new Authorization();
        auth.setLogin("TEST");
        auth.setPassword("NEW");
        ResponseEntity<Authorization> responseEntity = userController.newPassword(auth);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(connection).createStatement();
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeUpdate();
        verify(resultSet, times(2)).next();
        verify(resultSet).getString(1);
    }
}

