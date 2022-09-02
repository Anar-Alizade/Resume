package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDao {

    public Connection connect() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            com.mysql.cj.jdbc.Driver s;
            String url = "jdbc:mysql://localhost:3306/myfirtsresume";
            String userName = "root";
            String password = "12345";
            connect = DriverManager.getConnection(url, userName, password);
            System.out.println(connect.getClass().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connect;
    }

}
