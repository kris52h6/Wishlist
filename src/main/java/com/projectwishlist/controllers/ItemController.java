package com.projectwishlist.controllers;

import com.projectwishlist.models.Item;
import com.projectwishlist.repositories.ItemRep;
import com.projectwishlist.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class ItemController {
    ItemRep itemRep = new ItemRep();
    ItemService itemService = new ItemService();

    @GetMapping("/showWishlistItems")
    public String showWishlistItems(Model model) {
        ArrayList<Item> itemList = itemRep.getItemsFromWishlist(1);
        model.addAttribute("items", itemList);
        return "wishlist-items";
    }

    @GetMapping("/wishlist")
    public String wishlist(@RequestParam int id, Model model) {
        ArrayList<Item> itemList = itemRep.getItemsFromWishlist(id);
        model.addAttribute("items", itemList);
        model.addAttribute("wishlistId", id);
        return "wishlist-items";
    }

    @GetMapping("/wishlist-update-item")
    public String updateItem(@RequestParam int id, Model model){
        itemService.updateItem(id,model);
        return "item-update";
    }

    @GetMapping("/delete-item")
    public String deleteItem(@RequestParam int id){
        System.out.println("test delete");
        itemService.deleteItem(id);
        return "redirect:/getWishlist";
    }

    @PostMapping("/update-item")
    public String itemUpdate(WebRequest formData){
        itemService.updateItemDb(formData);
        return "redirect:/wishlist?id=1";
    }

    @PostMapping("/addItemToWishlist")
    public String addItemToWishList(WebRequest formData) {
        itemService.insertItemDb(formData);
        int id = Integer.parseInt(formData.getParameter("wishlistId"));
        return "redirect:/wishlist?id=" + id;
    }

}
