package com.projectwishlist.controllers;

import com.projectwishlist.models.Item;
import com.projectwishlist.models.Wishlist;
import com.projectwishlist.repositories.WishlistRep;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class WishlistController {
    WishlistRep wishListRep = new WishlistRep();


    @GetMapping("/wltest")
    public String wlTest() {
        return wishListRep.getData();
    }
/*
    @GetMapping("/newTest")
    public String newTest() {
        Wishlist wl = wishListRep.getWishListFromDB();
        System.out.println(wl);
        return wl.toString();
    }

 */
    @GetMapping("/getWishlist")
    public String getWishlist(Model model, HttpSession session) {
        int userId = (int)session.getAttribute("userId");
        if(userId > 0) {
            System.out.println();
            System.out.println(userId);
            ArrayList<Wishlist> list = wishListRep.getAllWishlistsFromUser(userId);
            model.addAttribute("wishlists", list);
            return "wishlist";
        }else {
            return "redirect:/index";
        }
    }

    @GetMapping("/createWishlist")
    public String createWishlist(HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");

        return "create-wishlist";
    }

    @PostMapping("/createNewWishlist")
    public String createNewWishlist(WebRequest dataFromForm) {
        wishListRep.prepareWishlist(dataFromForm);
        return "redirect:/getWishlist";

    }

    @GetMapping("/deleteWishlist")
    public String deleteWishlist(@RequestParam int id) {
        wishListRep.deleteWishlist(id);
        return "redirect:/getWishlist";
    }






}
