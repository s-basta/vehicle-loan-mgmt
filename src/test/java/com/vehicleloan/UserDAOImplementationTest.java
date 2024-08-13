//package com.vehicleloan;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import com.vehicleloan.constant.Gender;
//import com.vehicleloan.constant.TypeOfEmployment;
//import com.vehicleloan.dao.user.User;
//import com.vehicleloan.dao.user.UserDAO;
//import com.vehicleloan.dao.user.UserDAOImplementation;
//
//public class UserDAOImplementationTest {
//    
//    UserDAO userDAO;
//    User currentUser;
//    Integer userId;
//
//    @BeforeEach
//    public void init() {
//        userDAO = new UserDAOImplementation();
//        // Create a user to be used in the tests
//        currentUser = new User(null, "John", "Doe", LocalDate.of(1985, 5, 15), Gender.MALE, "johndoe", "john.doe@example.com", "1234567890", "password", false, TypeOfEmployment.SALARIED, 50000.0, "ABCDE1234F", "1234-5678-9012");
//        userId = userDAO.create(currentUser);
//    }
//
//    @AfterEach
//    public void over() {
//        // Cleanup code if needed, e.g., delete the user from the database
//        if (userId != null) {
//            userDAO.deleteById(userId);
//        }
//        userDAO = null;
//    }
//
//    @Test
//    public void testCreateUser() {
//        // Verify user creation and retrieval
//        Assertions.assertNotNull(userId);
//        User retrievedUser = userDAO.get(userId);
//        Assertions.assertNotNull(retrievedUser);
//        Assertions.assertEquals(currentUser.getEmail(), retrievedUser.getEmail());
//    }
//
//    @Test
//    public void testGetUserById() {
//        // Verify user retrieval by ID
//        User retrievedUser = userDAO.get(userId);
//        Assertions.assertNotNull(retrievedUser);
//        Assertions.assertEquals(currentUser.getEmail(), retrievedUser.getEmail());
//    }
//
//    @Test
//    public void testGetUserByEmailAndPassword() {
//        // Verify user retrieval by email and password
//        User retrievedUser = userDAO.get(currentUser.getEmail(), currentUser.getPassword());
//        Assertions.assertNotNull(retrievedUser);
//        Assertions.assertEquals(currentUser.getEmail(), retrievedUser.getEmail());
//        Assertions.assertEquals(currentUser.getPassword(), retrievedUser.getPassword());
//    }
//    
//    @Test
//    public void testGetAllUsers() {
//        // Verify retrieval of all users
//        List<User> users = userDAO.getAll();
//        Assertions.assertNotNull(users);
//        Assertions.assertTrue(users.size() > 0);
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void testUserUpdate() {
//        String newPassword = "newpassword";
//        String newEmail = "new.email@example.com";
//
//        currentUser.setUserId(userId);
//        currentUser.setPassword(newPassword);
//        currentUser.setEmail(newEmail);
//
//        boolean isUpdated = userDAO.update(currentUser);
//        Assertions.assertTrue(isUpdated, "User should be updated successfully");
//
//        User updatedUser = userDAO.get(userId);
//        Assertions.assertNotNull(updatedUser, "Updated user should not be null");
//
//        Assertions.assertEquals(newPassword, updatedUser.getPassword(), "Password should be updated");
//        Assertions.assertEquals(newEmail, updatedUser.getEmail(), "Email should be updated");
//    }
//    
//    @Test
//    public void testUserDeletionByUserId() {
//        // Verify user deletion
//        boolean isDeleted = userDAO.deleteById(userId);        
//        Assertions.assertTrue(isDeleted);
//        User retrievedUser = userDAO.get(userId);
//        Assertions.assertNull(retrievedUser);
//    }
//}