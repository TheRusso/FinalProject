package com.example.demo.db.dao;

import com.example.demo.db.ShopChooseQuery;
import com.example.demo.db.DBManager;
import com.example.demo.db.EntityMapper;
import com.example.demo.db.Fields;
import com.example.demo.db.entities.Item;
import com.example.demo.utils.DBHandlerUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private Logger logger = Logger.getLogger(ItemDAO.class.getName());

    private int itemsPerPage = 3;

    /**
     * Inserting new item into DB
     *
     * @param item
     *          Item entity
     * @return is inserted or not
     *
     * @throws SQLException
     */
    public boolean insertItem(Item item, Connection connection) {
        logger.info("Start updating user");
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            DBHandlerUtil.getInstance().getSQL(
                                    SQLProperyNamesHandler.ITEM_INSERT.getPropertyName()))){
            connection = DBManager.getInstance().getConnection();

            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setDouble(4, item.getCount());
            preparedStatement.setLong(5, item.getCategory_id());
            preparedStatement.setString(6, item.getImg());

            preparedStatement.executeUpdate();

            logger.info("Have inserted with: " + preparedStatement);

        } catch (SQLException | IOException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.warn(exception.getMessage());
            return false;
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return true;
    }

    /**
     * Deletes item with given id
     *
     * @param id
     * @param connection
     *          DB connection
     * @return is Deleted
     */
    public boolean deleteItem(Long id, Connection connection){
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ITEM_DELETE.getPropertyName()))){

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException exception) {
            logger.error(exception);

            return false;
        }

        return true;
    }

    /**
     * Updating Item
     *
     * @param item
     *          Item entity
     * @return is updated or not
     *
     * @throws SQLException
     */
    public boolean updateItem(Item item, Connection connection) {
        logger.info("Start updating user");

        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ITEM_UPDATE.getPropertyName()))){
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setDouble(4, item.getDisable());
            preparedStatement.setLong(5, item.getId());

            preparedStatement.executeUpdate();

            logger.info("Have done update with: " + preparedStatement);

        } catch (SQLException | IOException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.warn(exception.getMessage());
            return false;
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return true;
    }

    /**
     * Finds item by given id
     *
     * @param id
     *          id of an item
     * @return Item
     *          Item entity
     */
    public Item findItemById(Long id, Connection connection){
        Item item = null;
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ITEM_FIND_BY_ID.getPropertyName()))){
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            ItemMapper mapper = new ItemMapper();

            resultSet.next();
            item = mapper.mapRow(resultSet);

        } catch (SQLException | IOException exception) {
            logger.warn(exception.getMessage());
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return item;
    }

    /**
     * Finds count of pages with included filters
     *
     *
     * @param sortBy
     *          param that we sort items by
     * @param order
     *          ascending of descending
     * @param categories
     *          given categories that will be apply to SQL
     * @return count of pages
     */
    public int countOfPages(String sortBy, String order, List<String> categories, Connection connection){
        return (int)Math.ceil((double) countOfItems(sortBy, order, categories, connection) / itemsPerPage);
    }

    /**
     * Returns count of items with given filters
     *
     *
     * @param sortBy
     *          param that we sort items by
     * @param order
     *          ascending of descending
     * @param categories
     *          given categories that will be apply to SQL
     * @return count of items
     */
    public int countOfItems(String sortBy, String order, List<String> categories, Connection connection){
        Statement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try{
            String sql = ShopChooseQuery.getCountSQL(sortBy, order, categories);
            logger.info("Sql for count of pages: " + sql);
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            resultSet.next();
            count = resultSet.getInt("count");

        } catch (SQLException exception) {
            logger.warn(exception.getMessage());
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return count;
    }

    /**
     * Returns count of all items
     *
     * @return count of all items
     */
    public int countOfItems(Connection connection){
        Statement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try{
            statement = connection.createStatement();

            resultSet = statement.executeQuery(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ITEM_COUNT.getPropertyName()));

            resultSet.next();
            count = resultSet.getInt("count");

        } catch (SQLException | IOException exception) {
            logger.warn(exception.getMessage());
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return count;
    }

    /**
     * Returns list of items in given page
     *
     * @param page
     * @return list of Item entity
     */
    public List<Item> findItemsPage(int page, Connection connection){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Item> items = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ITEM_FIND_FOR_PAGE.getPropertyName()));
            preparedStatement.setInt(1, itemsPerPage * (page - 1));
            preparedStatement.setInt(2, itemsPerPage);

            ResultSet rs = preparedStatement.executeQuery();

            ItemMapper mapper = new ItemMapper();
            while (rs.next())
                items.add(mapper.mapRow(rs));

        } catch (SQLException | IOException exception) {
            logger.warn(exception.getMessage());
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return items;
    }

    /**
     * Returns list of items with given filters
     *
     * @param page
     *
     * @param sortBy
     *          param that we sort items by
     * @param order
     *          ascending of descending
     * @param categories
     *          given categories that will be apply to SQL
     * @return list of Item entities
     *
     */
    public List<Item> findItemsPage(int page, String sortBy,
                                    String order,
                                    List<String> categories,
                                    Connection connection){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Item> items = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(ShopChooseQuery.getSQL(sortBy, order, categories));

            preparedStatement.setInt(1, itemsPerPage * (page - 1));
            preparedStatement.setInt(2, itemsPerPage);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            ItemMapper mapper = new ItemMapper();
            while (rs.next())
                items.add(mapper.mapRow(rs));

        } catch (SQLException exception) {
            logger.warn(exception.getMessage());
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return items;
    }

    /**
     * Returns list of all items
     *
     * @return list of all item entities
     */
    public List<Item> findAllItems(Connection connection){
        Statement statement = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();

        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ITEM_FIND_ALL.getPropertyName()));
            ItemMapper mapper = new ItemMapper();

            while (rs.next())
                items.add(mapper.mapRow(rs));
        } catch (SQLException | IOException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.warn(exception.getMessage());
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return items;
    }

    /**
     * Extracts an Item from the result set row.
     */
    private static class ItemMapper implements EntityMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs) {
            try{
                Item item = new Item();
                item.setId(rs.getLong(Fields.ENTITY_ID));
                item.setTitle(rs.getString(Fields.ITEM_TITLE));
                item.setDescription(rs.getString(Fields.ITEM_DESCRIPTION));
                item.setPrice(rs.getFloat(Fields.ITEM_PRICE));
                item.setImg(rs.getString(Fields.ITEM_IMG));
                item.setImg(rs.getString(Fields.ITEM_IMG));
                item.setDisable(rs.getInt(Fields.ITEM_DISABLE));
                return item;
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
        }
    }
}
