package com.nl.tracker.dao;

/**
 * Created by levin1 on 1/11/2017.
 */

import com.nl.tracker.model.User;
import com.nl.tracker.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userDao")
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {

    static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    public User findById(int id) {
        return getByKey(id);
    }

    public User findByUserName(String username) {
        logger.info("Username : {}", username);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getManager());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {

        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<User> users = (List<User>) criteria.list();
        for (User user : users) {
            //Hibernate.initialize(user.getUserProfiles());
            Hibernate.initialize(user.getManager());
        }
        return users;
    }

    @SuppressWarnings("unchecked")
    public List<User> findByRole(UserProfile profile) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("userProfiles", profile)).addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        //List<User> users = (List<User>) criteria.list();
        return (List<User>) criteria.list();
    }

    @Override
    public List<User> findByManager(User user) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("manager", user)).addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<User> users = (List<User>) criteria.list();
        for (User usr : users) {
            Hibernate.initialize(usr.getManager());
        }
        return users;
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteByUserName(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User) crit.uniqueResult();
        delete(user);
    }


}
