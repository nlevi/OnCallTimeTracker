package com.nl.tracker.service;

import com.nl.tracker.model.ServiceRequest;
import com.nl.tracker.model.ServiceRequestTime;
import com.nl.tracker.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by levin1 on 2/3/2017.
 */
public interface ServiceRequestTimeService {
    List<ServiceRequestTime> findBySr(ServiceRequest sr);

    List<ServiceRequestTime> findBySrs(List<ServiceRequest> srs);

    void save(ServiceRequestTime srTime);

    void delete(Long id);

    List<ServiceRequestTime> findByTseForPeriod(User user, Date start, Date end);
}
