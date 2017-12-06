package com.amazon.redshiftcopier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author hoodrya
 */
public class RedshiftConnection {
    
    private final String URL = System.getenv("endpoint"); 
    private final String PORT = System.getenv("port"); 
    private final String DB = System.getenv("dbname"); 
    private final String USERNAME = System.getenv("user");
    private final String PASSWORD = System.getenv("password");
    
    private Connection connection;
    
    public RedshiftConnection() throws ClassNotFoundException, SQLException
    {
        System.err.println("trinetx: Building connection to RedShift");
        Class.forName("com.amazon.redshift.jdbc41.Driver");
        System.err.println("trinetx: Instantiating properties");
        Properties props = new Properties();

        System.err.println("trinetx: Setting username");
        props.setProperty("user", USERNAME);
        System.err.println("trinetx: Setting password");
        props.setProperty("password", PASSWORD);
        
        String connectionString = "jdbc:redshift://" + URL + ":" + PORT + "/" + DB;
        System.err.println("trinetx: Connection string: " + connectionString);
        connection = DriverManager.getConnection(connectionString, props);
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        return connection;
    }
}
