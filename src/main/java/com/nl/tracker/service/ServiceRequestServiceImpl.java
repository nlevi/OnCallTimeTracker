package com.nl.tracker.service;

import com.nl.tracker.dao.ServiceRequestDAO;
import com.nl.tracker.dao.UserDAO;
import com.nl.tracker.model.ServiceRequest;
import com.nl.tracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by levin1 on 1/30/2017.
 */
@Service("serviceRequestService")
@Transactional
public class ServiceRequestServiceImpl implements ServiceRequestService {

    @Autowired
    ServiceRequestDAO serviceRequestDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public ServiceRequest findSrId(long id) {
        return serviceRequestDAO.findSrId(id);
    }

    @Override
    public ServiceRequest findBySiteId(long id) {
        return null;
    }

    @Override
    public void saveSr(ServiceRequest sr) {
        serviceRequestDAO.save(sr);
    }

    @Override
    public void deleteBySrId(Long id) {

    }

    @Override
    public List<ServiceRequest> findAllSrs() {
        return null;
    }

    @Override
    public List<ServiceRequest> findSrByCreator(User user) {
        return serviceRequestDAO.findByCreator(user);
    }

    @Override
    public List<ServiceRequest> findSrByManager(List<User> users) {
        return serviceRequestDAO.findByManager(users);
    }

    @Override
    public List<ServiceRequest> findForPeriod(User user, Date start, Date end) {
        return serviceRequestDAO.findForPeriod(user, start, end);
    }
}
