package com.nl.tracker.service;

import com.nl.tracker.dao.ServiceRequestTimeDAO;
import com.nl.tracker.model.ServiceRequest;
import com.nl.tracker.model.ServiceRequestTime;
import com.nl.tracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by levin1 on 2/3/2017.
 */
@Service("serviceRequestTimeService")
@Transactional
public class ServiceRequestTimeServiceImpl implements ServiceRequestTimeService {

    @Autowired
    ServiceRequestTimeDAO serviceRequestTimeDAO;

    @Override
    public List<ServiceRequestTime> findBySr(ServiceRequest sr) {
        return serviceRequestTimeDAO.findBySr(sr);
    }

    @Override
    public List<ServiceRequestTime> findBySrs(List<ServiceRequest> srs) {
        return serviceRequestTimeDAO.findBySrs(srs);
    }

    @Override
    public void save(ServiceRequestTime srTime) {
        serviceRequestTimeDAO.save(srTime);
    }

    @Override
    public void delete(Long id) {
        serviceRequestTimeDAO.deleteId(id);
    }

    @Override
    public List<ServiceRequestTime> findByTseForPeriod(User user, Date start, Date end) {
        return serviceRequestTimeDAO.findByTseForPeriod(user, start, end);
    }
}
