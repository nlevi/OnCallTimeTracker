package com.nl.tracker.service;

/**
 * Created by levin1 on 1/11/2017.
 */

import com.nl.tracker.dao.UserDAO;
import com.nl.tracker.model.User;
import com.nl.tracker.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    User findById(int id);

    User findByUserName(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserByUsername(String username);

    List<User> findAllUsers();

    List<User> findByRole(UserProfile profile);

    List<User> findByManager(User user);

    boolean isUserNameUnique(Integer id, String username);
}
