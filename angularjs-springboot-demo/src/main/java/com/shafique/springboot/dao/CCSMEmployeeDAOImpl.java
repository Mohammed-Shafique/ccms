package com.shafique.springboot.dao;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.Employee;
import com.shafique.springboot.repository.CCSMComplaintsRepository;
import com.shafique.springboot.repository.CCSMEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CCSMEmployeeDAOImpl implements CCSMEmployeeDao {

    @Autowired
    private CCSMEmployeeRepository ccsmEmployeeRepository;

    @Autowired
    private CCSMComplaintsRepository ccsmComplaintsRepository;

    @Override
    public Employee addEmployee(Employee empl) {
        return ccsmEmployeeRepository.save(empl);
    }

    @Override
    public List<Employee> deleteEmplyee(long empId, long managerId) {
       ccsmEmployeeRepository.deleteById(empId);
       return ccsmEmployeeRepository.getAllEmployees(managerId);
    }

    @Override
    public List<Employee> loadEmployees(long empId) {
        return ccsmEmployeeRepository.getAllEmployees(empId);
    }

    @Override
    public List<Complaint> loadComplaints() {
        return ccsmComplaintsRepository.getAllComplaints();
    }

    @Override
    public Complaint findComplaintById(long complaintId) {
        return ccsmComplaintsRepository.findById(complaintId).get();
    }

    @Override
    public Employee findEmployeeById(long empId) {
        return ccsmEmployeeRepository.findById(empId).get();
    }

    @Override
    public Complaint addComplaints(Complaint complnt) {
        return ccsmComplaintsRepository.save(complnt);
    }
}
