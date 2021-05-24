package db;

import db.entities.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsDAO {
    private static final String SQL__GET_ALL_MESSAGES =
            "SELECT * FROM contact_messages";
    private static final String SQL__GET_RANGE_MESSAGES =
            "SELECT * FROM contact_messages WHERE id IN(?, ?)";
    private static final String SQL__INSERT_MESSAGE =
            "INSERT INTO contact_messages(name, email, message, phone_number) VALUES(?, ?, ?, ?)";


    public boolean insertMessage(Message message){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL__INSERT_MESSAGE);

            int k = 1;
            preparedStatement.setString(k++, message.getName());
            preparedStatement.setString(k++, message.getEmail());
            preparedStatement.setString(k++, message.getMessage());
            preparedStatement.setString(k, message.getPhone_number());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException exception) {
            DBManager.getInstance().rollbackAndClose(connection);
            exception.printStackTrace();

            return false;
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return true;
    }

    public List<Message> getAllMessages(){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();

        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL__GET_ALL_MESSAGES);
            MessageMapper mapper = new MessageMapper();
            while (rs.next())
                messages.add(mapper.mapRow(rs));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return messages;
    }

    public List<Message> getRangeMessages(int from, int to){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();

        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL__GET_RANGE_MESSAGES);
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, to);
            rs = preparedStatement.executeQuery();
            MessageMapper mapper = new MessageMapper();
            while (rs.next())
                messages.add(mapper.mapRow(rs));

            connection.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return messages;
    }

    private class MessageMapper implements EntityMapper<Message>{
        @Override
        public Message mapRow(ResultSet rs) {
            Message message = null;
            try {
                message = new Message();
                message.setId(rs.getLong(Fields.ENTITY_ID));
                message.setName(rs.getString(Fields.CONTACTS_NAME));
                message.setEmail(rs.getString(Fields.CONTACTS_EMAIL));
                message.setMessage(rs.getString(Fields.CONTACTS_MESSAGE));
                message.setPhone_number(rs.getString(Fields.CONTACTS_PHONE_NUMBER));
            } catch (SQLException exception) {
                throw new IllegalArgumentException();
            }
            return message;
        }
    }
}
