package com.project.Group1.Dao;


import java.sql.*;

public class Database implements IDatabase {

    private static Database db = null;

    private Database() {

    }

    @Override
    public Connection getConnection(String connectionURL, String username, String password) {
        // TODO Auto-generated method stub
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, username, password);
            con.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static IDatabase getInstance() {
        if (null == db) {
            db = new Database();
            return db;
        }
        return db;
    }

}
