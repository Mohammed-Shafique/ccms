package com.shafique.springboot.util;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.CustomerDetails;
import com.shafique.springboot.model.Employee;
import com.shafique.springboot.view.CustomerComplaintView;
import com.shafique.springboot.view.CustomerRegistrationView;
import com.shafique.springboot.view.EmployeeView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mohammedshafique
 */

@Component
public class ViewModelMapper {

    public ViewModelMapper(){
        this.employeeTypeMap.put("MAN", "Manager");
        this.employeeTypeMap.put("SRVEXC", "Service Executive Engineer");
        this.employeeTypeMap.put("SRVENG", "Service Engineer");
    }
    private Map<String, String> employeeTypeMap = new HashMap<String, String>();
    public CustomerDetails map(CustomerRegistrationView customerRegistrationView){
        CustomerDetails customerDetailsModel = new CustomerDetails();
        customerDetailsModel.setEmpType(customerRegistrationView.getEmpType());
        customerDetailsModel.setFirstName(customerRegistrationView.getFirstName());
        customerDetailsModel.setLastName(customerRegistrationView.getLastName());
        customerDetailsModel.setEmail(customerRegistrationView.getEmail());
        customerDetailsModel.setPhone(customerRegistrationView.getPhone());
        customerDetailsModel.setAddress1(customerRegistrationView.getAddress1());
        customerDetailsModel.setAddress2(customerRegistrationView.getAddress2());
        customerDetailsModel.setPassword(customerRegistrationView.getPassword());
        return customerDetailsModel;
    }

    public CustomerRegistrationView map(CustomerDetails customerDetails, boolean isPassword){
        CustomerRegistrationView  customerDetailsView = new CustomerRegistrationView();
        customerDetailsView.setFirstName(customerDetails.getFirstName());
        customerDetailsView.setLastName(customerDetails.getLastName());
        customerDetailsView.setEmail(customerDetails.getEmail());
        customerDetailsView.setPhone(customerDetails.getPhone());
        customerDetailsView.setAddress1(customerDetails.getAddress1());
        customerDetailsView.setAddress2(customerDetails.getAddress2());

        if(isPassword)
        customerDetailsView.setPassword(customerDetails.getPassword());

        return customerDetailsView;
    }

    public Complaint map(CustomerComplaintView customerComplaintView){
        Complaint complaint = new Complaint();
        complaint.setCustomerId(customerComplaintView.getCustomerId());
        complaint.setProductName(customerComplaintView.getProductName());
        complaint.setProductCategory(customerComplaintView.getProductCategory());
        complaint.setComplain(customerComplaintView.getComplaint());
        return complaint;
    }

    public Employee map(EmployeeView employeeView){
        Employee emp = new Employee();
        emp.setEmail(employeeView.getEmail());
        emp.setEmployeeName(employeeView.getEmployeeName());
        emp.setAddress1(employeeView.getAddress1());
        emp.setAddress2(employeeView.getAddress2());
        emp.setPhone(employeeView.getPhone());
        emp.setEmpType(employeeView.getEmpType());
        emp.setLastUpdatedBy(employeeView.getUserName());

        return emp;
    }

    public List<EmployeeView> mapEmployeeList(List<Employee> empList){
        List<EmployeeView> employeeViewList = new ArrayList<>();
        if(empList !=null && !empList.isEmpty()){
            empList.stream().forEach(e -> {
                EmployeeView empView = new EmployeeView();
                empView.setEmployeeId(e.getEmployeeId());
                empView.setEmail(e.getEmail());
                empView.setEmpType(e.getEmpType());
                empView.setAddress1(e.getAddress1());
                empView.setAddress2(e.getAddress2());
                empView.setEmpType(employeeTypeMap.get(e.getEmpType()));
                empView.setPhone(e.getPhone());
                empView.setEmployeeName(e.getEmployeeName());
                employeeViewList.add(empView);
            });
        }

        return employeeViewList;
    }

    public List<CustomerComplaintView> mapComplaintsModelToView(List<Complaint> complaints){
        List<CustomerComplaintView> complaintsViewList = new ArrayList<>();
        if(complaints != null && !complaints.isEmpty()){
            complaints.stream().forEach(e -> {
                CustomerComplaintView complaintView = new CustomerComplaintView();
                complaintView.setCustomerId(e.getCustomerId());
                complaintView.setComplaintId(e.getComplaintId());
                complaintView.setProductCategory(e.getProductCategory());
                complaintView.setComplaint(e.getComplain());
                complaintView.setComplaintStatus(e.getComplainStatus());
                complaintView.setComment(e.getComment());
                complaintView.setComplaintDate(e.getComplaintDate());
                complaintView.setProductName(e.getProductName());
                complaintsViewList.add(complaintView);
            });
        }
        return complaintsViewList;
    }

    public List<Complaint> mapComplaintsViewToModel(List<CustomerComplaintView> complaintsView){
        List<Complaint> complaintsModel = new ArrayList<>();
        if(complaintsView != null){
            complaintsView.stream().forEach(complaintView -> {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(complaintView.getComplaintId());
                complaint.setCustomerId(complaintView.getCustomerId());
                complaint.setProductName(complaintView.getProductName());
                complaint.setProductCategory(complaintView.getProductCategory());
                complaint.setComplain(complaintView.getComplaint());
                complaintsModel.add(complaint);
            });
        }
       return complaintsModel;
    }
}
