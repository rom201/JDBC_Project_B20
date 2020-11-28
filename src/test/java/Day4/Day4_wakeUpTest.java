package Day4;

import utility.DB_Utility;

public class Day4_wakeUpTest {

    public static void main(String[] args) {

        DB_Utility.createConnection();

        String query = "select e.first_name, d.department_name, e.salary \n" +
                "from employees e\n" +
                "inner JOIN departments d on e.department_id = d.department_id\n" +
                "where e.salary in (select max(e.salary)\n" +
                "                    from employees e\n" +
                "                    inner JOIN departments d on e.department_id = d.department_id\n" +
                "                    group by d.department_name)\n" +
                "ORDER BY 1 ";

        DB_Utility.runQuery(query);
        DB_Utility.displayAllData();

        System.out.println("DB_Utility.getRowCount() = " + DB_Utility.getRowCount());

        System.out.println("DB_Utility.getColumnCount() = " + DB_Utility.getColumnCount());


        System.out.println("DB_Utility.getColumnDataAsList(\"Firs_name\") = \n\t"
                + DB_Utility.getColumnDataAsList("First_name"));


        System.out.println("DB_Utility.getColumnDataAtRow(2,\"department_name\") = "
                + DB_Utility.getColumnDataAtRow(2, "department_name"));


        System.out.println("DB_Utility.getRowDataAsList(3) = \n\t"
                + DB_Utility.getRowDataAsList(3));

        System.out.println("DB_Utility.getAllDataAsListOfMap() = \n\t"
                + DB_Utility.getAllDataAsListOfMap());

        System.out.println("DB_Utility.getColumnNames() = " + DB_Utility.getColumnNames());

        DB_Utility.runQuery("select * from countries");
        DB_Utility.displayAllData();







        DB_Utility.destroy();




    }


}
