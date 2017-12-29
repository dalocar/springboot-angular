package com.carrascolimited.springboot.util;

public class UserUtil {

    private UserUtil() {

    }

    public static String convertToFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}