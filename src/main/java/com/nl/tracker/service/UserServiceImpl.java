package com.nl.tracker.service;

/**
 * Created by levin1 on 1/11/2017.
 */

import com.nl.tracker.dao.UserDAO;
import com.nl.tracker.dao.UserProfileDAO;
import com.nl.tracker.model.User;
import com.nl.tracker.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(int id) {
        return userDAO.findById(id);
    }

    public User findByUserName(String username) {
        //User user = userDAO.findByUserName(username);
        return userDAO.findByUserName(username);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFullName(user.getFirstName() + ' ' + user.getLastName());
        userDAO.save(user);
    }

    public void updateUser(User user) {
        User entity = userDAO.findById(user.getId());
        if (entity != null) {
            entity.setUsername(user.getUsername());
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
            entity.setFullName(user.getFirstName() + ' ' + user.getLastName());
            entity.setManager(user.getManager());
            entity.setTimezone(user.getTimezone());
            userDAO.save(entity);
        }
    }


    public void deleteUserByUsername(String username) {
        userDAO.deleteByUserName(username);
    }

    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    public List<User> findByRole(UserProfile profile) {
        return userDAO.findByRole(profile);
    }

    @Override
    public List<User> findByManager(User user) {
        return userDAO.findByManager(user);
    }

    public boolean isUserNameUnique(Integer id, String username) {
        User user = findByUserName(username);
        return (user == null || ((id != null) && (user.getId() == id)));
    }
}
