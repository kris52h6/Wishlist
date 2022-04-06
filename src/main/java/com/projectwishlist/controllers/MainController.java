package com.projectwishlist.controllers;

import com.projectwishlist.models.User;
import com.projectwishlist.repositories.DatabaseRep;
import com.projectwishlist.repositories.UserRep;
import com.projectwishlist.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{
    private DatabaseRep databaseRep;

    public MainController() {
        this.databaseRep = new DatabaseRep();;
    }

    //Controller methods
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
