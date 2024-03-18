/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.utils;

import com.hyperbeast.entity.SanPham;
import com.hyperbeast.model.hoaDonModel;
import com.hyperbeast.model.sanPhamModel;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Admin
 */
public class DBconnect {
    public static final String HOSTNAME = "LAPTOP-HDVM3L64";
    public static final String PORT = "1433";
    public static final String DBNAME = "HyperBeastTest";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "knha1207";
    
    public static Connection getConnection() {

        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DBNAME + ";encrypt=true;trustServerCertificate=true;";
        try {
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } 
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
