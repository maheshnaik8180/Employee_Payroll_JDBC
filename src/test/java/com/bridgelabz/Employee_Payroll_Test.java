package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

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
        long result=employee_Payroll.updateData();
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenDateRangeToEmployeePayRollInDB_WhenRetrieved_DataShouldMatchFilteredEmployeeCount() {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<EmployeePayrollData>employeePayrollDataList=employee_Payroll.employeeDetailsfromDate();
        Assert.assertEquals(2,employeePayrollDataList.size());
    }

    @Test
    public void given_sum_avg_min_max_count() {
        Employee_payroll employee_Payroll = new Employee_payroll();
        List<String> list=employee_Payroll.Database_operation();
        Assert.assertEquals(12,list.size());
    }
}
