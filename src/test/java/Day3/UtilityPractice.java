package Day3;

import utility.DB_Utility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilityPractice {

    public static void main(String[] args) throws SQLException {

        DB_Utility.createConnection();



        ResultSet jobRS =  DB_Utility.runQuery("SELECT * FROM JOBS");

        //row count
        jobRS.last();
        int rowCount = DB_Utility.getRowCount();
        System.out.println("rowCount = " + rowCount);


        int colCount = DB_Utility.getColumnCount();
        System.out.println("colCount = " + colCount);









        //DB_Utility.destroy();
    }




}
