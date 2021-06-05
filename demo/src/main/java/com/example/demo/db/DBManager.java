package com.example.demo.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Logger;

public class DBManager {
    private static final Logger log = Logger.getLogger(DBManager.class.getName());

    private final String user = "root";
    private final String pass = "1234";

    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in your
     * WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return A DB connection.
     */
    public Connection getConnection() throws SQLException {
        Connection con = null;
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            DataSource ds = (DataSource) envContext.lookup("jdbc/shop");
            con = ds.getConnection();
        }catch (NamingException e) {
            log.severe("Cannot obtain a connection from pool");
            log.severe(e.getMessage());
        }
        return con;
    }


    /**
     * Commits and close the given connection.
     *
     * @param con
     *            Connection to be committed and closed.
     */
    public void commitAndClose(Connection con){
        try{
            con.commit();
            con.close();
        } catch (SQLException exception) {
            log.severe(exception.getMessage());
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con
     *            Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con){
        try{
            con.rollback();
            con.close();
        } catch (SQLException exception) {
            log.severe(exception.getMessage());
        }
    }
}
