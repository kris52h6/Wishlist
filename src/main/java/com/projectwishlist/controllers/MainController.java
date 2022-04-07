package com.projectwishlist.controllers;

import com.projectwishlist.models.User;
import com.projectwishlist.repositories.DatabaseRep;
import com.projectwishlist.repositories.UserRep;
import com.projectwishlist.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController
{
    //Controller methods
    @GetMapping("/")
    public String index() {
        return "index";
    }


   @GetMapping("/sign-out")
    public String signOut(HttpSession session){
       session.setAttribute("userId",-1);
       session.setAttribute("loggedInUsername",null);
       return ("redirect:/");
   }
}
