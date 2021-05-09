package CapyTecSolutions;

import java.sql.*;

public class DBConnection {

    private static Connection dbConn = null;

    public DBConnection() {
        Connect();
    }

    public static void Connect() {
        if(dbConn == null) {
            try {
                String url = "jdbc:sqlite:src\\CapyTecSolutions\\CapyTecSolutions.db";
                dbConn = DriverManager.getConnection(url);
                System.out.println("Connection to database has been established.");
            } catch (SQLException e) {
                System.out.println("Failed to connect to database");
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean runSQL(String sql) {
        if (dbConn == null) {
            System.out.println("There is no database loaded. SQL cannot be run");
            return false;
        }
        try {
            Statement sqlStatement = dbConn.createStatement();
            sqlStatement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Failed to execute " + sql);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ResultSet runSQLQuery(String sql) {
        ResultSet result;
        if (dbConn == null) {
            System.out.println("There is no database loaded. Cannot run SQL");
            return null;
        }
        try {
            Statement sqlStatement = dbConn.createStatement();
            result = sqlStatement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Failed to execute " + sql);
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
}
