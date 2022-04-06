package com.projectwishlist.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class DatabaseRep {
    private Connection connection = null;
    private Statement statement;
    private ResultSet rs;
    private String sqlString;
    private Connection con;

    public DatabaseRep() {
        this.connection = DatabaseConnection.getConnection();
        this.statement = getStatement();
    }

    private Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getResultSet(String sql) {
        try {
            return Objects.requireNonNull(getStatement()).executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getDataWhereId(String table, String rowIdName, int dataId){

        String sql = "SELECT * FROM projectwishlist." + table +
                " WHERE " + rowIdName + "='" + dataId + "';";
        System.out.println(sql);
        return getResultSet(sql);
    }

    public void insertData(String table, String rows, String data) {
        try
        {
            sqlString = "INSERT INTO projectwishlist." + table + "(" + rows + ")" +
                    "VALUES(" + data + ");";
            statement.executeUpdate(sqlString);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println(sqlString);
    }

    public void deleteData(String table,String rowId, int id){
        try
        {
            sqlString = "DELETE FROM projectwishlist." + table +
                    " WHERE (" + rowId + "='" + id + "');";
            statement.executeUpdate(sqlString);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String removeIdFromRow(String rowString){
        return rowString.substring(rowString.indexOf(',') + 1);
    }

    public String commaSeperateData(ArrayList<String> data){
        int size = data.size();
        int count = 1;
        String result = "";
        if (size > 0 ){
            for (String input : data){
                if (size == count){
                    result += "'" + input + "'";
                } else {
                    result += "'" + input + "'" + ", ";
                }
                count ++;
            }

        }
        return result;
    }

    public String commaSeperateRows(ArrayList<String> data){
        int size = data.size();
        int count = 1;
        String result = "";
        if (size > 0 ){
            for (String input : data){
                if (size == count){
                    result += input ;
                } else {
                    result += input + ", ";
                }
                count ++;
            }

        }
        return result;
    }


    public String commaSeperateRowsWithTablename(ArrayList<String> data, String table){
        int size = data.size();
        int count = 1;
        String result = "";
        if (size > 0 ){
            for (String input : data){
                if (size == count){
                    result += table + "." + input ;
                } else {
                    result += table + "." + input + ", ";
                }
                count ++;
            }

        }
        return result;
    }

    public void updateData(String table, ArrayList<String> rows, ArrayList<String> data, String rowId, int dataId){

        String sql = "";
        String rowsAndData = updateDataString(rows, data);
        if(rows.size() == data.size()) {
            sql = "UPDATE " + "projectwishlist." + table +
                    " SET " + rowsAndData +
                    " WHERE " + rowId + " = " + dataId;
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    public String updateDataString(ArrayList<String>rows, ArrayList<String> data){
        String result = "";

        if(rows.size() == data.size()){
            for (int i = 0; i < rows.size(); i++) {
                String rowField = rows.get(i);
                String dataField = data.get(i);

                if(i == (rows.size())-1){
                    result+= rowField + " = ";
                    result+= "'" + dataField + "'";
                }else {
                    result+= rowField + " = ";
                    result+= "'" + dataField + "'";
                    result+= ", ";
                }
            }

        }

        return result;
    }

/*
    public static void main(String[] args) {
        DatabaseRep databaseRep = new DatabaseRep();

        ArrayList<String> rows = new ArrayList<String>();
        ArrayList<String> data = new ArrayList<String>();

        rows.add("item_name");
        rows.add("item_price");

        data.add("Lagkage");
        data.add("200");

        databaseRep.updateData("item", rows, data,"item_id", 1);
    }
 */

}