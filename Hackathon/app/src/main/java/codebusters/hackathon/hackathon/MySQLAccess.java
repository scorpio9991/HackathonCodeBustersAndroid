/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codebusters.hackathon.hackathon;

/**
 *
 * @author Ján
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLAccess {

    private PreparedStatement pst;
    private Connection con;
    private String author;

    public void startConnection() {
        con = null;
     //   com.mysql.jdbc.Driver d = null;

       //     try {
           //     d = new com.mysql.jdbc.Driver();
       //     } catch (SQLException e) {
       //         e.printStackTrace();
       //         System.err.println("UNABLE TO LOAD DRIVER");
       //     }
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
        }


        String url = "jdbc:mysql://bossqone.eu/hackathon";
        String user = "hackathon";
        String password = "hackathon";
        this.author="Jozko Mrkvicka";

        try {

            con = DriverManager.getConnection(url, user, password);
            System.err.println("CONNECTED SUCCESFULLY");
        } catch (SQLException ex) {
// handle any errors
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());

        }

    }


    public void write_into_entry(int user_id,String location,int category_id, String path, String title, String Description) throws Exception {

        if (this.author == null) {
            System.err.println("Input author?"); //TODO: Better author input
        }

        ImageUpload upload = new ImageUpload();
        int image_id = upload.execute(path).get();

        pst = null;
        pst = con.prepareStatement("INSERT INTO entry(user_id,location,category_id,image_id,title,description) VALUES(?,?,?,?,?,?)");
        pst.setInt(1, user_id);
        System.err.println("USER ID: " + user_id);
        pst.setString(2, location);
        System.err.println("LOCATION: " + location);
        pst.setInt(3, category_id);
        System.err.println("category_id: " + category_id);
        pst.setInt(4, image_id);
        System.err.println("image_id: " + image_id);
        pst.setString(5, title);
        System.err.println("Title: " + title);
        pst.setString(6, Description);
        System.err.println("DESCRIPTION: "+Description);
        pst.executeUpdate();
        System.err.println("SUCCESFULLY SENDED TO DB");
    }

    public void set_Author(String author) {
        this.author = author;

    }

    public void closeConnection() {
        try {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQLAccess.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}



  // You need to close the resultSet
