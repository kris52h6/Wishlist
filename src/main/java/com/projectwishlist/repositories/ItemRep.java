package com.projectwishlist.repositories;

import com.projectwishlist.models.Item;
import com.projectwishlist.models.Wishlist;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemRep {

    private DatabaseRep databaseRep = null;
    private final String table = "item";
    private final ArrayList<String> rows = new ArrayList<>();
    private String rowsString = null;

    public ItemRep() {
        declareRowsArray();
        this.databaseRep = new DatabaseRep();
        this.rowsString = rowsToString();
    }

    public void declareRowsArray(){
        rows.add("item_id");
        rows.add("item_name");
        rows.add("item_price");
        rows.add("item_link");
        rows.add("wishlist_id");
    }
    public String rowsToString(){
        return databaseRep.commaSeperateRows(rows);
    }

    public void insertToDb(ArrayList<String> data) {
        String dataString = databaseRep.commaSeperateData(data);
        databaseRep.insertData(table, databaseRep.removeIdFromRow(rowsString), dataString);
    }

    public void deleteItem(int itemId){
        databaseRep.deleteData(table, rows.get(0), itemId);
    }

    public ArrayList<Item> getItemsFromWishlist(int wishlistId){
        rowsString = databaseRep.commaSeperateRowsWithTablename(rows, table);
        String sql = "SELECT " + rowsString + " FROM projectwishlist." + table
                + " WHERE wishlist_id = " + wishlistId + " ;";

        /*String sql = "SELECT " + rowsString + " FROM projectwishlist." + table + " INNER JOIN projectwishlist.wishlistitems ON item.item_id = wishlistitems.item_id WHERE wishlistitems.wishlist_id = " + wishlistId + " ;";*/
        ResultSet resultSet = databaseRep.getResultSet(sql);
        System.out.println(sql);
        ArrayList<Item> items = new ArrayList<>();
        int item_id = -1;
        String item_name = null;
        int item_price = -1;
        String item_link = null;
        Item item = null;

        try {
            while(resultSet.next()) {
                item_id = resultSet.getInt(rows.get(0));
                item_name = resultSet.getString(rows.get(1));
                item_price = resultSet.getInt(rows.get(2));
                item_link = resultSet.getString(rows.get(3));
                item = new Item(item_id, item_name, item_price, item_link);
                items.add(item);
                item = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getItemFromDb(int itemId) {
        ResultSet resultSet = databaseRep.getDataWhereId(table, rows.get(0), itemId);

        String itemName = null;
        int itemPrice;
        String itemLink = null;
        Item item = null;

        try {
            while(resultSet.next()) {
                itemId = resultSet.getInt(rows.get(0));
                itemName = resultSet.getString(rows.get(1));
                itemPrice = resultSet.getInt(rows.get(2));
                itemLink = resultSet.getString(rows.get(3));

                item = new Item(itemId, itemName,itemPrice,itemLink);
            }
            if (itemName != null) {
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateItem(ArrayList<String> rows, ArrayList<String> data, String rowId, int dataId){
        databaseRep.updateData(table,rows, data, rowId, dataId);
    }



    public static void main(String[] args) {
        ItemRep itemRep = new ItemRep();
        ArrayList<Item> items = itemRep.getItemsFromWishlist(2);

        System.out.println("Wishlist nr: " + 2);
        for (Item item : items){
            System.out.println(item);
        }
    }
}
