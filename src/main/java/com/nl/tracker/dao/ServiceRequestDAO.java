package com.nl.tracker.dao;

import com.nl.tracker.model.ServiceRequest;
import com.nl.tracker.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by levin1 on 1/27/2017.
 */
public interface ServiceRequestDAO {

    ServiceRequest findSrId(long id);

    ServiceRequest findBySiteId(long id);

    void save(ServiceRequest sr);

    void deleteBySrId(Long id);

    List<ServiceRequest> findAllSrs();

    List<ServiceRequest> findByCreator(User user);

    List<ServiceRequest> findByManager(List<User> users);

    List<ServiceRequest> findForPeriod(User user, Date start, Date end);
}
