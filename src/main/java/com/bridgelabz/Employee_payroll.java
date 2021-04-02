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
            List<EmployeePayrollData> employeePayrollDataArrayList=new ArrayList<>();
            try {
                Connection connection=this.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement("Select * from employee_payroll; ");
                ResultSet resultSet=preparedStatement.executeQuery();

                while (resultSet.next()){

                    EmployeePayrollData employeePayrollData=new EmployeePayrollData(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDate(3),
                            resultSet.getDouble(4),
                            resultSet.getString(5));
                    employeePayrollDataArrayList.add(employeePayrollData);
                }
                System.out.println(employeePayrollDataArrayList.toString());
                connection.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return employeePayrollDataArrayList;
        }

    public long updateData(int id, double salary){
        try {
            Connection connection=this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("Update employee_payroll set salary=? where id=? ; ");
            preparedStatement.setDouble(1,salary);
            preparedStatement.setInt(2,id);
            long resultSet=preparedStatement.executeUpdate();
            System.out.println(resultSet);
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        return 0;
        }


    public List<EmployeePayrollData> employeeDetailsfromDate(String date){
        List<EmployeePayrollData> employeePayrollDataArrayList=new ArrayList<>();
        try {
            Connection connection=this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("Select * from employee_payroll where start>=? ");
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){

                EmployeePayrollData employeePayrollData=new EmployeePayrollData(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5));
                employeePayrollDataArrayList.add(employeePayrollData);

            }
            System.out.println(employeePayrollDataArrayList.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employeePayrollDataArrayList;
    }

    public List<String> Database_operation(){
        List<String> list=new ArrayList();
        try {
            Connection connection=this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select gender,sum(salary), Avg(salary),min(salary),max(salary),count(salary) from employee_payroll group by gender; ");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int num=1;
                System.out.println("Gender: "+resultSet.getString(1));
                System.out.println("Salary: "+resultSet.getString(2));
                for (int i=0;i<6;i++){
                    if(num<8) {
                        list.add(i, resultSet.getString(num));
                        num++;
                    }
                }
                System.out.println(list);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return list;
    }
    public void AddDataInTable(String name,String date,double salary,String gender){
        try{
            Connection connection=this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("insert into employee_payroll(name,start,salary,gender) values(?,?,?,?); ");

            preparedStatement.setString(1,name);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setDouble(3,salary);
            preparedStatement.setString(4,gender);
            int resultSet=preparedStatement.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

}
