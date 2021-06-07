package com.example.demo.db.dao;

import com.example.demo.utils.DBHandlerUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMock {
    private static final String URL_DB = "jdbc:h2:tcp://localhost/~/teams";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private static final String CREATE_PATH = "sql/create_database.sql";
    private static final String INSERT_PATH = "sql/insert_data.sql";
    private Connection connection;
    private static DBMock dbMock;

    private DBMock() {
    }

    public static DBMock getInstance() throws SQLException {
        if (dbMock == null)
            dbMock = new DBMock();

        return dbMock;
    }

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed())
            connection = DriverManager.getConnection(URL_DB, USER, PASS);

        return connection;
    }

    public static void createMockDataBase(){
        executeQueryFromFile(CREATE_PATH);
        executeQueryFromFile(INSERT_PATH);

        try {
            Connection connection = DBMock.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.ITEM_TRIM_TEST_TABLE.getPropertyName()));
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void executeQueryFromFile(String path){
        Connection connection = null;
        try(BufferedReader create = new BufferedReader(new FileReader(path))) {
            connection = DBMock.getInstance().getConnection();

            StringBuilder sql = new StringBuilder();

            while (create.ready()){
                sql.append(create.readLine()).append("\n");
            }

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql.toString());
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void dropMockDataBase(){
        Connection connection = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/drop_database.sql"))) {
            connection = DBMock.getInstance().getConnection();

            StringBuilder sql = new StringBuilder();

            while (bufferedReader.ready()){
                sql.append(bufferedReader.readLine()).append("\n");
            }

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql.toString());

        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
