package db;

import db.entities.Order;
import db.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String SQL_GET_ORDER_BY_USER_ID = "SELECT u.first_name, u.second_name, ol.address, ol.city, c.name" +
            " as country, dt.type as delivery_type, s.name as status FROM " +
            "order_list ol JOIN country c on c.id = ol.country_id\n" +
            "    JOIN delivery_type dt on dt.id = ol.delivery_type_id\n" +
            "    JOIN status s on s.id = ol.status_id\n" +
            "    JOIN users u on u.id = ol.user_id\n" +
            "    WHERE user_id = ?";
    private static final String SQL__FIND_ALL_ORDERS = "SELECT * FROM order_list";

    private static final String SQL__FIND_ORDERS_BY_STATUS_AND_USER =
            "SELECT * FROM order_list WHERE status_id = ? and user_id = ?";

    private static final String SQL__FIND_ORDERS_BY_STATUS =
            "SELECT * FROM order_list WHERE status_id = ?";


    /**
     * Returns all orders.
     *
     * @return List of order entities.
     */
    public List<Order> findOrders() {
        List<Order> ordersList = new ArrayList<Order>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_ORDERS);
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
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
    public List<Order> findOrders(int statusId) {
        List<Order> ordersList = new ArrayList<Order>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();
            pstmt = con.prepareStatement(SQL__FIND_ORDERS_BY_STATUS);
            pstmt.setInt(1, statusId);
            rs = pstmt.executeQuery();
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return ordersList;
    }

    public List<Order> findOrders(String[] ids){
        List<Order> orderList = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();

            //create SQL query like "... id IN(1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM order_list WHERE id IN("
            );
            for(String idAsString : ids)
                query.append(idAsString).append(',');
            query.append(')');

            statement = connection.createStatement();
            rs = statement.executeQuery(query.toString());
            while (rs.next())
                orderList.add(mapper.mapRow(rs));
        } catch (SQLException exception) {
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
    public List<Order> findOrders(User user, int statusId){
        List<Order> ordersList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection con = null;

        try{
            con = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();
            preparedStatement = con.prepareStatement(SQL__FIND_ORDERS_BY_STATUS_AND_USER);
            preparedStatement.setInt(1, statusId);
            preparedStatement.setLong(2, user.getId());
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                ordersList.add(mapper.mapRow(rs));
            }
        } catch (SQLException exception) {
            DBManager.getInstance().rollbackAndClose(con);
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return ordersList;
    }

    private static class OrderMapper implements EntityMapper<Order>{

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
                order.setStatus_id(rs.getLong(Fields.STATUS_ID));
                return order;
            } catch (SQLException exception) {
                throw new IllegalStateException(exception);
            }
        }
    }

}


