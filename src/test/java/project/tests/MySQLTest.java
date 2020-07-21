package project.tests;

import framework.tools.MySQLTools;
import framework.tools.ReadPropertyTool;

import java.sql.SQLException;

public class MySQLTest {

    private static final String USER = new ReadPropertyTool(MySQLTest.RESOURCES_PATH, MySQLTest.MYSQL_PROPERTIES)
            .getProperty("login");
    private static final String PASSWORD = new ReadPropertyTool(MySQLTest.RESOURCES_PATH, MySQLTest.MYSQL_PROPERTIES)
            .getProperty("password");
    private static final String DATE = "2015-11-07 21:00:00";
    private static final String BROWSER1 = "firefox";
    private static final String BROWSER2 = "chrome";
    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String MYSQL_PROPERTIES = "config.properties";
    private static final String URL = new ReadPropertyTool(MySQLTest.RESOURCES_PATH, MySQLTest.MYSQL_PROPERTIES)
            .getProperty("mainUrl");

    public static void main(String args[]) throws SQLException {
        MySQLTools.connectToDataBase(URL, USER, PASSWORD);
        Queries.getMinWorkingTime();
        Queries.getQuantityOfTestsRunsOnDifferentBrowsers(BROWSER1, BROWSER2);
        Queries.getQuantityOfUniqueTests();
        Queries.getTestsStartsAfterDate(DATE);
        MySQLTools.closeConnection();
    }

}
