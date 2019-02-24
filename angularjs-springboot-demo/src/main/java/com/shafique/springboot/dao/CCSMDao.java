package com.shafique.springboot.dao;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.CustomerDetails;
import com.shafique.springboot.model.Employee;

/**
 * @author mohammedshafique
 */
public interface CCSMDao {

    public CustomerDetails getCustomerByEmail(String email);
    public long signup(CustomerDetails customerDetails);
    public CustomerDetails login(String email, String password);
    public Employee employeeLogin(String email, String password);
    public CustomerDetails getCustomer(long customerId);
    public CustomerDetails updateCustomer(CustomerDetails cust);
    public Complaint saveComplaint(Complaint complaint);
    public Employee getEmployeeById(long employeeId);
}
