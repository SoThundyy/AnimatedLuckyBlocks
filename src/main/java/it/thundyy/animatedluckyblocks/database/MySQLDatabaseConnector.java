package it.thundyy.animatedluckyblocks.database;

import lombok.Getter;

import java.sql.Connection;

@Getter
public class MySQLDatabaseConnector {
    
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;
    private Connection connection;
    
    public MySQLDatabaseConnector(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    
    public void connect() {
        // Load the JDBC driver
    }
    
    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
}
