package com.example.demo.db.dao;

import com.example.demo.db.entities.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {
    private List<User> userList = new ArrayList<>();

    @BeforeClass
    public static void beforeClass(){
        DBMock.createMockDataBase();
    }

    @Before
    public void init(){
        userList.clear();

        userList.add(new User.Builder()
                .withFirstName("Ruslan")
                .withSecondName("Humeniuk")
                .withAddress("Pushkina 2")
                .withCity("Vynnitsia")
                .withCountyId(1)
                .withEmail("ruslan21343@gmail.com")
                .withPass("123")
                .withRoleId(0)
                .build());

        userList.add(new User.Builder()
                .withFirstName("Vasya")
                .withSecondName("Pupochkin")
                .withAddress("Vinni Puha 32")
                .withCity("Vynnitsia")
                .withCountyId(3)
                .withEmail("Vinni@ukr.net")
                .withPass("pass")
                .withRoleId(1)
                .build());
    }

    @Test
    public void shouldInsertUser() throws SQLException, IOException {
        User user = new User.Builder()
                .withFirstName("Ann")
                .withSecondName("Kovalchuk")
                .withEmail("ann@com.ua")
                .withPass("ann123")
                .withAddress("Privokzalna street 2")
                .withCity("Kyiv")
                .withRoleId(1)
                .withIsBanned(0)
                .withCountyId(3)
                .build();
        UserDAO userDAO = new UserDAO();

        userDAO.insertUser(user, DBMock.getInstance().getConnection());

        userList.add(user);

        assertEquals(userList.size(), userDAO.findAll(DBMock.getInstance().getConnection()).size());
        assertEquals(userList, userDAO.findAll(DBMock.getInstance().getConnection()));

        assertTrue(userDAO.deleteUserWithEmail(user.getEmail(), DBMock.getInstance().getConnection()));
    }

    @Test
    public void findByEmail() throws SQLException {
        assertEquals(userList.get(0), new UserDAO().findUser("ruslan21343@gmail.com", DBMock.getInstance().getConnection()));
    }

    @Test
    public void testReturnUsers() throws SQLException {
        Connection connection = DBMock.getInstance().getConnection();
        List<User> DBList = new UserDAO().findAll(connection);
        assertEquals(userList.size(), DBList.size());
        assertEquals(userList, DBList);
    }

    @Test
    public void shouldFindById() throws SQLException {
        assertEquals(userList.get(0), new UserDAO().findUser(1L, DBMock.getInstance().getConnection()));
        assertEquals(userList.get(1), new UserDAO().findUser(2L, DBMock.getInstance().getConnection()));
    }

    @Test
    public void updateUser() throws SQLException, IOException {
        UserDAO userDAO = new UserDAO();
        Connection connection = DBMock.getInstance().getConnection();

        User user = userDAO.findUser(1L, connection);
        user.setPass("1111");

        userDAO.updateUser(user, connection);

        assertEquals(user, userDAO.findUser("ruslan21343@gmail.com", connection));

        user.setPass("123");
        userDAO.updateUser(user, connection);
        assertEquals(user, userDAO.findUser("ruslan21343@gmail.com", connection));
    }

    //    @Test
//    public void shouldUpdateUser() throws SQLException, CloneNotSupportedException, IOException {
//        User user = new User.Builder()
//                .withFirstName("Ann")
//                .withSecondName("Kovalchuk")
//                .withEmail("ann@com.ua")
//                .withPass("ann123")
//                .withAddress("Privokzalna street 2")
//                .withCity("Kyiv")
//                .withRoleId(1)
//                .withIsBanned(0)
//                .withCountyId(3)
//                .build();
//        UserDAO userDAO = new UserDAO();
//        Connection connection = DBMock.getInstance().getConnection();
//
//        userDAO.insertUser(user, connection);
//
//        user.setPass("qwerty228");
//
//        user = userDAO.findUser(user.getEmail(), connection);
//
//        userList.add(user);
//        userDAO.updateUser(user, DBMock.getInstance().getConnection());
//        assertEquals(userList, userDAO.findAll(connection));
//
//        userDAO.deleteUserWithEmail(user.getEmail(), connection);
//    }

    @Test
    public void returnUserBean() throws SQLException {
        assertEquals("[Entity{id=1}, Entity{id=2}]", new UserDAO().findUsersBean(DBMock.getInstance().getConnection()).toString());
    }

    @AfterClass
    public static void tearDown(){
        DBMock.dropMockDataBase();
    }
}