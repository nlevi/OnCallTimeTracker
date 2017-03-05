package com.nl.tracker.dao;

import com.nl.tracker.model.User;
import com.nl.tracker.model.UserProfile;

import java.util.List;

/**
 * Created by levin1 on 1/11/2017.
 */
public interface UserDAO {
    User findById(int id);

    User findByUserName(String username);

    void save(User user);

    void deleteByUserName(String username);

    List<User> findAllUsers();

    List<User> findByRole(UserProfile profile);

    List<User> findByManager(User user);
}
