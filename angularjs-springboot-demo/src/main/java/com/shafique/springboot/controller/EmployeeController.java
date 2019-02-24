package com.shafique.springboot.controller;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.Employee;
import com.shafique.springboot.service.CCSMEmployeeService;
import com.shafique.springboot.util.ViewModelMapper;
import com.shafique.springboot.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private CCSMEmployeeService ccsmEmployeeServiceImpl;

    @Autowired
    private ViewModelMapper viewModelMapper;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse addEmployee(@RequestBody EmployeeView employeeView){
        GenericResponse response = new GenericResponse();
        if(employeeView != null){
            Employee empl = viewModelMapper.map(employeeView);
            Employee employee = ccsmEmployeeServiceImpl.addEmployee(empl);
            if(employee != null){
                response.setEntiry(employee);
                response.setMessageSuccess("Employee Added Successfully");
            }
        }
        return response;
    }

    @GetMapping(value = "/loadAll/{empId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> loadEmployees(@PathVariable("empId") long empId){
        List<EmployeeView> employees = new ArrayList<>();
        List<Employee> employeeList = ccsmEmployeeServiceImpl.getEmployees(empId);
        employees.addAll(viewModelMapper.mapEmployeeList(employeeList));
        return employees;
    }

    @PostMapping(value = "/delete/{empId}/{managerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeView> deleteEmployee(@PathVariable("empId") long empId, @PathVariable("managerId") long managerId){
        List<EmployeeView> employees = new ArrayList<>();
        List<Employee> empList = ccsmEmployeeServiceImpl.deleteEmplyee(empId, managerId);
        employees = viewModelMapper.mapEmployeeList(empList);
        return employees;
    }

    @GetMapping(value = "/dashboard/{managerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DashBoardView loadAllComplaints(@PathVariable long managerId){
        DashBoardView dashboard = new DashBoardView();
        List<CustomerComplaintView> complaints = new ArrayList<>();
        List<Complaint> complaintModelList = ccsmEmployeeServiceImpl.loadAllComplaints();
        complaints.addAll(viewModelMapper.mapComplaintsModelToView(complaintModelList));
        dashboard.setComplaints(complaints);
        dashboard.setOpenTickets(complaints.size());
        List<Employee> employeeList = ccsmEmployeeServiceImpl.getEmployees(managerId);
        dashboard.setEmployees(viewModelMapper.mapEmployeeList(employeeList));
        return dashboard;
    }

    @PostMapping(value = "/complainst/assigneto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerComplaintView> assignToEmployee(@RequestBody ComplaintAssignmentView complaintAssignmentView){
        List<CustomerComplaintView> custComplaints = new ArrayList<>();
        List<Complaint> complaintList = viewModelMapper.mapComplaintsViewToModel(complaintAssignmentView.getComplaints());
        List<Complaint> complaints = ccsmEmployeeServiceImpl.assignToEmployee(complaintList, complaintAssignmentView.getAssignedToEmployeeId());
        custComplaints = viewModelMapper.mapComplaintsModelToView(complaints);
        return custComplaints;
    }
}
