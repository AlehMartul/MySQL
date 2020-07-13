package project.tests;

import java.sql.SQLException;

public class MySQLTest {

    private static final String DATE = "2015-11-07 21:00:00";

    public static void main(String args[]) throws SQLException {
        Queries.getMinWorkingTime();
        Queries.getQuantityOfTestsRunsOnDifferentBrowsers();
        Queries.getQuantityOfUniqueTests();
        Queries.getTestsStartsAfterDate(DATE);
    }

}
