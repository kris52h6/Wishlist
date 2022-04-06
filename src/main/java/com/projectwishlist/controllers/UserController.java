package com.projectwishlist.controllers;

import com.projectwishlist.models.User;
import com.projectwishlist.repositories.UserRep;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class UserController
{

    //public String checkUser(@RequestParam ( value = "username", required = false), @RequestParam (value = "password", ){
    @PostMapping("/checkUser")
    public String checkUser(WebRequest data, HttpSession session){
        UserRep userRep = new UserRep();
        String username = data.getParameter("username");
        String password = data.getParameter("password");

        System.out.println(data.getParameter("username"));
        System.out.println(data.getParameter("password"));


        try{
            int userId = userRep.authenticateUser(username, password);
            if(userId > 0){
                session.setAttribute("userId",userId);
                session.setAttribute("loggedInUsername",username);
                return "redirect:/getWishlist";
            }else {
                return "index";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "fejl";
    }

    @PostMapping("/creatNewUser")
    public String creatNewUser(WebRequest data){
        UserRep userRep = new UserRep();

        String username = data.getParameter("username");
        String password = data.getParameter("password");
        String firstname = data.getParameter("firstname");

        userRep.addNewUser(username, password, firstname);
        return "index";
    }

    @GetMapping("/alextest")
    public String alextest(){
        return "alextest";
    }
}
