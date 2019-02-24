package com.shafique.springboot.view;

/**
 * @author mohammedshafique
 */
public class UserLoginAndPasswordResetRequest {
    private String email;
    private String password;

    public UserLoginAndPasswordResetRequest(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
