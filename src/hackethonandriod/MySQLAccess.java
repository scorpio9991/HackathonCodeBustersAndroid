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

    public void readDataBase() {
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

    public void write_into_database(String name, String Description, String Location, int id) throws SQLException {
        if (this.author == null) {
            System.err.println("Input author?"); //TODO: Better author input
        }
        pst = null;
        pst = con.prepareStatement("INSERT INTO DB VALUES(?,?,?,?)");
        pst.setString(1, name);
        pst.setString(2, Description);
        pst.setString(3, Location);
        pst.setInt(4, id);
        pst.executeUpdate();
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

    public void set_Author(String author) {
        this.author = author;

    }
}



  // You need to close the resultSet
