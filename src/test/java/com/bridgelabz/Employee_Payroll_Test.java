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
        Assert.assertEquals(3,employeePayrollDataList.size());
    }
}
