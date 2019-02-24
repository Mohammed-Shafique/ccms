package com.shafique.springboot.service;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.CustomerDetails;
import com.shafique.springboot.model.Employee;
import com.shafique.springboot.model.UserLogin;

import java.util.List;

/**
 * @author mohammedshafique
 */

public interface CCSMService {

    public String sungup(CustomerDetails customerDetails);
    public CustomerDetails login(UserLogin userLogin);
    public Employee employeeLogin(UserLogin userLogin);
    public CustomerDetails getCustomerByEmail(long customerId);
    public CustomerDetails updateCustomer(CustomerDetails cust);
    public CustomerDetails changePassword(CustomerDetails cust);
    public Complaint registerComplaint(Complaint complaint);
    public List<Complaint> loadComplaints(long userId, long complaintId);


}
