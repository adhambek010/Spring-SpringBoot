package com.springboot.myfirstwebapplication.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String userName, String pwd){
        boolean isValidUsername = userName.equalsIgnoreCase("Adham");
        boolean isValidPassword = pwd.equalsIgnoreCase(("2301"));
        return isValidUsername && isValidPassword;
    }
}
