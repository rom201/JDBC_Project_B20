package Day2;

import java.sql.*;

public class Review {

    public static void main(String[] args) throws SQLException {

        String connectionStr = "jdbc:oracle:thin:@54.236.21.55:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connectionStr, username, password);

        Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = stmnt.executeQuery("SELECT * FROM JOBS");

        rs.first();// or
        System.out.println("First column value in Jobs " +rs.getString(1));
        System.out.println("First column value in Jobs " +rs.getString(2));








    }
}