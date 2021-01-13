/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abojawdat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed A Mahjoub
 */
public class Database {

    static Connection con;
    static PreparedStatement command;
    static ResultSet rst;
    static private int counter;

    public static void connect() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://data.accdb");
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void add(String name, String phone, String Mno, String quantity, String price, String adate,String day, String time, String notes) {
        try {
            command = con.prepareStatement("insert into adata(aname,phone,Mno,quantity,price,adate,day,time,notes)values('" + name + "','" + phone + "','" + Mno + "','" + quantity + "','" + price + "',#" + adate + "#,'"+day+"',#" + time + "#,'" + notes + "')");
            command.execute();
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getLastIndex() {
        try {
            command = con.prepareStatement("select id from adata");
            rst = command.executeQuery();
            while (rst.next()) {
                setCounter(rst.getInt(1)+1);
            }
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   static DefaultTableModel model;
    public static void getdata() throws ClassNotFoundException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://data.accdb");
            command = con.prepareStatement("select * from adata");
            rst = command.executeQuery();
            while (rst.next()) {
                model.insertRow(model.getRowCount(), new Object[]
                {rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)
                        ,rst.getString(7),rst.getString(8),rst.getString(9)});
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public static void setCounter(int counter) {
        Database.counter = counter;
    }
}
