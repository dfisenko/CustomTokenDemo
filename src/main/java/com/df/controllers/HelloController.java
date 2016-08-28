package com.df.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.RolesAllowed;

/**
 * Created by denis on 8/26/2016.
 */
@Controller
public class HelloController {



    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello(){

        return "index";
    }
}
