package org.testApp.ConnectUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AutoIncrementCompressor {

    public static void compressionTable(String tableName, long countRows){
        String query = "ALTER TABLE " + tableName + " auto_increment = " + countRows;
        try(Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
