package com.shafique.springboot.service;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.Employee;

import java.util.List;

public interface CCSMEmployeeService {
    public Employee addEmployee(Employee empl);
    public List<Employee> deleteEmplyee(long empId, long managerId);
    public List<Employee> getEmployees(long employeeId);
    public List<Complaint> loadAllComplaints();
    public List<Complaint> assignToEmployee(List<Complaint> complains, long empId);

}
