package db;

import com.example.demo.ShopChoose;
import db.entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private int itemsPerPage = 1;

    private static final String SQL__FIND_ALL_ITEMS =
            "SELECT * FROM items";

    private static final String SQL__COUNT_OF_ITEMS =
            "SELECT COUNT(id) as count FROM items";

    private static final String SQL__FIND_PAGE_ORDER_DESC =
            "SELECT * FROM items ORDER by price DESC LIMIT ?, ?";

    private static final String SQL__FIND_PAGE_ORDER_ASC =
            "SELECT * FROM items ORDER by ? LIMIT ?, ?";

    private static final String SQL__FIND_ITEMS_FOR_PAGE =
            "SELECT * FROM items LIMIT ?,?";

    private static final String SQL__FIND_ALL_ORDERED_ITEMS_BY_USER =
            "SELECT o.order_id ,items.id as item_id, items.title," +
                    " items.price, items.count, o.quantity FROM items " +
                    "JOIN orders o on items.id = o.item_id WHERE id IN " +
                    "(SELECT item_id FROM orders JOIN order_list ol ON " +
                    "order_id = ol.id JOIN users u on u.id = ol.user_id" +
                    " WHERE u.id = ?)";

    public static final String SQL__FIND_ITEM_BY_ID =
            "SELECT * FROM items WHERE id = ?";

    public Item findItemById(Long id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Item item = null;
        ResultSet resultSet = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL__FIND_ITEM_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            ItemMapper mapper = new ItemMapper();

            resultSet.next();
            item = mapper.mapRow(resultSet);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return item;
    }

    public int countOfPages(String sortBy, String order, List<String> categories){
        return countOfItems(sortBy, order, categories) / itemsPerPage;
    }

    public int countOfItems(String sortBy, String order, List<String> categories){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try{
            connection = DBManager.getInstance().getConnection();


            String sql = ShopChoose.getSQL(sortBy, order, categories).replace("*", "COUNT(id) as count").replace(" LIMIT ?, ?", "");
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            resultSet.next();
            count = resultSet.getInt("count");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return count;
    }

    public int countOfItems(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try{
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL__COUNT_OF_ITEMS);

            resultSet.next();
            count = resultSet.getInt("count");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return count;
    }

    public List<Item> findItemsPage(int page){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Item> items = new ArrayList<>();
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL__FIND_ITEMS_FOR_PAGE);
            preparedStatement.setInt(1, itemsPerPage * (page - 1));
            preparedStatement.setInt(2, itemsPerPage);

            ResultSet rs = preparedStatement.executeQuery();

            ItemMapper mapper = new ItemMapper();
            while (rs.next())
                items.add(mapper.mapRow(rs));

        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return items;
    }

    public List<Item> findItemsPage(int page, String sortBy, String order, List<String> categories){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Item> items = new ArrayList<>();
        try{
            connection = DBManager.getInstance().getConnection();


            preparedStatement = connection.prepareStatement(ShopChoose.getSQL(sortBy, order, categories));

            preparedStatement.setInt(1, itemsPerPage * (page - 1));
            preparedStatement.setInt(2, itemsPerPage);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            ItemMapper mapper = new ItemMapper();
            while (rs.next())
                items.add(mapper.mapRow(rs));

        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return items;
    }

    public List<Item> findAllItems(){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();

        try{
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL__FIND_ALL_ITEMS);
            ItemMapper mapper = new ItemMapper();

            while (rs.next())
                items.add(mapper.mapRow(rs));
        } catch (SQLException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return items;
    }

    private static class ItemMapper implements EntityMapper<Item>{

        @Override
        public Item mapRow(ResultSet rs) {
            try{
                Item item = new Item();
                item.setId(rs.getLong(Fields.ENTITY_ID));
                item.setTitle(rs.getString(Fields.ITEM_TITLE));
                item.setDescription(rs.getString(Fields.ITEM_DESCRIPTION));
                item.setPrice(rs.getFloat(Fields.ITEM_PRICE));
                item.setCount(rs.getInt(Fields.ITEM_COUNT));
                item.setImg(rs.getString(Fields.ITEM_IMG));
                return item;
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
        }
    }
}
