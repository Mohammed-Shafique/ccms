package com.shafique.springboot.controller;

import com.shafique.springboot.model.Employee;
import com.shafique.springboot.model.LoginResponse;
import com.shafique.springboot.model.CustomerDetails;
import com.shafique.springboot.model.UserLogin;
import com.shafique.springboot.service.CCSMService;
import com.shafique.springboot.util.ViewModelMapper;
import com.shafique.springboot.view.CustomerRegistrationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author mohammedshafique
 */
@RestController("/userLogin")
public class LoginController {

    @Autowired
    private CCSMService ccsmServiceImpl;

    @Autowired
    private ViewModelMapper viewModelMapper;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody UserLogin userLogin, HttpSession session){
        LoginResponse response = new LoginResponse();
        if("C".equals(userLogin.getUserType())) {
            CustomerDetails cust = ccsmServiceImpl.login(userLogin);
            if(cust != null){
                session.setAttribute("userName", userLogin.getEmail());
                response.setEmpType(cust.getEmpType());
                response.setCustomerId(cust.getCustomerId());
                response.setUserName(userLogin.getEmail());
                response.setMessage("Login Successful");
                response.setLoginStatus(true);
            }
        }else{
            Employee emp = ccsmServiceImpl.employeeLogin(userLogin);
            if(emp != null){
                session.setAttribute("userName", userLogin.getEmail());
                response.setEmpType(emp.getEmpType());
                response.setCustomerId(emp.getEmployeeId());
                response.setUserName(userLogin.getEmail());
                response.setMessage("Login Successful");
                response.setLoginStatus(true);
            }
        }
            if(!response.isLoginStatus())
            response.setMessage("Invalid UserName or Password");

        return response;
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse signup(@RequestBody CustomerRegistrationView customerDetailsView){
        System.out.println(customerDetailsView);
        String message = ccsmServiceImpl.sungup(viewModelMapper.map(customerDetailsView));
        LoginResponse response = new LoginResponse();
        response.setLoginStatus(true);
        //response.setMessage(message);
        return response;
    }

    @PostMapping(value="/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse logout(@RequestBody UserLogin userLogin, HttpSession session){
        LoginResponse response = new LoginResponse();
        session.removeAttribute(userLogin.getEmail());
        session.invalidate();
        response.setLoginStatus(false);
        response.setMessage("Logged Out Successfully");
        return response;
    }
}
