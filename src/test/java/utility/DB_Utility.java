package utility;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Utility {

    private static Connection conn;
    private static Statement stmnt;
    private static ResultSet rs ;




    public static void createConnection(){
        String connectionStr = "jdbc:oracle:thin:@54.236.21.55:1521:XE";
        String username = "hr";
        String password = "hr";

        try {
            conn = DriverManager.getConnection(connectionStr, username, password);
            System.out.println("CONNECTION SUCCESSFUL");
        }catch (SQLException e){
            System.out.println("Connecntion is failed  !!!" + e.getMessage());
        }
}

    public static ResultSet runQuery(String query){
        try {
            stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error while getting resultser " + e.getMessage());;
        }
        return rs;
    }


    public static void destroy() {
        try {
            rs.close();
            stmnt.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** get row count
     *
     * @return
     */
    public static int getRowCount(){
        int rowCount = 0;

        try {
            rs.last();
            rowCount = rs.getRow();
            // move cursor to beforefirst location    we have to fixed later
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT "+e.getMessage());
        }
        return rowCount;
    }


    /**
     * Get the column count
     * @return count of column
     *
     */

    public  static int getColumnCount(){

        int columnCount = 0;

        try {
            ResultSetMetaData rsmd = rs.getMetaData() ;
            columnCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN COUNT "+e.getMessage());
        }
        return columnCount;
    }













    public static void main(String[] args) throws SQLException {
        createConnection();
        ResultSet rs =runQuery("SELECT * FROM REGIONS");

        rs.next();
        System.out.println("rs.getString(2) = " + rs.getString(2));
    }


}
