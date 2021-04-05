package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;
import java.time.Duration;
import java.time.Instant;
import java.sql.Date;
import java.sql.SQLException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class Employee_Payroll_Test {

    @Test
    public void givenEmployeePayroll_WhenRetrieved_ShouldMatchEmpCount() {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<EmployeePayrollData> employeePayrollDataList= employee_Payroll.readData();
        Assert.assertEquals(3,employeePayrollDataList.size());

    }

    @Test
    public void givenNewSalaryForEmployee_whenUpdate_shouldSyncWithDB() {
        Employee_payroll employee_Payroll = new Employee_payroll();
        double salary=500000;
        int id=3;
        long result=employee_Payroll.updateData(id,salary);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenDateRangeToEmployeePayRollInDB_WhenRetrieved_DataShouldMatchFilteredEmployeeCount() {
        Employee_payroll employee_Payroll = new Employee_payroll();
        String date = "2019-11-13";
        List<EmployeePayrollData>employeePayrollDataList=employee_Payroll.employeeDetailsfromDate(date);
        Assert.assertEquals(2,employeePayrollDataList.size());
    }

    @Test
    public void given_sum_avg_min_max_count() {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<String> list=employee_Payroll.Database_operation();
        Assert.assertEquals(12,list.size());
    }

    @Test
    public void insert_new_employee_in_employee_table()throws SQLException {
        Employee_payroll employee_Payroll = new Employee_payroll();
        String name="Sam";
        String date="2021-07-07";
        double salary=900000;
        String gender="M";
        employee_Payroll.AddDataInTable(name,date,salary,gender);
        List<EmployeePayrollData> employeePayrollDataList=employee_Payroll.readData();
        Assert.assertEquals(3,employeePayrollDataList.size());
    }

    @Test
    public void givenNewEmployee_Add_In_Payroll_details() {
        Employee_payroll employee_Payroll = new Employee_payroll();
        int payroll_id=2;
        double basic_pay=8000;
        double deduction=30000;
        double tax_pay=20000;
        double tax=90000;
        double net_pay=4000;
        int result=employee_Payroll.InsertDataInPayroll_Details(payroll_id,basic_pay,deduction,tax_pay,tax,net_pay);
        Assert.assertEquals(1,result);   
    }

    @Test
    public void add_employee_details_in_employee_table() throws SQLException {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<EmployeePayrollData> employeePayrollDataList= employee_Payroll.readData();
        String name = "Nandini";
        String date = "2021-03-03";
        double salary = 900000;
        String gender = "F";
        employee_Payroll.RecordsinsertInDataBase(name,date,salary,gender);
        Assert.assertEquals(4,employeePayrollDataList.size());

    }

    @Test
    public void Remove_detail_from_employee_table_data() {
        String name="Nandini";
        Employee_payroll employee_Payroll = new Employee_payroll();
        try {
            employee_Payroll.delete_Employee_From_Employee_Payroll_Table(name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<EmployeePayrollData> employeePayrollDataList= employee_Payroll.readData();
        Assert.assertEquals(4,employeePayrollDataList.size());

    }

    @Test
    public void Add_Multiple_employee_In_Table_One_Time() throws SQLException {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<EmployeePayrollData> list=new ArrayList<>();
        list.add(new EmployeePayrollData(4,"sandhya", Date.valueOf("2019-05-19"),600000,"F"));
        list.add(new EmployeePayrollData(5,"Sheetal",Date.valueOf("2019-01-21"),800000,"F"));
        employee_Payroll.UsingArrayListAddMultipleEmployee(list);
        List<EmployeePayrollData> employeePayrollDataList=employee_Payroll.readData();
        Assert.assertEquals(5,employeePayrollDataList.size());
    }

    @Test
    public void Insert_Multiple_Records_Add_In_DataBase_using_threads() throws SQLException {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<EmployeePayrollData> list=new ArrayList<>();
        list.add(new EmployeePayrollData(6,"Vinit", Date.valueOf("2018-03-08"),700000,"M"));
        list.add(new EmployeePayrollData(7,"Manju",Date.valueOf("2017-04-22"),600000,"F"));
        Instant Start=Instant.now();
        employee_Payroll.insertDataUsingThreads(list);
        Instant end=Instant.now();
        System.out.println("Duration of non thread process is: "+ Duration.between(Start,end));
        List<EmployeePayrollData> employeePayrollDataList=employee_Payroll.readData();
        Assert.assertEquals(6,employeePayrollDataList.size());
    }

    @Test
    public void using_synchronization_Insert_The_Record_in_table() throws SQLException {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<EmployeePayrollData> list=new ArrayList<>();
        list.add(new EmployeePayrollData(8,"Harshal", Date.valueOf("2018-09-11"),700000,"M"));
        list.add(new EmployeePayrollData(9,"Neha",Date.valueOf("2020-05-09"),790000,"F"));

        Instant start=Instant.now();
        employee_Payroll.insertDataUsingThreads(list);
        Instant end=Instant.now();
        System.out.println("Duration of non thread process is: "+ Duration.between(start,end));

        List<EmployeePayrollData> employeePayrollDataList=employee_Payroll.readData();
        Assert.assertEquals(9,employeePayrollDataList.size());
    }
}
