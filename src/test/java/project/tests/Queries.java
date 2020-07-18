package project.tests;

import framework.tools.MySQLTools;

import java.sql.SQLException;

public class Queries {

    private static final String QUERY1 = "SELECT p.name, t.name, MIN(end_time - start_time) AS min_votes FROM" +
            " union_reporting.test AS t JOIN union_reporting.project AS p ON t.project_id = p.id GROUP BY p.name," +
            " t.name order by p.name, t.name";
    private static final String QUERY2 = "SELECT p.name, COUNT(DISTINCT t.name) as 'Уникальные тесты'" +
            " FROM union_reporting.test AS t JOIN union_reporting.project AS p ON t.project_id = p.id GROUP BY (project_id)";
    private static final String QUERY3 = "SELECT p.name, t.name, start_time FROM union_reporting.test AS t JOIN" +
            " union_reporting.project AS p ON t.project_id = p.id WHERE start_time >= '%s' order by p.name, t.name";
    private static final String QUERY4 = "(SELECT browser, COUNT('BROWSERS') FROM union_reporting.test WHERE browser = '%s')";
    private static final String UNION = "UNION";

    public static void getMinWorkingTime() throws SQLException {
        MySQLTools.printQuery(QUERY1);
    }

    public static void getQuantityOfUniqueTests() throws SQLException {
        MySQLTools.printQuery(QUERY2);
    }

    public static void getTestsStartsAfterDate(String date) throws SQLException {
        String query = String.format(QUERY3, date);
        MySQLTools.printQuery(query);
    }

    public static void getQuantityOfTestsRunsOnDifferentBrowsers(String browser1, String browser2) throws SQLException {
        String query = String.format(QUERY4, browser1) + UNION + String.format(QUERY4, browser2);
        MySQLTools.printQuery(query);
    }
}
