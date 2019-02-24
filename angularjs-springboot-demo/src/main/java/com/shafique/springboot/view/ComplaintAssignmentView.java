package com.shafique.springboot.view;

import java.util.List;

public class ComplaintAssignmentView {
    private List<CustomerComplaintView> complaints;
    private long assignedToEmployeeId;

    public ComplaintAssignmentView(){}

    public List<CustomerComplaintView> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<CustomerComplaintView> complaints) {
        this.complaints = complaints;
    }

    public long getAssignedToEmployeeId() {
        return assignedToEmployeeId;
    }

    public void setAssignedToEmployeeId(long assignedToEmployeeId) {
        this.assignedToEmployeeId = assignedToEmployeeId;
    }
}
