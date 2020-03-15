package com.jwd.authorizationForm.dao;

import com.jwd.authorizationForm.bean.RegisterBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterDao {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/user";
    String uname = "root";
    String pass = "";

    public String authorizeRegister(RegisterBean registerBean) {

        String firstname = registerBean.getFirstname();
        String lastname = registerBean.getLastname();
        String username = registerBean.getUsername();
        String password = registerBean.getPassword();

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, uname, pass);

            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement("insert into user(firstname,lastname,username,password) values(?,?,?,?)");
            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return "SUCCESS REGISTER";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL REGISTER";
    }
}
