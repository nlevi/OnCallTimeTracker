package com.nl.tracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by levin1 on 1/26/2017.
 */
@Entity
@Table(name = "SERVICE_REQUEST")
public class ServiceRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sr_id", unique = true, nullable = false)
    private long srId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "site_id")
    private long siteId;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    @Column(name = "severity")
    private String severity;

    public long getSrId() {
        return srId;
    }

    public void setSrId(long id) {
        this.srId = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
