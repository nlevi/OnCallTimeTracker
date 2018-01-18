package com.nl.tracker.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

/**
 * Created by levin1 on 2017-03-05.
 */
public interface AuthenticationFacade {
    UserDetails getPrincipal();
}
