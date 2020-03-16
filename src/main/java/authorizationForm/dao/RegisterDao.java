package main.java.authorizationForm.dao;

import main.java.authorizationForm.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterDao {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/user_test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    String uname = "root";
    String pass = "0000";

    public String authorizeRegister(User user) {

        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String username = user.getUsername();
        String password = user.getPassword();

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, uname, pass);

            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement("insert into user_test.user(username,password, firstname,lastname) values(?,?,?,?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstname);
            pstmt.setString(4, lastname);
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
