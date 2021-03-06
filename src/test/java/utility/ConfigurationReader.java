package utility;

import java.io.*;
import java.util.*;

public class ConfigurationReader {

    //#1- We created the properties object
    private static Properties properties = new Properties();

    static {
        try {

            //#2- We get the path and pass it into FileInputStream, to open the file
            FileInputStream file = new FileInputStream("configuration.properties");
            //#3- We load the opened file into properties object
            properties.load(file);
            //#5- close the file
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Properties file not found");

        }

    }


    //#4- We read from file
    public static String getProperty(String keyWord) {
        return properties.getProperty(keyWord);

    }

}
