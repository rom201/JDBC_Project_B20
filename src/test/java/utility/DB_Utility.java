package utility;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {

    private static Connection conn;
    private static Statement stmnt;
    private static ResultSet rs;


    public static void createConnection() {
        String connectionStr = "jdbc:oracle:thin:@54.236.21.55:1521:XE";
        String username = "hr";
        String password = "hr";

        try {
            conn = DriverManager.getConnection(connectionStr, username, password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException e) {
            System.out.println("Connecntion is failed  !!!" + e.getMessage());
        }
    }

    public static ResultSet runQuery(String query) {
        try {
            stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error while getting resultser " + e.getMessage());
            ;
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

    /**
     * get row count
     *
     * @return
     */
    public static int getRowCount() {
        int rowCount = 0;

        try {
            rs.last();
            rowCount = rs.getRow();
            // move cursor to beforefirst location    we have to fixed later
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT " + e.getMessage());
        }
        return rowCount;
    }


    /**
     * Get the column count
     *
     * @return count of column
     */

    public static int getColumnCount() {

        int columnCount = 0;

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN COUNT " + e.getMessage());
        }
        return columnCount;
    }


    /**
     * return all columns names as list<string>
     */

    public static List<String> getColumnNames() {
        List<String> columnList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {

                String columnName = rsmd.getColumnLabel(colNum);
                columnList.add(columnName);
            }
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ALL COLUMN NAMES " + e.getMessage());
        }
        return columnList;
    }

    /**
     * return row data as List
     *
     * @param rowNum row numbre you want to get data
     * @return the row data as List object
     */
    public static List<String> getRowDataAsList(int rowNum) {

        List<String> rowDataList = new ArrayList<>();

        try {
            rs.absolute(rowNum);
            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
                String cellValue = rs.getString(colNum);
                rowDataList.add(cellValue);
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW DATA " + e.getMessage());
        }
        return rowDataList;
    }


    /**
     * Create a method to return the cell value at certain row certain column
     *
     * @param rowNum
     * @return Cell value as String
     * @parem colNum
     */
    public static String getColumnDataAtRow(int rowNum, int colNum) {

        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(colNum);
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING CELL VALUE AT ROWNUM COLNUM " + e.getMessage());
        }

        return result;
    }

    /**
     * Create a method to return the cell value at certain row certain column
     *
     * @param rowNum
     * @return Cell value as String
     * @parem colName
     */


    public static String getColumnDataAtRow(int rowNum, String colName) {

        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(colName);
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING CELL VALUE AT ROW NAME of COLNUM " + e.getMessage());
        }
        return result;
    }

    /**
     * @param colNum the column  number you want to get List
     * @return value of all cells as list
     */
    public static List<String> getColumnDataAsList(int colNum) {
        List<String> cellValuesList = new ArrayList<>();

        try {
            while (rs.next()) {
                String cellValue = rs.getString(colNum);
                cellValuesList.add( cellValue ) ;
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ONE COLUMN DATA AS LIST " + e.getMessage() );
        }
        return cellValuesList;
    }

    /**
     * return value of all cells in that column using column name
     *
     * @param colName the column name you want to get the list out of
     * @return value of all cells in that column as a List<String>
     */
    public static List<String> getColumnDataAsList(String colName) {

        List<String> cellValuesList = new ArrayList<>();

        try {
            while (rs.next()) {
                String cellValue = rs.getString(colName);
                cellValuesList.add( cellValue ) ;
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ONE COLUMN DATA AS LIST " + e.getMessage() );
        }
        return cellValuesList ;
    }



    /**
     * A method that display all the result set data on console
     */
    public static void displayAllData(){

        try {
            rs.beforeFirst();

            while (rs.next()) {

                for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
                    System.out.print(rs.getString(colNum) + "\t");
                }
                System.out.println();
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE PRINTING WHOLE TABLE " + e.getMessage());
        }
    }


    /**
     * A method that return the row data along with column name as Map object
     * @param rowNum row numebr you want to get the data
     * @return Map object -- column name as key and cell value as value
     */
    public static Map<String,String> getRowMap(int rowNum){

        Map<String,String> rowMap = new LinkedHashMap<>() ;

        try{
            rs.absolute(rowNum) ;
            ResultSetMetaData rsmd = rs.getMetaData() ;

            for (int colNum = 1; colNum <= rsmd.getColumnCount() ; colNum++) {

                String columnName   =  rsmd.getColumnLabel( colNum ) ;
                String cellValue    =  rs.getString( colNum ) ;
                rowMap.put(columnName, cellValue) ;

            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getting RowMap " + e.getMessage());
        }
        return rowMap ;
    }



    public static List<Map<String,String> > getAllDataAsListOfMap(){

        List<Map<String,String> > rowMapList = new ArrayList<>();

        for (int rowNum = 1; rowNum <= getRowCount() ; rowNum++) {

            rowMapList.add(   getRowMap(rowNum)    ) ;

        }
        return  rowMapList ;
    }














}

