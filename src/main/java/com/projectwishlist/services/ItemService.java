package com.projectwishlist.services;

import com.projectwishlist.models.Item;
import com.projectwishlist.repositories.ItemRep;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

public class ItemService {
    private ItemRep itemRep = null;

    public ItemService() {
        this.itemRep = new ItemRep();
    }

    public void deleteItem(@RequestParam int id){
        itemRep.deleteItem(id);
    }

    public void updateItem(@RequestParam int id, Model model){

        Item item = itemRep.getItemFromDb(id);
        ArrayList<String> itemInfo = new ArrayList<String>();
        String itemId = String.valueOf(item.getId());
        String itemName = item.getName();
        String itemPrice = String.valueOf(item.getPrice());
        String itemLink = item.getLink();

        itemInfo.add(itemId);
        itemInfo.add(itemName);
        itemInfo.add(itemPrice);
        itemInfo.add(itemLink);

        model.addAttribute("itemInfo", itemInfo);
    }

    public void updateItemDb(WebRequest formData){
        int id = Integer.parseInt(formData.getParameter("id"));
        String name = formData.getParameter("name");
        int price = Integer.parseInt(formData.getParameter("price"));
        String link = formData.getParameter("link");

        ArrayList<String> rows = new ArrayList<String>();
        ArrayList<String> data = new ArrayList<String>();


        Item itemBefore = itemRep.getItemFromDb(id);

        if(!name.equals(itemBefore.getName())){
            rows.add("item_name");
            data.add(name);
        }
        if(price != itemBefore.getPrice()){
            rows.add("item_price");
            data.add(formData.getParameter("price"));
        }
        assert link != null;
        if(!link.equals(itemBefore.getLink())){
            rows.add("item_link");
            data.add(link);
        }

        String rowId = "item_id";
        int dataId = id;
        if(rows.size() != 0){
            itemRep.updateItem(rows,data,rowId,dataId);
        }
    }

    public void insertItemDb(WebRequest formData) {
        ArrayList<String> data = new ArrayList<>();
        data.add(formData.getParameter("item-name"));
        data.add(formData.getParameter("item-price"));
        data.add(formData.getParameter("item-link"));
        data.add(formData.getParameter("wishlistId"));
        itemRep.insertToDb(data);
    }
}
