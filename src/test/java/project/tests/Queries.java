package project.tests;

import framework.tools.MySQLTools;
import framework.tools.ReadPropertyTool;

import java.sql.SQLException;

public class Queries {

    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String MYSQL_PROPERTIES = "config.properties";
    private static final String URL = new ReadPropertyTool(Queries.RESOURCES_PATH, Queries.MYSQL_PROPERTIES)
            .getProperty("mainUrl");
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String QUERY1 = "SELECT p.name, t.name, MIN(end_time - start_time) AS min_votes FROM" +
            " union_reporting.test AS t JOIN union_reporting.project AS p ON t.project_id = p.id GROUP BY p.name," +
            " t.name order by p.name, t.name";
    private static final String QUERY2 = "SELECT p.name, COUNT(t.name) as 'Уникальные тесты' FROM union_reporting.test" +
            " AS t JOIN union_reporting.project AS p ON t.project_id = p.id GROUP BY (project_id)";
    private static final String QUERY3 = "SELECT p.name, t.name, start_time FROM union_reporting.test AS t JOIN" +
            " union_reporting.project AS p ON t.project_id = p.id WHERE start_time > '%s' order by p.name, t.name";
    private static final String QUERY4 = "(SELECT browser, COUNT('BROWSERS') FROM union_reporting.test WHERE browser =" +
            " 'firefox') UNION (SELECT browser, COUNT('BROWSERS') FROM union_reporting.test WHERE browser = 'chrome')";

    public static void getMinWorkingTime() throws SQLException {
        MySQLTools.printQuery(MySQLTools.makeQuery(QUERY1, URL, USER, PASSWORD));
        MySQLTools.closeAll();
    }

    public static void getQuantityOfUniqueTests() throws SQLException {
        MySQLTools.printQuery(MySQLTools.makeQuery(QUERY2, URL, USER, PASSWORD));
        MySQLTools.closeAll();
    }

    public static void getTestsStartsAfterDate(String date) throws SQLException {
        String query = String.format(QUERY3, date);
        MySQLTools.printQuery(MySQLTools.makeQuery(query, URL, USER, PASSWORD));
        MySQLTools.closeAll();
    }

    public static void getQuantityOfTestsRunsOnDifferentBrowsers() throws SQLException {
        MySQLTools.printQuery(MySQLTools.makeQuery(QUERY4, URL, USER, PASSWORD));
        MySQLTools.closeAll();
    }
}
