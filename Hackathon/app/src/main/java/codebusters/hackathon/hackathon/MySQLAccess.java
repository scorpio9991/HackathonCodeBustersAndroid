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

        String url = "bossqone.eu";
        String user = "hackathon";
        String password = "hackathon";
        this.author="Jozko Mrkvicka";

        try {

            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQLAccess.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
    }

    public void write_into_entry(int user_id,String location,int category_id, String path, String title, String Description) throws Exception {
        if (this.author == null) {
            System.err.println("Input author?"); //TODO: Better author input
        }
        int image_id=this.get_image_id(path);
        pst = null;
        pst = con.prepareStatement("INSERT INTO hackathon/entry(user_id,location,category_id,image_id,title,description) VALUES(?,?,?,?,?,?)");
        pst.setInt(1, user_id);
        pst.setString(2, location);
        pst.setInt(3, category_id);
        pst.setInt(4, image_id);
        pst.setString(5, title);
        pst.setString(6, Description);
        pst.executeUpdate();

    }

    public int get_image_id(String path) throws Exception {
        FileUpload parser = new FileUpload();
        int image_id= parser.executeMultipartPost(path);
        return image_id;
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
