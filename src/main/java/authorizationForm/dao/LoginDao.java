package main.java.authorizationForm.dao;

import main.java.authorizationForm.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/user_test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    String uname = "root";
    String pass = "0000";


    public String authorizeLogin(User loginBean) {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();

        String dbusername = "";
        String dbpassword = "";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, uname, pass);

            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement("select * from user_test.user where username=? and password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                dbusername = rs.getString("username");
                dbpassword = rs.getString("password");

                if (username.equals(dbusername) && password.equals(dbpassword)) {
                    return "SUCCESS LOGIN";
                }
            }

            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "WRONG USERNAME AND PASSWORD";
    }
}
