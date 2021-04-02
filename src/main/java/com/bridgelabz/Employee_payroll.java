package com.bridgelabz;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Employee_payroll {


        private Connection getConnection() throws SQLException {
            String jdbcurl="jdbc:mysql://localhost:3306/employee_payroll_jdbc?useSSL=false";
            String username="root";
            String  password="admin";
            Connection connection;
            System.out.println("Connecting to database: "+jdbcurl);
            connection= DriverManager.getConnection(jdbcurl,username,password);
            System.out.println("Connection successful: "+connection);
            return connection;
        }

        public List<EmployeePayrollData> readData() {
            String sql_query="Select * from employee_payroll; ";
            List<EmployeePayrollData> employeePayrollDataArrayList=new ArrayList<>();
            try {
                Connection connection=this.getConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(sql_query);

                while (resultSet.next()){
                    int id=resultSet.getInt(1);
                    String name=resultSet.getString(2);
                    Date date=resultSet.getDate(3);
                    double salary=resultSet.getDouble(4);
                    String gender=resultSet.getString(5);
                    System.out.println("+++++++++++++++++++++++++++");
                    System.out.println("Id: "+id);
                    System.out.println("FirstName: "+name);
                    System.out.println("StartDate: "+date);
                    System.out.println("Salary: "+salary);
                    System.out.println("Gender: "+gender);

                    EmployeePayrollData employeePayrollData=new EmployeePayrollData(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDate(3),
                            resultSet.getDouble(4),
                            resultSet.getString(5));
                    employeePayrollDataArrayList.add(employeePayrollData);
                }
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return employeePayrollDataArrayList;
        }

    public void updateData(){
        String sql_query="Update employee_payroll set salary='30000000' where id='2'; ";
        try {
            Connection connection=this.getConnection();
            Statement statement=connection.createStatement();
            long resultSet=statement.executeLargeUpdate(sql_query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        }
}