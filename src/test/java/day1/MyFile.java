package day1;

import java.sql.*;

public class MyFile {

    public static void main(String[] args) throws SQLException {
        // REPLACE THIS IP ADDRESS WITH YOUR OWN THAT WORKING IN SQL DEVELOPER
        String connectionStr = "jdbc:oracle:thin:@54.236.21.55:1521:XE";
        String username = "hr";
        String password = "hr";
        Connection conn = DriverManager.getConnection(connectionStr, username, password);
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("select first_name, last_name, email,employee_id\n" +
                "from employees\n" +
                "where salary > 10000");
    //rs.next();
        //System.out.println(rs.getString(1));
        while (rs.next()){
            System.out.println(rs.getString(1)+" "+
                    rs.getString(2)+" "+
                    rs.getString(3)+" "+
                    rs.getString(4)
            );
        }


        rs.close();
        stmnt.close();
        conn.close();


    }
}
