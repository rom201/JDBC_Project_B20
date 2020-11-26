package day1;

import java.sql.*;

public class MovingResultSetPointer {


    // we want to create a statement object that generate
    // ResultSet that can move forward and backward anytime
    public static void main(String[] args) throws SQLException {
        // REPLACE THIS IP ADDRESS WITH YOUR OWN THAT WORKING IN SQL DEVELOPER
        String connectionStr = "jdbc:oracle:thin:@54.236.21.55:1521:XE";
        String username = "hr";
        String password = "hr";
        Connection conn = DriverManager.getConnection(connectionStr, username, password);

        Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS");

        while (rs.next()){
            System.out.println("Region_NAME "+rs.getString("REGION_NAME"));
        }

        //rs.previous();
        //System.out.println("Region_NAME "+rs.getString("REGION_NAME"));

        while (rs.previous()){
            System.out.println("Region_NAME "+rs.getString("REGION_NAME"));
        }


        rs.beforeFirst();// before
        rs.first();// first row
        rs.last();// last
        rs.afterLast();// after last
        rs.absolute(2);// move to cpesific

        rs.last();
        //rs.first();
        int currentRowNum = rs.getRow();
        System.out.println("Row Count ="+currentRowNum);


        rs.close();
        stmnt.close();
        conn.close();

    }


}
