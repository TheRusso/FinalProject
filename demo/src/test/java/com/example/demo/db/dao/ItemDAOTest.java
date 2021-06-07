package com.example.demo.db.dao;

import com.example.demo.db.entities.Item;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ItemDAOTest {
    private List<Item> itemList = new ArrayList<>();

    @BeforeClass
    public static void startUp(){
        DBMock.createMockDataBase();
    }

    @Before
    public void init(){
        itemList.clear();

        itemList.add(new Item.Builder()
                .withTitle("Motanka T-Shirt")
                .withDescription("Very cool motanka t-shirt")
                .withPrice(Float.parseFloat("22.5"))
                .withCount(0)
                .withImg("/items/img/ref.jpg")
                .withQuantity(0)
                .build());

        itemList.add(new Item.Builder()
                .withTitle("SOLSTAFIR")
                .withDescription("ENDLESS TWILIGHT OF CODEPENDENT LOVE - CLEAR BLACK MARBLED 2-VINYL")
                .withPrice(Float.parseFloat("16.99"))
                .withCount(0)
                .withImg("/items/img/asd.jpg")
                .withQuantity(0)
                .build());

        itemList.add(new Item.Builder()
                .withTitle("POWERWOLF")
                .withDescription("CALL OF THE WILD - MEDIABOOK 2-CD")
                .withPrice(Float.parseFloat("24.99"))
                .withCount(0)
                .withImg("/items/img/pawerwolfcd.png")
                .withQuantity(0)
                .build());
    }

    @Test
    public void shouldFindAllItems() throws SQLException {
        assertEquals(itemList.get(0), new ItemDAO().findAllItems(DBMock.getInstance().getConnection()).get(0));
        assertEquals(itemList.get(1), new ItemDAO().findAllItems(DBMock.getInstance().getConnection()).get(1));
        assertEquals(itemList.get(2), new ItemDAO().findAllItems(DBMock.getInstance().getConnection()).get(2));
    }

    @Test
    public void shouldInsertItem() throws SQLException {
        Item item = new Item.Builder()
                .withTitle("PINK FLOYD")
                .withDescription("DSOTM ESCHER - T-SHIRT")
                .withPrice(Float.parseFloat("19.99"))
                .withImg("/items/img/pinkfloyd.jpg")
                .withCategory_id(1)
                .build();
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.insertItem(item, DBMock.getInstance().getConnection());
        itemDAO.deleteItem(4L, DBMock.getInstance().getConnection());
        assertEquals(itemList, itemDAO.findAllItems(DBMock.getInstance().getConnection()));

    }

    @Test
    public void shouldReturnCountOfItems() throws SQLException {
        assertEquals(3, new ItemDAO().countOfItems(DBMock.getInstance().getConnection()));
    }

    @Test
    public void itemsPage() throws SQLException {
        assertEquals(itemList, new ItemDAO().findItemsPage(1, "id", "ASC", Arrays.asList("clothes", "music", "other"), DBMock.getInstance().getConnection()));
    }

    @Test
    public void shouldUpdate() throws SQLException {
        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.findItemById(1L, DBMock.getInstance().getConnection());
        item.setTitle("Test");
        itemDAO.updateItem(item, DBMock.getInstance().getConnection());

        itemList.get(0).setTitle("Test");

        assertEquals(itemList, itemDAO.findAllItems(DBMock.getInstance().getConnection()));

        item.setTitle("Motanka T-Shirt");
        itemDAO.updateItem(item, DBMock.getInstance().getConnection());
    }

    @Test
    public void findBean() throws SQLException {
        assertNotNull(new OrderDAO().findBeanAllOrders(DBMock.getInstance().getConnection()));
    }

    @Test
    public void findBeanForUser() throws SQLException {
        assertNotNull(new OrderDAO().findBeanForUser(new UserDAO().findUser(1L, DBMock.getInstance().getConnection()), DBMock.getInstance().getConnection()));
    }

    @Test
    public void findItemsPage() throws SQLException {
        assertEquals(itemList, new ItemDAO().findItemsPage(1, DBMock.getInstance().getConnection()));
    }

    @Test
    public void findByID() throws SQLException {
        assertEquals(itemList.get(0), new ItemDAO().findItemById(1L, DBMock.getInstance().getConnection()));
    }

    @Test
    public void shouldReturnCountOfItemsAfterInserting() throws SQLException {
        Item item = new Item.Builder()
                .withTitle("PINK FLOYD")
                .withDescription("DSOTM ESCHER - T-SHIRT")
                .withPrice(Float.parseFloat("19.99"))
                .withImg("/items/img/pinkfloyd.jpg")
                .withCategory_id(1)
                .build();
        ItemDAO itemDAO = new ItemDAO();

        itemDAO.insertItem(item, DBMock.getInstance().getConnection());

        System.out.println(new ItemDAO().findAllItems(DBMock.getInstance().getConnection()));
        assertEquals(3, new ItemDAO().countOfItems(DBMock.getInstance().getConnection()));
    }

    @AfterClass
    public static void tearDown(){
        DBMock.dropMockDataBase();
    }

}