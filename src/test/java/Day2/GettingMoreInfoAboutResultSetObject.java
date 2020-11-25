package Day2;

import java.sql.*;

public class GettingMoreInfoAboutResultSetObject {

    public static void main(String[] args) throws SQLException {


        String connectionStr = "jdbc:oracle:thin:@54.236.21.55:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connectionStr, username, password);

        Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = stmnt.executeQuery("SELECT * FROM EMPLOYEES");


        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        System.out.println("colCount = " + colCount);
        System.out.println("First column name is " + rsmd.getColumnLabel(1));
        System.out.println("Second  column name is " + rsmd.getColumnLabel(2));

        for (int i = 1; i <= colCount; i++) {
            System.out.println("Cocumn "+ i + " is "+ rsmd.getColumnLabel(i));

        }



    }
}
