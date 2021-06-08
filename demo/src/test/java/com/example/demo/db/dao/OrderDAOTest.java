package com.example.demo.db.dao;

import com.example.demo.db.entities.Order;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OrderDAOTest {
    private List<Order> orderList = new ArrayList<>();

    @BeforeClass
    public static void setUpClass(){
        DBMock.createMockDataBase();
    }

    @Before
    public void setUp() throws Exception {
        orderList.clear();

        orderList.add(new Order.Builder()
                .withUserId(1L)
                .withAddress("Vinni puha 2")
                .withCity("Kyiv")
                .withCountryId(1L)
                .withDeliveryTypeId(1L)
                .withStatusId(2)
                .build());
    }

    @Test
    public void shouldReturnAllOrders() throws SQLException {
        assertEquals(orderList.get(0), new OrderDAO().findOrders(DBMock.getInstance().getConnection()).get(0));
    }

    @Test
    public void makeOrder() throws SQLException {
        Order order = new Order.Builder()
                .withAddress("Some street")
                .withCity("NY")
                .withCountryId(2L)
                .withStatusId(2)
                .withDeliveryTypeId(3L)
                .withUserId(1L)
                .build();

        OrderDAO orderDAO = new OrderDAO();

        Map<Long, Integer> map = new HashMap<>();
        map.put(1L, 3);
        map.put(2L, 3);
        order.setItems(map);

        orderDAO.makeOrder(order, DBMock.getInstance().getConnection());

        assertEquals(4, orderDAO.findOrders(DBMock.getInstance().getConnection()).size());
    }

    @Test
    public void findOrdersWithGivenStatus() throws SQLException {
        assertEquals(2, new OrderDAO().findOrders(1, DBMock.getInstance().getConnection()).size());
    }

    @Test
    public void findOrdersWithGivenIds() throws SQLException {
        assertEquals(2, new OrderDAO().findOrders(new String[]{"1", "2"}, DBMock.getInstance().getConnection()).size());
    }

    @Test
    public void findOrdersForUserWithGivenStatus() throws SQLException {
        assertEquals(2, new OrderDAO().findOrders(new UserDAO().findUser(1L, DBMock.getInstance().getConnection()), 1 , DBMock.getInstance().getConnection()).size());
    }

    @Test
    public void updateStatus() throws SQLException {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.findOrderById(1L, DBMock.getInstance().getConnection());

        orderDAO.updateStatus(order, 3, DBMock.getInstance().getConnection());

        assertEquals(3, orderDAO.findOrderById(1L, DBMock.getInstance().getConnection()).getStatus_id());

        orderDAO.updateStatus(order, 2, DBMock.getInstance().getConnection());
    }

    @AfterClass
    public static void tearDown(){
        DBMock.dropMockDataBase();
    }
}