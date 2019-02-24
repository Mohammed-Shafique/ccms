package com.shafique.springboot.view;

import java.util.List;

public class DashBoardView {
    private List<CustomerComplaintView> complaints;
    private List<EmployeeView> employees;
    private int openTickets;
    public DashBoardView(){}

    public List<CustomerComplaintView> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<CustomerComplaintView> complaints) {
        this.complaints = complaints;
    }

    public int getOpenTickets() {
        return openTickets;
    }

    public void setOpenTickets(int openTickets) {
        this.openTickets = openTickets;
    }

    public List<EmployeeView> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeView> employees) {
        this.employees = employees;
    }
}
