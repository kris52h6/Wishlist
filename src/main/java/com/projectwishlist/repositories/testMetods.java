package com.projectwishlist.repositories;

import java.util.ArrayList;

public class testMetods
{
    public static void main(String[] args)
    {
        DatabaseRep dbr = new DatabaseRep();
        ArrayList <String> test = new ArrayList<>();
        test.add("bil");
        test.add("2000");
        test.add("test");
        System.out.println(dbr.commaSeperateData(test));
        //dbr.deletedata();
    }
}
