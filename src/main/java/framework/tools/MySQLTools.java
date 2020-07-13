package framework.tools;

import java.sql.*;

public class MySQLTools {

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static ResultSet makeQuery(String query, String url, String user, String password) {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return rs;
    }

    public static void printQuery(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(resultSet.getString(i) + " ");
            }
            System.out.println();
        }
    }

    public static void closeAll() {
        try {
            con.close();
        } catch (SQLException se) {
        }
        try {
            stmt.close();
        } catch (SQLException se) {
        }
        try {
            rs.close();
        } catch (SQLException se) {
        }
    }
}
