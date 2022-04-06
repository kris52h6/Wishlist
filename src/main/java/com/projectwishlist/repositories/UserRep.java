package com.projectwishlist.repositories;

import com.projectwishlist.models.User;
import org.springframework.web.context.request.WebRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRep {
     private final DatabaseRep databaseRep;
     private final String table = "user";
     private final ArrayList<String> rows = new ArrayList<String>();




     public UserRep() {
         declareRows();
         this.databaseRep = new DatabaseRep();

     }

     public void declareRows(){
        rows.add("user_id");
        rows.add("user_username");
        rows.add("user_password");
        rows.add("user_firstname");
     }

     public User getUserFromDb(int userId) throws SQLException {
        ResultSet resultSet = databaseRep.getDataWhereId(table, rows.get(0), userId);

        String username = null;
        String password = null;
        String firstName = null;
        User user = null;

        while (resultSet.next()) {
            username = resultSet.getString(rows.get(1));
            password = resultSet.getString(rows.get(2));
            firstName = resultSet.getString(rows.get(3));
        }
        if(username != null) {
            user = new User(userId, username, password, firstName);

        }
        return user;
      }


     public int authenticateUser(String usernameInput, String passwordInput) throws SQLException {
         String sql = "SELECT * FROM projectwishlist." + table +
                 " WHERE " + rows.get(1) + " = " + "'" + usernameInput + "' AND " + rows.get(2) + " = '" + passwordInput + "' ;";

         System.out.println(sql);

             ResultSet resultSet = databaseRep.getResultSet(sql);

         int userId = -1;
         String username = null;
         String password = null;
         int result;

         while (resultSet.next()) {
             userId = resultSet.getInt(rows.get(0));
             username = resultSet.getString(rows.get(1));
             password = resultSet.getString(rows.get(2));
         }

         if(username.equals(usernameInput) && password.equals(passwordInput)){
             result = userId;

         }else {
             result = -1;
         }

         return result;
     }

     public void addNewUser(String username, String password, String firstname){
         ArrayList<String> data = new ArrayList<>();
         data.add(username);
         data.add(password);
         data.add(firstname);
         String dataString = databaseRep.commaSeperateData(data);
         rows.remove(0);
         String rowsString = databaseRep.commaSeperateRows(rows);
         rows.add(0, "user_id");
         databaseRep.insertData(table, rowsString, dataString);
     }
}
