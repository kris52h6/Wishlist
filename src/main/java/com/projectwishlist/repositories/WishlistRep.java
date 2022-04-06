package com.projectwishlist.repositories;

import com.projectwishlist.models.Wishlist;
import org.springframework.web.context.request.WebRequest;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WishlistRep {
    private final DatabaseRep databaseRep;
    private final ArrayList<String> rows = new ArrayList<String>();
    private final String rowsString;
    private final String table;


    public WishlistRep() {
        declareRowsArray();
        this.databaseRep = new DatabaseRep();
        this.table = "wishlist";
        this.rowsString = rowsToString();
    }

    public String rowsToString(){
        return databaseRep.commaSeperateRows(rows);
    }

    public void declareRowsArray(){
        rows.add("wishlist_id");
        rows.add("wishlist_name");
        rows.add("wishlist_link");
        rows.add("user_id");
    }

    public void prepareWishlist(WebRequest dataFromForm) {
        rows.remove(0);
        String newRows = databaseRep.commaSeperateRows(rows);
        ArrayList<String> data = new ArrayList<>();
        rows.add(0,"wishlist_id"); // resets rows

        data.add(dataFromForm.getParameter("wl-name"));
        data.add(dataFromForm.getParameter("wl-link"));
        data.add(dataFromForm.getParameter("wl-userid"));
        String convertedData = databaseRep.commaSeperateData(data);

        insertWishlistIntoDB(newRows, convertedData);
    }

    public void insertWishlistIntoDB(String rows, String data) {
        databaseRep.insertData("wishlist", rows, data);
    }

    public void deleteWishlist(int id) {
        databaseRep.deleteData("wishlist", "wishlist_id", id);
    }

    public Wishlist getWishListFromDB(int wishlistId) {
        ResultSet resultSet = databaseRep.getDataWhereId(table, rows.get(0), wishlistId);

        String wishlistName = null;
        String wishlistLink = null;
        int userId;
        Wishlist wishlist = null;

        try {
            while(resultSet.next()) {
                wishlistId = resultSet.getInt(rows.get(0));
                wishlistName = resultSet.getString(rows.get(1));
                wishlistLink = resultSet.getString(rows.get(2));
                userId = resultSet.getInt(rows.get(3));

                wishlist = new Wishlist(wishlistId, wishlistName,wishlistLink,userId);
            }
            if (wishlistName != null) {
                return wishlist;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getData() {
        ResultSet resultSet = databaseRep.getResultSet("SELECT * FROM projectwishlist.wishlist");
        try {
            while(resultSet.next()) {
                String col1 = resultSet.getString("wishlist_id");
                String col2 = resultSet.getString("wishlist_name");
                String col3 = resultSet.getString("wishlist_link");
                String col4 = resultSet.getString("user_id");
                System.out.println(col1 + ", " + col2 + ", " + col3 + ", " + col4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "t";
    }

    public ArrayList<Wishlist> getAllWishlistsFromUser(int userId) {
        ArrayList<Wishlist> listOfWishlists = new ArrayList<>();
        ResultSet resultSet = databaseRep.getDataWhereId(table, rows.get(3), userId);

        int wishlistId;
        String wishlistName = null;
        String wishlistLink = null;
        Wishlist wishlist = null;


        try {
            while(resultSet.next()) {
                wishlistId = resultSet.getInt(rows.get(0));
                wishlistName = resultSet.getString(rows.get(1));
                wishlistLink = resultSet.getString(rows.get(2));
                userId = resultSet.getInt(rows.get(3));
                wishlist = new Wishlist(wishlistId,wishlistName,wishlistLink,userId);

                listOfWishlists.add(wishlist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(listOfWishlists);
        return listOfWishlists;
    }

    public static void main(String[] args) {

    }



}
