package com.shafique.springboot.dao;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.Employee;

import java.util.List;

public interface CCSMEmployeeDao {
    public Employee addEmployee(Employee empl);
    public List<Employee> deleteEmplyee(long empId, long managerId);
    public List<Employee> loadEmployees(long empId);
    public List<Complaint> loadComplaints();
    public Complaint findComplaintById(long complaintId);
    public Employee findEmployeeById(long empId);
    public Complaint addComplaints(Complaint complnt);
}
