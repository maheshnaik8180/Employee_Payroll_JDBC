package com.bridgelabz;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class ConnectionDB {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/employee_payroll_jdbc?useSSL=false";
        String userName = "root";
        String passWord = "admin";
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver");

        }
        listDrivers();
        try {
            System.out.println("Connecting to database:" + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, passWord);

            System.out.println("Connection is Successful !!!! :" + connection);


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while(driverList.hasMoreElements())
        {
            Driver driverClass = driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }
}