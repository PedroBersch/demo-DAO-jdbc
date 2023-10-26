package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

    private static Connection conn = null;
    /*Establishes a connection to the database. If a connection doesn't exist,
    it creates a new one based on properties loaded from a db.properties file.*/
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
//Closes the database connection, if it is currently open.
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    //Loads database connection properties from a file named "db.properties."
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            // Throw a custom exception if there's an error reading the properties file
            throw new DbException(e.getMessage());
        }
    }
    // Closes a database Statement if it is currently open.
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                // Throw a custom exception if there's an error closing the Statement
                throw new DbException(e.getMessage());
            }
        }
    }
    //Closes a database ResultSet if it is currently open.
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // Throw a custom exception if there's an error closing the ResultSet
                throw new DbException(e.getMessage());
            }
        }
    }
}