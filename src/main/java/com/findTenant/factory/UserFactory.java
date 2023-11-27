package com.findTenant.factory;

import com.findTenant.domain.User;
import com.findTenant.util.Helper;

public class UserFactory {

    public static User createUser(String username, String password, String firstName, String lastName) {
        if (Helper.isNullOrEmpty(username)) {
            System.out.println("Username cannot be null");
            return null;
        }

        if (Helper.isNullOrEmpty(password)) {
            System.out.println("Password cannot be null");
            return null;
        }

        if (Helper.isNullOrEmpty(firstName)) {
            System.out.println("First name cannot be null");
            return null;
        }

        if (Helper.isNullOrEmpty(lastName)) {
            System.out.println("Last name cannot be null");
            return null;
        }

        return new User.Builder()
                .setUsername(username)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();

    }

}
