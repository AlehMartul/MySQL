package framework.tools;

import aquality.selenium.browser.AqualityServices;

import java.sql.*;

public class MySQLTools {

    private static Connection con;

    public static void connectToDataBase(String url, String user, String password) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printQuery(String query) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException se) {
            {
                AqualityServices.getLogger().error(se.getMessage());
            }
        }

    }
}
