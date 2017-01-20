package com.nl.tracker.configuration;

import com.nl.tracker.model.UserProfile;
import com.nl.tracker.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by levin1 on 1/12/2017.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

    static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

    @Autowired
    UserProfileService userProfileService;

    public UserProfile convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        UserProfile profile = userProfileService.findById(id);
        logger.info("Profile : {}", profile);
        return profile;
    }
}
