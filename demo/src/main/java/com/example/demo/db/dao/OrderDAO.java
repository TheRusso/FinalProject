package com.example.demo.db.dao;

import com.example.demo.db.DBManager;
import com.example.demo.db.EntityMapper;
import com.example.demo.db.Fields;
import com.example.demo.db.ShopChooseQuery;
import com.example.demo.db.bean.UserOrderBean;
import com.example.demo.db.entities.Item;
import com.example.demo.db.entities.Order;
import com.example.demo.db.entities.User;
import com.example.demo.services.service_impl.ItemService;
import com.example.demo.utils.DBHandlerUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class OrderDAO {

    private Logger logger = Logger.getLogger(OrderDAO.class.getName());



    /**
     * Returns order by id
     *
     * @param id
     *
     * @return Order
     *          Order Entity
     *
     */
    public Order findOrderById(Long id, Connection connection){
        ResultSet resultSet = null;
        Order order = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ORDER_FIND_BY_ID.getPropertyName()))){
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                order = new OrderMapper().mapRow(resultSet);

            preparedStatement.close();
        } catch (SQLException | IOException exception) {
            logger.warn(exception.getMessage());
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return order;
    }

    /**
     * Updating status of order
     *
     * @param id
     *         id of an order
     * @param status_id
     *         id of an status
     * @return is updated or not
     */
    public boolean updateStatus(Long id, Integer status_id, Connection connection){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL__ORDER_UPDATE_STATUS.getPropertyName()))){
            preparedStatement.setLong(1, status_id);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
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
     * Updates order
     *
     * @param order
     *          Order entity
     * @return is updated
     *
     */
    public boolean update(Order order, Connection connection){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ORDER_UPDATE.getPropertyName()))){
            int k = 0;
            preparedStatement.setLong(++k, order.getUser_id());
            preparedStatement.setString(++k, order.getAddress());
            preparedStatement.setString(++k, order.getCity());
            preparedStatement.setLong(++k, order.getCountry_id());
            preparedStatement.setLong(++k, order.getDelivery_type_id());
            preparedStatement.setLong(++k, order.getStatus_id());
            preparedStatement.setLong(++k, order.getId());

            preparedStatement.executeUpdate();
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
     * Updating status of order
     *
     *
     * @param order
     *          Order entity
     *
     * @param status_id
     *         Integer - Id of a status
     *
     * @return is updated or not
     */
    public boolean updateStatus(Order order, Integer status_id, Connection connection){
        return updateStatus(order.getId(), status_id, connection);
    }

    /**
     * Returns all Beans of UserOrderBean
     *
     * @return List of UserOrderBean
     */
    public List<UserOrderBean> findBeanAllOrders(Connection connection){
        ResultSet resultSet = null;

        List<UserOrderBean> userOrderBeanList = new ArrayList<>();

        try(Statement statement = connection.createStatement()){
            logger.info("Start");
            resultSet = statement.executeQuery(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL__ORDER_ALL_USERS_BEAN.getPropertyName()));

            UserBeanMapper mapper = new UserBeanMapper();

            logger.info("Mapping");

            while (resultSet.next())
                userOrderBeanList.add(mapper.mapRow(resultSet));

            logger.info("User Bean: " + userOrderBeanList);

            userOrderBeanList = concatenateUserOrderBean(userOrderBeanList);

            logger.info("Concatenated user Bean: " + userOrderBeanList);

            connection.close();
            statement.close();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }

        return userOrderBeanList;
    }

    public List<UserOrderBean> findBeanForUser(User user, Connection connection){
        logger.info("User: " + user);
        return findBeanForUser(user.getId(), connection);
    }

    /**
     * Returns Bean for user
     *
     * @param id
     *         user id
     * @return List of UserOrderBean
     */
    public List<UserOrderBean> findBeanForUser(Long id, Connection connection){
        logger.info("Id user: " + id);

        ResultSet resultSet = null;

        List<UserOrderBean> userOrderBeanList = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL_ORDER_BEAN__ALL_USERS.getPropertyName()))){
            logger.info("Start");

            preparedStatement.setLong(1,id);

            logger.info(preparedStatement);

            resultSet = preparedStatement.executeQuery();

            UserBeanMapper mapper = new UserBeanMapper();

            logger.info("Mapping");

            while (resultSet.next())
                userOrderBeanList.add(mapper.mapRow(resultSet));

            logger.info("User Bean: " + userOrderBeanList);

            userOrderBeanList = concatenateUserOrderBean(userOrderBeanList);

            logger.info("Concatenated user Bean: " + userOrderBeanList);

            connection.close();
            preparedStatement.close();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }

        return userOrderBeanList;
    }

    /**
     * Make order. Includes inserting data into order_list and items into orders
     * @param order
     *         Order entity
     * @return boolean
     *          isInserted or not
     */
    public boolean makeOrder(Order order, Connection connection){
        Long id = addOrderList(order, connection);
        order.setId(id);
        return addItemsToOrder(order, connection);
    }

    /**
     * Insert items to orders table
     *
     * @param order
     *          Order entity
     */
    private boolean addItemsToOrder(Order order, Connection connection){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL__ITEMS_ADD_TO_ORDER.getPropertyName()))){
            logger.info("Start adding an items to orders");
            logger.info(order.getItems());
            for (Map.Entry<Long, Integer> entry:
                 order.getItems().entrySet()) {
                    logger.info(entry);

                    preparedStatement.setLong(1, entry.getKey());
                    preparedStatement.setLong(2, order.getId());
                    preparedStatement.setLong(3, entry.getValue());

                    preparedStatement.executeUpdate();

                    DBManager.getInstance().commitAndClose(connection);
                    preparedStatement.close();
            }
            logger.info("Successfully added all items to orders");
        } catch (SQLException | IOException exception) {
            logger.warn(exception.getMessage());
            return false;
        }

        return true;
    }


    /**
     * Insert order into order_list table
     *
     * @param order
     *
     * @return id
     *         Id of inserted order
     */
    private Long addOrderList(Order order, Connection connection){
        long id = -1L;
        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL__ORDER_ADD_TO_ORDER.getPropertyName()),
                Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1, order.getUser_id());
            preparedStatement.setString(2, order.getAddress());
            preparedStatement.setString(3, order.getCity());
            preparedStatement.setLong(4, order.getCountry_id());
            preparedStatement.setLong(5, order.getDelivery_type_id());
            preparedStatement.setLong(6, order.getStatus_id());

            int affectedRows = preparedStatement.executeUpdate();

            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if(generatedKeys.next())
                    id = generatedKeys.getLong(1);
            }

            DBManager.getInstance().commitAndClose(connection);
        } catch (SQLException | IOException exception) {
            logger.warn(exception.getMessage());
        }

        return id;
    }

        /**
         * Returns all orders.
         *
         * @return List of order entities.
         */
    public List<Order> findOrders(Connection connection) {
        List<Order> ordersList = new ArrayList<Order>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            OrderMapper mapper = new OrderMapper();
            rs = stmt.executeQuery(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL__ORDER_FIND_ALL.getPropertyName()));
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException | IOException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return ordersList;
    }


    /**
     * Returns orders with the given status.
     *
     * @param statusId
     *            Status identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(int statusId, Connection connection) {
        List<Order> ordersList = new ArrayList<Order>();
        ResultSet rs = null;
        try(PreparedStatement pstmt = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL__ORDER_FIND_BY_STATUS.getPropertyName()))) {
            OrderMapper mapper = new OrderMapper();
            pstmt.setInt(1, statusId);
            rs = pstmt.executeQuery();
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException | IOException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return ordersList;
    }

    /**
     * Returns List of orders with given id`s
     *
     * @param ids
     *         array with id`s of orders
     *
     * @return List of order entities
     */
    public List<Order> findOrders(String[] ids, Connection connection){
        List<Order> orderList = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            OrderMapper mapper = new OrderMapper();

            statement = connection.createStatement();
            rs = statement.executeQuery(ShopChooseQuery.makeSQL_find_orders(ids));
            while (rs.next())
                orderList.add(mapper.mapRow(rs));
        } catch (SQLException | IOException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return orderList;
    }

    /**
     * Returns orders of the given user and status
     *
     * @param user
     *            User entity.
     * @param statusId
     *            Status identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(User user, int statusId, Connection connection){
        List<Order> ordersList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            OrderMapper mapper = new OrderMapper();
            preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL__ORDER_FIND_BY_STATUS_AND_USER.getPropertyName()));
            preparedStatement.setInt(1, statusId);
            preparedStatement.setLong(2, user.getId());
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                ordersList.add(mapper.mapRow(rs));
            }
        } catch (SQLException | IOException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return ordersList;
    }

    /**
     * Extracts an order from the result set row.
     */
    private static class OrderMapper implements EntityMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs) {
            try{
                Order order = new Order();
                order.setId(rs.getLong(Fields.ENTITY_ID));
                order.setUser_id(rs.getLong(Fields.USER_ID));
                order.setAddress(rs.getString(Fields.ADDRESS));
                order.setCity(rs.getString(Fields.CITY));
                order.setCountry_id(rs.getLong(Fields.COUNTRY_ID));
                order.setDelivery_type_id(rs.getLong(Fields.DELIVERY_TYPE_ID));
                order.setStatus_id(rs.getInt(Fields.STATUS_ID));
                return order;
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
        }
    }

    /**
     * Extracts an UserBean from the result set row.
     */
    private static class UserBeanMapper implements EntityMapper<UserOrderBean>{

        @Override
        public UserOrderBean mapRow(ResultSet rs) {
            UserOrderBean userOrderBean = new UserOrderBean();
            try{
                userOrderBean.setId(rs.getLong("id"));
                userOrderBean.setAddress(rs.getString("address"));
                userOrderBean.setCity(rs.getString("city"));
                userOrderBean.setEmail(rs.getString("email"));
                userOrderBean.setCountry(rs.getString("country"));
                userOrderBean.setDelivery_type(rs.getString("delivery_type"));
                userOrderBean.setStatus(rs.getString("status"));
                Map<Item, Integer> items = new HashMap<>();
                items.put(new ItemService().findById(rs.getLong("item_id")), rs.getInt("quantity"));
                userOrderBean.setItems(items);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            return userOrderBean;
        }
    }

    /**
     * Concatenate same UserOrderBeans with different items into one
     */
    private List<UserOrderBean> concatenateUserOrderBean(List<UserOrderBean> userBeans){
        List<UserOrderBean> result = new ArrayList<>();

        //  userOrderBean  id
        Map<UserOrderBean, Long> sort = new HashMap<>();
        //   id    index in list
        Map<Long, Integer> indexHandler = new HashMap<>();
        int index = 0;

        for (UserOrderBean u :
                userBeans) {
            sort.put(u, u.getId());
        }

        for (Map.Entry<UserOrderBean, Long> entry:
                 sort.entrySet()){
            if(!indexHandler.containsKey(entry.getValue())){
                result.add(entry.getKey());
                indexHandler.put(entry.getValue(), index++);
            }else{
                result.get(indexHandler.get(entry.getValue())).getItems().putAll(entry.getKey().getItems());
            }
        }

        return result;
    }

}


