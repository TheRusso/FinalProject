package com.example.demo.db.dao;

import com.example.demo.db.DBManager;
import com.example.demo.db.EntityMapper;
import com.example.demo.db.Fields;
import com.example.demo.db.bean.UsersBean;
import com.example.demo.db.entities.User;
import com.example.demo.utils.DBHandlerUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Logger logger = Logger.getLogger(UserDAO.class.getName());

    /**
     * Insert new user into a database
     *
     * @param user
     *          User entity
     */
    public boolean insertUser(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.USER_INSERT.getPropertyName()));

            System.out.println(connection);

            int k = 1;
            preparedStatement.setString(k++, user.getFirst_name());
            preparedStatement.setString(k++, user.getSecond_name());
            preparedStatement.setString(k++, user.getAddress());
            preparedStatement.setString(k++, user.getCity());
            preparedStatement.setInt(k++, user.getCountry_id());
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k, user.getPass());
            preparedStatement.executeUpdate();

        } catch (SQLException | IOException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            exception.printStackTrace();
            return false;
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return true;
    }

    /**
     * Returns a user with the given id
     *
     * @param id
     *            User identifier
     *
     * @return User entity
     *
     */
    public User findUser(Long id){
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;

        try{
            connection = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.USER_FIND_BY_ID.getPropertyName()));
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            if(rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            preparedStatement.close();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    /**
     * Returns list of all users
     *
     * @return List of user entities
     */
    public List<User> findAll(){
        List<User> list = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;

        try{
            connection = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();

            statement = connection.createStatement();

            rs = statement.executeQuery(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.USER_FIND_ALL.getPropertyName()));

            while (rs.next())
                list.add(mapper.mapRow(rs));

        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    /**
     *  Returns bean in such way
     *
     *  id | first_name | second_name | email | role_id
     *
     * @return List of UsersBean
     */
    public List<UsersBean> findUsersBean(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<UsersBean> beanList = new ArrayList<>();
        try{
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.BEAN_ALL_USERS.getPropertyName()));
            UsersBeanMapper mapper = new UsersBeanMapper();

            while (resultSet.next())
                beanList.add(mapper.mapRow(resultSet));

            connection.close();
            statement.close();
        } catch (SQLException | IOException exception) {
            logger.warn(exception.getMessage());
        }

        return beanList;
    }

    /**
     * Find user by email
     *
     * @param email
     *
     * @return User entity
     */
    public User findUser(String email){
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;

        try{
            connection = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.USER_FIND_BY_EMAIL.getPropertyName()));
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            if(rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            preparedStatement.close();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
        return user;
    }


    /**
     * User update
     *
     * @param user
     *             Entity user to update
     */

    public boolean updateUser(User user){
        Connection con = null;
        try{
            con = DBManager.getInstance().getConnection();
            updateUser(con, user);
        } catch (SQLException | IOException exception) {
            DBManager.getInstance().rollbackAndClose(con);
            exception.printStackTrace();
            return false;
        }finally {
            DBManager.getInstance().commitAndClose(con);
        }

        return true;
    }

    /**
     * Update user
     *
     * @param con
     *           Database connection
     * @param user
     *           User entity
     *
     * @throws SQLException
     */
    public void updateUser(Connection con, User user) throws SQLException, IOException {
        PreparedStatement preparedStatement = con.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.USER_UPDATE.getPropertyName()));
        int k = 1;
        preparedStatement.setString(k++, user.getPass());
        preparedStatement.setString(k++, user.getFirst_name());
        preparedStatement.setString(k++, user.getSecond_name());
        preparedStatement.setString(k++, user.getEmail());
        preparedStatement.setString(k++, user.getAddress());
        preparedStatement.setString(k++, user.getCity());
        preparedStatement.setInt(k++, user.getCountry_id());
        preparedStatement.setInt(k++, user.getRoleId());
        preparedStatement.setInt(k++, user.getBanned());
        preparedStatement.setLong(k, user.getId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }



    /**
     * Extracts a user from the result set row.
     */
    private class UserMapper implements EntityMapper<User> {
        @Override
        public User mapRow(ResultSet rs) {
            User user = null;
            try{
                user = new User();
                user.setId(rs.getLong(Fields.ENTITY_ID));
                user.setFirst_name(rs.getString(Fields.USER_FIRST_NAME));
                user.setSecond_name(rs.getString(Fields.USER_SECOND_NAME));
                user.setAddress(rs.getString(Fields.ADDRESS));
                user.setCity(rs.getString(Fields.CITY));
                user.setCountry_id(rs.getInt(Fields.COUNTRY_ID));
                user.setEmail(rs.getString(Fields.USER_EMAIL));
                user.setPass(rs.getString(Fields.USER_PASS));
                user.setRoleId(rs.getInt(Fields.ROLE));
                user.setBanned(rs.getInt(Fields.USER_BAN));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return user;
        }
    }

    /**
     * Extracts an UserBean from the result set row.
     */
    private class UsersBeanMapper implements EntityMapper<UsersBean> {
        @Override
        public UsersBean mapRow(ResultSet rs) {
            UsersBean usersBean = new UsersBean();
            try{
                usersBean.setId(rs.getLong("id"));
                usersBean.setFirst_name(rs.getString("first_name"));
                usersBean.setSecond_name(rs.getString("second_name"));
                usersBean.setEmail(rs.getString("email"));
                usersBean.setRole_id(rs.getInt("role_id"));
                usersBean.setIsBanned(rs.getInt("banned"));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return usersBean;
        }
    }
}
