package Day4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import utility.DB_Utility;

public class DB_Test {


    @BeforeAll
    public static void init(){
        DB_Utility.createConnection();
    }

    @Test
    public void testEmployeeCount(){

        DB_Utility.runQuery("select employee_id from employees");

        int expected = 107;
        int actual = DB_Utility.getRowCount();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void test3rdRowSecondColumn(){
        DB_Utility.runQuery("select * from regions");
        Assertions.assertEquals("Asia", DB_Utility.getColumnDataAtRow(3,2));

    }









    @AfterAll
    public static void tearDown(){
        DB_Utility.destroy();
    }





}
