package com.nl.tracker.utils;

/**
 * Created by levin1 on 1/12/2017.
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class QuickPasswordEncodingGenerator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String password = "dctm";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));

        String pwd = "$2a$10$zrN/T6iWZmsYbqvuGE6p/.4QSyvej3OllP9Fhk95jJmN9XtNJ8cXa";
        System.out.println(passwordEncoder.matches(password, pwd));
    }

}
