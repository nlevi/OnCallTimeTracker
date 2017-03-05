package com.nl.tracker.utils;

/**
 * Created by levin1 on 1/12/2017.
 */

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class QuickPasswordEncodingGenerator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String password = "dctm";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
        /*
        String pwd = "$2a$10$zrN/T6iWZmsYbqvuGE6p/.4QSyvej3OllP9Fhk95jJmN9XtNJ8cXa";
        System.out.println(passwordEncoder.matches(password, pwd));

        String id = "56598656";

        Long l = Long.parseLong(id);
        System.out.println("Long : " + l);

        String[] zones = TimeZone.getAvailableIDs();
        for(String zone : zones) {
            System.out.println(zone);
        }
        */
        int h = 2;
        System.out.println(++h);
    }

}
