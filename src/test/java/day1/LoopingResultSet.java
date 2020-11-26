package day1;

import java.sql.*;

public class LoopingResultSet {

    public static void main(String[] args) throws SQLException {
        // REPLACE THIS IP ADDRESS WITH YOUR OWN THAT WORKING IN SQL DEVELOPER
        String connectionStr = "jdbc:oracle:thin:@54.236.21.55:1521:XE";
        String username = "hr";
        String password = "hr";
        Connection conn = DriverManager.getConnection(connectionStr, username, password);
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS");


        // this method return boolean value
//        while (rs.next()){
  //          System.out.println("Region_ID : " + rs.getString("REGION_ID"));
    //        System.out.println("Region_Name : " + rs.getString("REGION_NAME"));
      //  }

        //location get all country
        rs = stmnt.executeQuery("Select * from countries");
         //rs.next();
         //System.out.println(rs.getString(1));
        while (rs.next()){
            System.out.println(rs.getString(1)+" "+
                    rs.getString(2)+" "+
                    rs.getString(3)
            );
        }

        rs.close();
        stmnt.close();
        conn.close();









    }

}
