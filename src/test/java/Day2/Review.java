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
        System.out.println("Second column value in Jobs " +rs.getString(2));

// move to row n 7
        rs.absolute(7);
        System.out.println("First column value in Jobs in row 7 " +rs.getString(1));
        System.out.println("Second column value in Jobs in row 7 " +rs.getString(2));


        // move to last n
        rs.last();
        System.out.println("First column value in Jobs in row last " +rs.getString(1));
        System.out.println("Second column value in Jobs in row last " +rs.getString(2));

// move to previos
        rs.previous();
        System.out.println("First column value in Jobs in row before row last " +rs.getString(1));
        System.out.println("Second column value in Jobs in row before lastrow last " +rs.getString(2));

        System.out.println("-----------------------------------loop from top to bottom - JOB_ID");

        rs.beforeFirst();
        while (rs.next()){
            System.out.println("loop first column  "+ rs.getString("JOB_ID"));
        }

        System.out.println("\n----------------loop from ---- min salary as number");
        rs.afterLast();
        while ((rs.previous())){
            System.out.println("Min salary column as number " + rs.getDouble("min_salary"));
        }







    }
}