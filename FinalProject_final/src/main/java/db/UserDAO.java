package db;

import db.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String SQL__FIND_USER_BY_EMAIL =
            "SELECT * FROM users WHERE email = ?";

    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id = ?";

    private static final String SQL__UPDATE_USER =
            "UPDATE users SET pass = ?, first_name = ?, second_name = ?, email = ?, " +
                    "address = ?, city = ?, country_id = ?," +
                    "role = ?  WHERE id = ?";

    private static final String SQL__CHECK_USER =
            "SELECT id FROM users WHERE email = ? AND pass = ?";

    private static final String SQL__INSERT_USER =
            "INSERT INTO users(first_name, second_name, address, city, country_id, email, pass)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatement = connection.prepareStatement(SQL__INSERT_USER);

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

        } catch (SQLException exception) {
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
            preparedStatement = connection.prepareStatement(SQL__FIND_USER_BY_ID);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            if(rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
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
            preparedStatement = connection.prepareStatement(SQL__FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            if(rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            preparedStatement.close();
        } catch (SQLException exception) {
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

    public void updateUser(User user){
        Connection con = null;
        try{
            con = DBManager.getInstance().getConnection();
            updateUser(con, user);
        } catch (SQLException exception) {
            DBManager.getInstance().rollbackAndClose(con);
            exception.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(con);
        }
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
    public void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(SQL__UPDATE_USER);
        int k = 1;
        preparedStatement.setString(k++, user.getPass());
        preparedStatement.setString(k++, user.getFirst_name());
        preparedStatement.setString(k++, user.getSecond_name());
        preparedStatement.setString(k++, user.getEmail());
        preparedStatement.setString(k++, user.getAddress());
        preparedStatement.setString(k++, user.getCity());
        preparedStatement.setInt(k++, user.getCountry_id());
        preparedStatement.setInt(k++, user.getRoleId());
        preparedStatement.setLong(k, user.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }



    /**
     * Extracts a user from the result set row.
     */
    private class UserMapper implements EntityMapper<User>{
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
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return user;
        }
    }
}
