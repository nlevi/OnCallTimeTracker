package com.nl.tracker.dao;

import com.nl.tracker.model.ServiceRequest;
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
 * Created by levin1 on 1/27/2017.
 */
@Repository("serviceRequestDao")
public class ServiceRequestDAOImpl extends AbstractDAO<Integer, ServiceRequest> implements ServiceRequestDAO {

    static final Logger logger = LoggerFactory.getLogger(ServiceRequestDAOImpl.class);

    @Override
    public ServiceRequest findSrId(long id) {
        logger.info("Service Request ID : {}", id);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("srId", id));
        ServiceRequest sr = (ServiceRequest) crit.uniqueResult();
        return sr;
    }

    @Override
    public ServiceRequest findBySiteId(long id) {
        return null;
    }

    @Override
    public void save(ServiceRequest sr) {
        persist(sr);
    }

    @Override
    public void deleteBySrId(Long id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("srId", id));
        ServiceRequest sr = (ServiceRequest) crit.uniqueResult();
        delete(sr);
    }

    @Override
    public List<ServiceRequest> findAllSrs() {
        return null;
    }

    @Override
    public List<ServiceRequest> findByCreator(User user) {
        logger.info("Service Request Creator : {}", user);
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("createdBy", user)).addOrder(Order.desc("creationDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<ServiceRequest> srs = (List<ServiceRequest>) criteria.list();
        for (ServiceRequest sr : srs) {
            Hibernate.initialize(sr.getCreatedBy());
        }
        return srs;
    }

    @Override
    public List<ServiceRequest> findByManager(List<User> users) {
        logger.info("Service Request Creator : {}", users);
        Criteria criteria = createEntityCriteria().add(Restrictions.in("createdBy", users)).addOrder(Order.asc("creationDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<ServiceRequest> srs = (List<ServiceRequest>) criteria.list();
        for (ServiceRequest sr : srs) {
            Hibernate.initialize(sr.getCreatedBy());
        }
        return srs;
    }

    @Override
    public List<ServiceRequest> findForPeriod(User user, Date start, Date end) {
        logger.info("User : {}, Start : {}, End : {}", user, start, end);
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("createdBy", user))
                .add(Restrictions.ge("creationDate", start))
                .add(Restrictions.lt("creationDate", end))
                .addOrder(Order.asc("creationDate"));
        List<ServiceRequest> srs = (List<ServiceRequest>) criteria.list();
        for (ServiceRequest sr : srs) {
            Hibernate.initialize(sr.getCreatedBy());
        }
        return srs;
    }
}
