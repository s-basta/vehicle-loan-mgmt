package com.vehicleloan;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.*;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import com.vehicleloan.constant.Gender;
import com.vehicleloan.constant.TypeOfEmployment;
import com.vehicleloan.dao.user.User;
import com.vehicleloan.dao.user.UserDAOImplementation;

public class UserDAOImplementationTest {
    
    @Mock
    private Connection conn;
    
    @Mock
    private PreparedStatement pst;
    
    @Mock
    private Statement statement;
    
    @Mock
    private ResultSet resultSet;
    
    @InjectMocks
    private UserDAOImplementation userDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(conn.createStatement()).thenReturn(statement);
        when(conn.prepareStatement(anyString())).thenReturn(pst);
    }
    
    @Test
    public void testCreateUser() throws SQLException {
        User user = new User(1, "John", "Doe", LocalDate.of(1985, 5, 15), Gender.MALE, "johndoe", "john.doe@example.com", "1234567890", "password", false, TypeOfEmployment.SALARIED, 50000.0, "ABCDE1234F", "1234-5678-9012");

        when(pst.executeUpdate()).thenReturn(1);
        
        boolean result = userDAO.create(user);
        
        assertTrue(result);
    }
    
    @Test
    public void testGetUserById() throws SQLException {
        User expectedUser = new User(1, "John", "Doe", LocalDate.of(1985, 5, 15), Gender.MALE, "johndoe", "john.doe@example.com", "1234567890", "password", false, TypeOfEmployment.SALARIED, 50000.0, "ABCDE1234F", "1234-5678-9012");

        when(statement.executeQuery("select * from vloanUser where userid=1")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(userDAO.resultSetToUserConvertor(resultSet)).thenReturn(expectedUser);

        User result = userDAO.get(1);
        
        assertNotNull(result);
        assertEquals(expectedUser, result);
    }
    
    @Test
    public void testGetUserByUsernameAndPassword() throws SQLException {
        User user = new User(1, "John", "Doe", LocalDate.of(1985, 5, 15), Gender.MALE, "johndoe", "john.doe@example.com", "1234567890", "password", false, TypeOfEmployment.SALARIED, 50000.0, "ABCDE1234F", "1234-5678-9012");

        when(userDAO.getByEmail("johndoe")).thenReturn(null);
        when(userDAO.getByUsername("johndoe")).thenReturn(user);
        
        User result = userDAO.get("johndoe", "password");

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() throws SQLException {
        User user = new User(1, "John", "Doe", LocalDate.of(1985, 5, 15), Gender.MALE, "johndoe", "john.doe@example.com", "1234567890", "password", false, TypeOfEmployment.SALARIED, 50000.0, "ABCDE1234F", "1234-5678-9012");

        when(pst.executeUpdate()).thenReturn(1);
        
        boolean result = userDAO.update(user);
        
        assertTrue(result);
    }
    
    @Test
    public void testDeleteUserById() throws SQLException {
        when(pst.executeUpdate()).thenReturn(1);
        
        boolean result = userDAO.deleteById(1);
        
        assertTrue(result);
    }
}
