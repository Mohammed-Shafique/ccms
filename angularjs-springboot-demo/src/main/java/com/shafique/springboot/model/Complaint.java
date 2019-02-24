package com.shafique.springboot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author mohammedshafique
 */
@Entity
@Table(name = "COMPLAINTS")
@IdClass(Complaint.ComplaintId.class)
public class Complaint {
    @Id
    @GeneratedValue()
    @Column(name = "COMPLAINT_ID")
    private long complaintId;
    @Id
    private long customerId;
    @Id
    private String productName;
    @Id
    private String productCategory;
    @Id
    private String complain;
    @Id
    private String complainStatus;
    private String comment ="";
    @Temporal(TemporalType.DATE)
    private Date complaintDate;
    @Temporal(TemporalType.DATE)
    @Id
    private Date lastUpdatedDate;
    private String lastUpdatedBy;
    private String assignedTo;
    public Complaint(){

    }
    static class ComplaintId implements Serializable {
        private long complaintId;
        private long customerId;
        private String productName;
        private String productCategory;
        private String complain;
        private String comment;
        private Date lastUpdatedDate;
    }

    public long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(long complaintId) {
        this.complaintId = complaintId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus;
    }

    public Date getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Date complaintDate) {
        this.complaintDate = complaintDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
