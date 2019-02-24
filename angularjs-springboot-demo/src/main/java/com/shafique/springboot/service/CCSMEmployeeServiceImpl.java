package com.shafique.springboot.service;

import com.shafique.springboot.dao.CCSMEmployeeDao;
import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CCSMEmployeeServiceImpl implements CCSMEmployeeService {

    @Autowired
    private CCSMEmployeeDao ccsmEmployeeDaoImpl;

    @Override
    public Employee addEmployee(Employee empl) {
        empl.setCreateDate(new Date());
        empl.setLastUpdatedDate(new Date());
        return ccsmEmployeeDaoImpl.addEmployee(empl);
    }

    @Override
    public List<Employee> deleteEmplyee(long empId, long managerId) {
        return ccsmEmployeeDaoImpl.deleteEmplyee(empId, managerId);
    }

    @Override
    public List<Employee> getEmployees(long empId) {
        return ccsmEmployeeDaoImpl.loadEmployees(empId);
    }

    public CCSMEmployeeDao getCcsmEmployeeDaoImpl() {
        return ccsmEmployeeDaoImpl;
    }

    public void setCcsmEmployeeDaoImpl(CCSMEmployeeDao ccsmEmployeeDaoImpl) {
        this.ccsmEmployeeDaoImpl = ccsmEmployeeDaoImpl;
    }

    @Override
    public List<Complaint> loadAllComplaints() {
        return ccsmEmployeeDaoImpl.loadComplaints();
    }

    @Override
    public List<Complaint> assignToEmployee(List<Complaint> complains, long empId) {
            if(complains != null){
                Employee manager = ccsmEmployeeDaoImpl.findEmployeeById(100);
                complains.stream().forEach(complaint -> {
                    Complaint complnt = ccsmEmployeeDaoImpl.findComplaintById(complaint.getComplaintId());
                    Employee emp = ccsmEmployeeDaoImpl.findEmployeeById(empId);
                    complnt.setLastUpdatedBy(manager.getEmployeeName());
                    complnt.setAssignedTo(emp.getEmployeeName());
                    ccsmEmployeeDaoImpl.addComplaints(complnt);
                });
            }

        return ccsmEmployeeDaoImpl.loadComplaints();
    }
}
