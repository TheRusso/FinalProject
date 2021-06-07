package com.example.demo.db.dao;

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
    public void insertUser(User user, Connection connection) throws SQLException, IOException {

        try(PreparedStatement preparedStatement =
                connection.prepareStatement(DBHandlerUtil.getInstance()
                        .getSQL(SQLProperyNamesHandler.USER_INSERT.getPropertyName()))){
            int k = 1;
            preparedStatement.setString(k++, user.getFirst_name());
            preparedStatement.setString(k++, user.getSecond_name());
            preparedStatement.setString(k++, user.getAddress());
            preparedStatement.setString(k++, user.getCity());
            preparedStatement.setInt(k++, user.getCountry_id());
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k, user.getPass());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Deletes user with given email
     *
     * @param email
     * @param connection
     *          DB connection
     * @return is Deleted
     */
    public boolean deleteUserWithEmail(String email, Connection connection){
        try (PreparedStatement preparedStatement =
                      connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.USER_DELETE.getPropertyName()))){

            preparedStatement.setString(1, email);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException exception) {
            logger.error(exception);

            return false;
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
    public User findUser(Long id, Connection connection){
        User user = null;
        ResultSet rs = null;

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            DBHandlerUtil.getInstance().getSQL(
                                    SQLProperyNamesHandler.USER_FIND_BY_ID.getPropertyName()))){
            UserMapper mapper = new UserMapper();

            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            if(rs.next())
                user = mapper.mapRow(rs);
            rs.close();
        } catch (SQLException | IOException exception) {
            logger.error(exception);
        }

        return user;
    }

    /**
     * Returns list of all users
     *
     * @return List of user entities
     */
    public List<User> findAll(Connection connection){
        List<User> list = new ArrayList<>();
        ResultSet rs = null;

        try{
            Statement statement = connection.createStatement();
            UserMapper mapper = new UserMapper();

            rs = statement.executeQuery(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.USER_FIND_ALL.getPropertyName()));

            while (rs.next())
                list.add(mapper.mapRow(rs));

        } catch (SQLException | IOException exception) {
            logger.error(exception);
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
    public List<UsersBean> findUsersBean(Connection connection){
        ResultSet resultSet = null;
        List<UsersBean> beanList = new ArrayList<>();
        try(Statement statement = connection.createStatement();){

            resultSet = statement.executeQuery(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.BEAN_ALL_USERS.getPropertyName()));
            UsersBeanMapper mapper = new UsersBeanMapper();

            while (resultSet.next())
                beanList.add(mapper.mapRow(resultSet));

            connection.close();
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
    public User findUser(String email, Connection connection){
        User user = null;
        ResultSet rs = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.USER_FIND_BY_EMAIL.getPropertyName()))){
            UserMapper mapper = new UserMapper();
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
     * Update user
     *
     * @param user
     *           User entity
     *
     * @param connection
     *           Database connection
     * @throws SQLException
     * @return
     */
    public void updateUser(User user, Connection connection) throws SQLException, IOException {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            DBHandlerUtil.getInstance().getSQL(
                                    SQLProperyNamesHandler.USER_UPDATE.getPropertyName()))){
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
        }
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
