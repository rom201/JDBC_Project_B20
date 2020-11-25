package Day2;

import java.sql.*;

public class DisplayingAllDataFromAllColumn {
    public static void main(String[] args) throws SQLException {


        String connectionStr = "jdbc:oracle:thin:@54.236.21.55:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connectionStr, username, password);

        Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = stmnt.executeQuery("SELECT * FROM EMPLOYEES");

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();



            for (int i = 1; i <= colCount; i++) {
                System.out.print(rsmd.getColumnLabel(i) + "\t");
            }
        System.out.println();
            System.out.println("---------------");

            rs.next();
        for (int i = 1; i <= colCount; i++) {
            System.out.print(rs.getString(i) + "\t");
        }

        rs.beforeFirst();
        while (rs.next()){

            for (int i = 1; i <= colCount; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }


        rs.close();
        stmnt.close();
        conn.close();









    }
}