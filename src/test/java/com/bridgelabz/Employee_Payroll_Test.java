package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
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
        Assert.assertEquals(5,employeePayrollDataList.size());
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
        String name = "Nandini";
        String date = "2021-03-03";
        double salary = 900000;
        String gender = "F";
        int payroll_id = 5;

    }

    @Test
    public void Remove_detail_from_employee_table_data() {
        String name="Nandini";
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<EmployeePayrollData> employeePayrollDataList= employee_Payroll.readData();
        Assert.assertEquals(5,employeePayrollDataList.size());

    }

    @Test
    public void insert_multiple_values_into_a_table_at_a_single_time() throws SQLException {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<EmployeePayrollData> list=new ArrayList<>();
        list.add(new EmployeePayrollData(0,"sandhya", Date.valueOf("2019-05-19"),600000,"F"));
        list.add(new EmployeePayrollData(0,"Sheetal",Date.valueOf("2019-01-21"),800000,"F"));
        employee_Payroll.UsingArrayListAddMultipleEmployee(list);
        List<EmployeePayrollData> employeePayrollDataList=employee_Payroll.readData();
        Assert.assertEquals(5,employeePayrollDataList.size());
    }
}
