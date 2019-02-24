package com.shafique.springboot.service;

import com.shafique.springboot.dao.CCSMDao;
import com.shafique.springboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * @author mohammedshafique
 *
 */
@Service
public class CCSMServiceImpl implements  CCSMService {

    @Autowired
    private CCSMDao ccsmDaoImpl;

    @Override
    public CustomerDetails login(UserLogin userLogin) {
            return ccsmDaoImpl.login(userLogin.getEmail(), userLogin.getPassword());
    }

    public Employee employeeLogin(UserLogin userLogin) {
        return ccsmDaoImpl.employeeLogin(userLogin.getEmail(), userLogin.getPassword());
    }

    @Override
    public String sungup(CustomerDetails customerDetails) {
        customerDetails.setCreateDate(new Date());
        customerDetails.setLastUpdated(new Date());
        customerDetails.setEmpType(UserRoleType.CUST.toString());
        customerDetails.setUpdatedBy("SYSTEM");
        System.out.println("srvImpl "+customerDetails.toString());
        Long custId = ccsmDaoImpl.signup(customerDetails);
        String message = null;
        if(custId != null){
            message = "Signup Successful";
        }
        return message;
    }

    @Override
    public CustomerDetails getCustomerByEmail(long customerId) {
        return ccsmDaoImpl.getCustomer(customerId);
    }

    @Override
    public CustomerDetails updateCustomer(CustomerDetails cust) {
        CustomerDetails customerDetails = ccsmDaoImpl.getCustomerByEmail(cust.getEmail());
        customerDetails.setUpdatedBy(cust.getEmail());
        customerDetails.setLastUpdated(new Date());
        customerDetails.setPhone(cust.getPhone());
        customerDetails.setAddress1(cust.getAddress1());
        customerDetails.setAddress2(cust.getAddress2());
        CustomerDetails customerDetailsUpdated = ccsmDaoImpl.updateCustomer(customerDetails);
        return customerDetailsUpdated;
    }

    @Override
    public CustomerDetails changePassword(CustomerDetails cust) {
        CustomerDetails customerDetails = ccsmDaoImpl.getCustomerByEmail(cust.getEmail());
        customerDetails.setLastUpdated(new Date());
        customerDetails.setPassword(cust.getPassword());
        CustomerDetails customerDetailsUpdated = ccsmDaoImpl.updateCustomer(customerDetails);
        return customerDetailsUpdated;
    }

    @Override
    public Complaint registerComplaint(Complaint complaint) {
        CustomerDetails customerDetails = ccsmDaoImpl.getCustomer(complaint.getCustomerId());
        complaint.setLastUpdatedBy(customerDetails.getEmail());
        complaint.setLastUpdatedDate(new Date());
        complaint.setComplainStatus(ComplaintStatus.OPEN.toString());
        complaint.setComplaintDate(new Date());
        if(customerDetails.getComplaints() == null) {
            Set<Complaint> complaints = new HashSet<>();
            complaints.add(complaint);
            customerDetails.getComplaints().addAll(complaints);
        }else{
            customerDetails.getComplaints().add(complaint);
        }

        CustomerDetails cust = ccsmDaoImpl.updateCustomer(customerDetails);
        List<Complaint> complaints = new ArrayList<>(cust.getComplaints());
        return complaints.get(0);
    }

    @Override
    public List<Complaint> loadComplaints(long userId, long complaintId) {
        CustomerDetails cust = ccsmDaoImpl.getCustomer(userId);
        List<Complaint> customerComplaints = new ArrayList<>();
        if(cust != null && !cust.getComplaints().isEmpty()){
            if(complaintId > 0l){
                customerComplaints.add(findComplaint(new ArrayList<>(cust.getComplaints()), complaintId));
            }else {
                customerComplaints.addAll(new ArrayList<>(cust.getComplaints()));
            }
        }
        return customerComplaints;
    }

    private Complaint findComplaint(List<Complaint> complaints, long complaintId){

        Optional<Complaint> compltnOptional = complaints.stream().filter(e -> e.getComplaintId() == complaintId).findFirst();
        Complaint complain = null;
        if(compltnOptional.isPresent()){
            complain = compltnOptional.get();
        }
        return complain;
    }

}
