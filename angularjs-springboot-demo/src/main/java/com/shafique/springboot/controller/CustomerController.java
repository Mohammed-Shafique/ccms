package com.shafique.springboot.controller;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.CustomerDetails;
import com.shafique.springboot.service.CCSMService;
import com.shafique.springboot.util.ViewModelMapper;
import com.shafique.springboot.view.CustomerComplaintView;
import com.shafique.springboot.view.CustomerRegistrationView;
import com.shafique.springboot.view.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mohammedshafique
 */

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CCSMService ccsmServiceImpl;

    @Autowired
    private ViewModelMapper viewModelMapper;

    @GetMapping(value = "/{userName}/{ispassword}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerRegistrationView getCustomerDetails(@PathVariable("userName") long userName, @PathVariable String ispassword){
        CustomerDetails customerModel = ccsmServiceImpl.getCustomerByEmail(userName);
        boolean passwordRequired = false;
        if("Y".equals(ispassword))
            passwordRequired = true;

        CustomerRegistrationView customerRegistrationView = viewModelMapper.map(customerModel, passwordRequired);
        return  customerRegistrationView;
    }

    @PostMapping(value ="/profile/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody GenericResponse updateCustomer(@RequestBody CustomerRegistrationView customerView){
        System.out.println("inside update");
        GenericResponse response = new GenericResponse();
        CustomerDetails customerDetails = viewModelMapper.map(customerView);
        CustomerDetails custDetailsUpdated = ccsmServiceImpl.updateCustomer(customerDetails);
            if (custDetailsUpdated != null) {
                response.setEntiry(customerDetails);
                response.setMessageSuccess("Customer Details Updated Successfully");
            } else {
                response.setMessageError("Unable to serve your request.");
            }

        return response;
    }

    @PostMapping(value ="/password/change", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody GenericResponse changePassword(@RequestBody CustomerRegistrationView customerView){
        GenericResponse response =null;
        if(customerView != null && customerView.getNewPassword1().equals(customerView.getNewPassword2())){
            response = new GenericResponse();
            CustomerDetails customerDetails = viewModelMapper.map(customerView);
            customerDetails.setPassword(customerView.getNewPassword1());
            CustomerDetails custDetailsUpdated = ccsmServiceImpl.changePassword(customerDetails);
            if (custDetailsUpdated != null) {
                response.setEntiry(customerDetails);
                response.setMessageSuccess("Password Changed Successfully");
            } else {
                response.setMessageError("Unable to serve your request.");
            }

        }

        return response;
    }

    @PostMapping(value ="/complaints", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody GenericResponse registerComplaints(@RequestBody CustomerComplaintView customerComplaint){
        GenericResponse response =null;
            response = new GenericResponse();
            Complaint complaintModel = viewModelMapper.map(customerComplaint);
            Complaint complaint = ccsmServiceImpl.registerComplaint(complaintModel);
            if (complaint != null) {
                response.setEntiry(complaint);
                response.setMessageSuccess("Complaint Registered Successfully");
            } else {
                response.setMessageError("Unable to serve your request.");
            }

        return response;
    }

    @GetMapping(value ="/complaints/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<CustomerComplaintView> loadComplaints(@PathVariable("userName") long userName){
        List<CustomerComplaintView> complaintsViewList = new ArrayList<>();
            if(userName > 0){
                List<Complaint> complaintList = ccsmServiceImpl.loadComplaints(userName, 0l);
                complaintsViewList.addAll(viewModelMapper.mapComplaintsModelToView(complaintList));
            }

        return complaintsViewList;
    }

    @PostMapping(value ="/complaints/{userName}/{complaintId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<CustomerComplaintView> searchComplaints(@PathVariable("userName") long userName, @PathVariable("complaintId") long complaintId){
        List<CustomerComplaintView> complaintsViewList = new ArrayList<>();
        if(complaintId > 0l){
            List<Complaint> complaintList = ccsmServiceImpl.loadComplaints(userName, complaintId);
            complaintsViewList.addAll(viewModelMapper.mapComplaintsModelToView(complaintList));
        }

        return complaintsViewList;
    }

}
