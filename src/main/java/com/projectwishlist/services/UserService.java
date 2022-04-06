package com.projectwishlist.services;

import com.projectwishlist.models.User;
import com.projectwishlist.repositories.DatabaseRep;
import com.projectwishlist.repositories.UserRep;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    UserRep userRep;

    public UserService() {
        this.userRep = new UserRep();
    }
}
