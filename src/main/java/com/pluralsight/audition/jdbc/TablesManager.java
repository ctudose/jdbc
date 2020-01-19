package com.pluralsight.audition.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablesManager {
	
    public static void createTable() {
    	String sql = "CREATE TABLE COUNTRY( ID IDENTITY, CODE_NAME VARCHAR(255), NAME VARCHAR(255) );";
    	executeStatement(sql);
    }

    public static void dropTable() {
    	String sql = "DROP TABLE IF EXISTS COUNTRY;";
    	executeStatement(sql);
    }
    
    private static void executeStatement(String sql) {
    	try (Connection connection = ConnectionManager.openConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
             statement.executeUpdate();
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }
	}

}
