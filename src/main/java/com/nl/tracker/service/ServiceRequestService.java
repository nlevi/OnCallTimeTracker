package com.nl.tracker.service;

import com.nl.tracker.model.ServiceRequest;
import com.nl.tracker.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by levin1 on 1/30/2017.
 */
public interface ServiceRequestService {

    ServiceRequest findSrId(long id);

    ServiceRequest findBySiteId(long id);

    void saveSr(ServiceRequest sr);

    void deleteBySrId(Long id);

    List<ServiceRequest> findAllSrs();

    List<ServiceRequest> findSrByCreator(User user);

    List<ServiceRequest> findSrByManager(List<User> users);

    List<ServiceRequest> findForPeriod(User user, Date start, Date end);
}
