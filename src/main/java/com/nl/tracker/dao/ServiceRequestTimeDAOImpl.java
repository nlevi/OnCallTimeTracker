package com.nl.tracker.dao;

import com.nl.tracker.model.ServiceRequest;
import com.nl.tracker.model.ServiceRequestTime;
import com.nl.tracker.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by levin1 on 2/2/2017.
 */
@Repository("serviceRequestTimeDao")
public class ServiceRequestTimeDAOImpl extends AbstractDAO<Integer, ServiceRequestTime> implements ServiceRequestTimeDAO {

    static final Logger logger = LoggerFactory.getLogger(ServiceRequestDAOImpl.class);

    @Override
    public List<ServiceRequestTime> findBySr(ServiceRequest sr) {
        logger.info("Service Request ID : {}", sr.getSrId());
        Criteria crit = createEntityCriteria().add(Restrictions.eq("serviceRequest", sr)).addOrder(Order.asc("startTime"));
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<ServiceRequestTime> srTime = (List<ServiceRequestTime>) crit.list();
        for (ServiceRequestTime time : srTime) {
            Hibernate.initialize(time.getUser());
        }
        return srTime;
    }

    @Override
    public List<ServiceRequestTime> findBySrs(List<ServiceRequest> srs) {
        //logger.info("Service Request ID : {}", srs.getSrId());
        Criteria crit = createEntityCriteria().add(Restrictions.in("serviceRequest", srs)).addOrder(Order.asc("startTime"));
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<ServiceRequestTime> srTime = (List<ServiceRequestTime>) crit.list();
        return srTime;
    }

    @Override
    public void save(ServiceRequestTime srTime) {
        persist(srTime);
    }

    @Override
    public void deleteId(Long id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        ServiceRequestTime srTime = (ServiceRequestTime) crit.uniqueResult();
        delete(srTime);
    }

    @Override
    public List<ServiceRequestTime> findByTseForPeriod(User user, Date start, Date end) {
        logger.info("User ID : {}, Start Date: {}, End date: {}", user.getId(), start, end);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("user", user));
        crit.add(Restrictions.ge("startTime", start));
        crit.add(Restrictions.lt("endTime", end));
        crit.addOrder(Order.asc("startTime"));
        List<ServiceRequestTime> srTime = (List<ServiceRequestTime>) crit.uniqueResult();
        return srTime;
    }
}
