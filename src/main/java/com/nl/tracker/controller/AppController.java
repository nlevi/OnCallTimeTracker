package com.nl.tracker.controller;

/**
 * Created by levin1 on 1/11/2017.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nl.tracker.model.*;
import com.nl.tracker.service.ServiceRequestService;
import com.nl.tracker.service.ServiceRequestTimeService;
import com.nl.tracker.service.UserProfileService;
import com.nl.tracker.service.UserService;
import com.nl.tracker.utils.DateUtils;
import com.nl.tracker.utils.TimeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/")
@SessionAttributes("role")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    ServiceRequestService serviceRequestService;

    @Autowired
    ServiceRequestTimeService serviceRequestTimeService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;


    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        List<User> users;
        User user = userService.findByUserName(getPrincipal());
        if (user.getUserProfiles().getType().equals(UserProfileType.MANAGER.getUserProfileType())) {
            users = userService.findByManager(user);
        } else {
            users = userService.findAllUsers();
        }
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userlist";
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String getProfile(ModelMap model) {
        User user = userService.findByUserName(getPrincipal());
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("editMgr", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.POST)
    public void updateProfile(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        User user = userService.findByUserName(getPrincipal());
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setTimezone(request.getParameter("timezone"));
        userService.updateUser(user);

        model.addAttribute("loggedinuser", getPrincipal());
        response.sendRedirect("/srlist");
    }

    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("editMgr", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    //public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
    public String saveUser(HttpServletRequest request, ModelMap model) {

        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        String manager = request.getParameter("manager");
        if (!manager.equals("")) {
            user.setManager(userService.findById(Integer.parseInt(manager)));
        }
        Set<UserProfile> profiles = new HashSet<UserProfile>();
        profiles.add(userProfileService.findById(Integer.parseInt(request.getParameter("userProfiles"))));
        user.setUserProfiles(userProfileService.findById(Integer.parseInt(request.getParameter("userProfiles"))));

        if (!userService.isUserNameUnique(user.getId(), user.getUsername())) {
            FieldError usernameError = new FieldError("user", "username", messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
            model.addAttribute("error", usernameError);
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationsuccess";
    }

    @RequestMapping(value = {"/edit-user-{username}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {
        User user = userService.findByUserName(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("editMgr", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    @RequestMapping(value = {"/edit-user-{username}"}, method = RequestMethod.POST)
    //public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String username) {
    public String updateUser(HttpServletRequest request, ModelMap model, @PathVariable String username) {

        User user = userService.findByUserName(username);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setUserProfiles(userProfileService.findById(Integer.parseInt(request.getParameter("userProfiles"))));

        if (request.getParameter("manager").equals("")) {
            user.setManager(userService.findById(Integer.parseInt(request.getParameter("manager"))));
        }
        user.setTimezone(request.getParameter("timezone"));
        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }

    @RequestMapping(value = {"/delete-user-{username}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/home";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @ModelAttribute("managers")
    public List<User> initializeManagers() {
        UserProfile profile = userProfileService.findByType(UserProfileType.MANAGER.getUserProfileType());

        return userService.findByRole(profile);
    }

    @ModelAttribute("zones")
    public List<String> initializeTimeZones() {
        List<String> zones = Arrays.asList(TimeZone.getAvailableIDs());
        return zones;
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        //return "redirect:/login?logout";
        response.sendRedirect("login?logout");
    }

    @RequestMapping(value = "/newsr", method = RequestMethod.GET)
    public String newServiceRequest(ModelMap model) {
        ServiceRequest sr = new ServiceRequest();
        model.addAttribute("sr", sr);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());

        return "createservicerequest";
    }

    @RequestMapping(value = "/newsr", method = RequestMethod.POST)
    //public String saveServiceRequest(@Valid User user, BindingResult result, ModelMap model) {
    public void saveServiceRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        ServiceRequest sr = new ServiceRequest();
        sr.setSrId(Long.parseLong(request.getParameter("srId")));
        sr.setSiteId(Long.parseLong(request.getParameter("siteId")));
        sr.setCustomerName(request.getParameter("customerName"));
        sr.setSubject(request.getParameter("subject"));
        sr.setCreatedBy(userService.findByUserName(getPrincipal()));
        sr.setSeverity(request.getParameter("severity"));
        sr.setCreationDate(DateUtils.getCurrentUTCDate());
        serviceRequestService.saveSr(sr);
        //return "srlist";
        response.sendRedirect("view-sr-" + sr.getSrId());
    }

    @RequestMapping(value = "/srlist", method = RequestMethod.GET)
    public String srList(HttpServletRequest request, ModelMap model) {
        List<ServiceRequest> srs = null;

        List<User> tses = null;

        User user = userService.findByUserName(getPrincipal());

        SearchCriteria searchCriteria = new SearchCriteria();

        if (user.getUserProfiles().getType().equals(UserProfileType.MANAGER.getUserProfileType())) {
            tses = userService.findByManager(user);
            srs = serviceRequestService.findSrByManager(userService.findByManager(user));
        } else if (user.getUserProfiles().getType().equals(UserProfileType.WFM.getUserProfileType())) {
            //to be discussed
            tses = userService.findByRole(userProfileService.findByType(UserProfileType.TSE.getUserProfileType()));
        } else {
            srs = serviceRequestService.findSrByCreator(userService.findByUserName(getPrincipal()));
            model.addAttribute("user", user);
        }

        model.addAttribute("srs", srs);
        model.addAttribute("tses", tses);
        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("loggedinuser", getPrincipal());
        return "srlist";
    }

    @RequestMapping(value = "/srlist", method = RequestMethod.POST)
    public String srListFiltered(HttpServletRequest request, ModelMap model) {
        List<ServiceRequest> srs;
        User mgr = userService.findByUserName(getPrincipal());
        List<User> tses = userService.findByManager(mgr);
        SearchCriteria searchCriteria = new SearchCriteria();
        Date start = DateUtils.getUTCDate(request.getParameter("start"), "yyyy-MM-dd'T'HH:mm:ssX");
        Date end = DateUtils.getUTCDate(request.getParameter("end"), "yyyy-MM-dd'T'HH:mm:ssX");
        User user = userService.findById(Integer.parseInt(request.getParameter("user")));

        srs = serviceRequestService.findForPeriod(user, start, end);
        int hours = 0;
        if (srs.size() != 0) {
            hours = TimeCalculator.calculateHrs(serviceRequestTimeService.findBySrs(srs));
        }
        model.addAttribute("srs", srs);
        model.addAttribute("tses", tses);
        model.addAttribute("hours", hours);
        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("loggedinuser", getPrincipal());
        return "srlist";
    }

    @RequestMapping(value = "/view-sr-{srid}", method = RequestMethod.GET)
    public String viewSr(ModelMap model, @PathVariable long srid) {
        ServiceRequest sr = serviceRequestService.findSrId(srid);
        List<ServiceRequestTime> srTimes = serviceRequestTimeService.findBySr(sr);
        int hours = TimeCalculator.calculateHrs(srTimes);
        model.addAttribute("sr", sr);
        model.addAttribute("srTimes", srTimes);
        model.addAttribute("hours", hours);
        model.addAttribute("timezone", userService.findByUserName(getPrincipal()).getTimezone());
        model.addAttribute("loggedinuser", getPrincipal());
        return "srview";
    }

    @RequestMapping(value = "/addtime-{srid}", method = RequestMethod.GET)
    public String addSrTime(ModelMap model, @PathVariable long srid) {
        ServiceRequest sr = serviceRequestService.findSrId(srid);
        ServiceRequestTime srTime = new ServiceRequestTime();

        model.addAttribute("sr", sr);
        model.addAttribute("srTime", srTime);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "addsrtime";
    }

    @RequestMapping(value = "/addtime-{srid}", method = RequestMethod.POST)
    public void saveTime(HttpServletRequest request, ModelMap model, @PathVariable long srid, HttpServletResponse response) throws ParseException, IOException {
        ServiceRequestTime srTime = new ServiceRequestTime();
        srTime.setServiceRequest(serviceRequestService.findSrId(srid));
        srTime.setStartTime(DateUtils.getUTCDate(request.getParameter("startTime"), "yyyy-MM-dd'T'HH:mm:ssX"));
        srTime.setEndTime(DateUtils.getUTCDate(request.getParameter("endTime"), "yyyy-MM-dd'T'HH:mm:ssX"));

        long t = TimeUnit.MILLISECONDS.toMinutes(srTime.getEndTime().getTime() - srTime.getStartTime().getTime());
        srTime.setTimeSpent(t);
        srTime.setCreationDate(DateUtils.getCurrentUTCDate());
        srTime.setUser(userService.findByUserName(getPrincipal()));
        serviceRequestTimeService.save(srTime);

        model.addAttribute("success", "Time added");
        model.addAttribute("loggedinuser", getPrincipal());
        response.sendRedirect("view-sr-" + srid);
        //return "redirect:/view-sr-" + srid;
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userService.findByUserName(userName).getUsername();
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}