package com.nl.tracker.model;

import java.util.Date;

/**
 * Created by levin1 on 2017-02-07.
 */
public class SearchCriteria {
    private User user;
    private Date start;
    private Date end;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
