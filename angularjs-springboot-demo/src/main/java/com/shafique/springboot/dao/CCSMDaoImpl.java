package com.shafique.springboot.dao;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.CustomerDetails;
import com.shafique.springboot.model.Employee;
import com.shafique.springboot.repository.CCSMComplaintsRepository;
import com.shafique.springboot.repository.CCSMEmployeeRepository;
import com.shafique.springboot.repository.CCSMUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mohammedshafique
 */
@Component
public class CCSMDaoImpl implements CCSMDao{

    @Autowired
    private CCSMUserRepository ccsmUserRepository;

    @Autowired
    private CCSMComplaintsRepository ccsmComplaintsRepository;

    @Autowired
    private CCSMEmployeeRepository ccsmEmployeeRepository;

    @Override
    public CustomerDetails getCustomerByEmail(String email) {
        return ccsmUserRepository.findCustomerByEmail(email);
    }

    @Override
    public CustomerDetails login(String email, String password) {
        boolean isValidUser =false;
        CustomerDetails customerDetails = ccsmUserRepository.findByLoginDetails(email, password);
        return customerDetails;
    }

    @Override
    public long signup(CustomerDetails customerDetails){
        System.out.println("dao"+customerDetails.toString());
        long custId = ccsmUserRepository.save(customerDetails).getCustomerId();
        return custId;
    }

    @Override
    public CustomerDetails getCustomer(long customerId) {
        return ccsmUserRepository.findCustomerById(customerId);
    }

    @Override
    public CustomerDetails updateCustomer(CustomerDetails cust) {
        return ccsmUserRepository.save(cust);
    }

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        return ccsmComplaintsRepository.save(complaint);
    }

    @Override
    public Employee employeeLogin(String email, String password) {
        return ccsmEmployeeRepository.findByLoginDetails(email, password);
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return ccsmEmployeeRepository.findEmployeById(employeeId);
    }
}
