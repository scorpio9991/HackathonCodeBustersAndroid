/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackethonandriod;

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

    public void connectDataBase() {
        con = null;

        String url = "bossqone.eu";
        String user = "hackathon";
        String password = "hackathon";

        try {

            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQLAccess.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
    }

    public void write_into_entry(int user_id,String location,int category_id, int image_id, String title, String Description) throws SQLException {
        if (this.author == null) {
            System.err.println("Input author?"); //TODO: Better author input
        }
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

    public void newCity(String desc) {
        
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
