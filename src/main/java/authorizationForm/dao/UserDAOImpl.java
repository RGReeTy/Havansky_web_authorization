package main.java.authorizationForm.dao;

import main.java.authorizationForm.bean.User;
import main.java.authorizationForm.dao.connectionPool.ConnectionPool;
import main.java.authorizationForm.dao.connectionPool.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    //private static Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final static String LOGIN = "SELECT * FROM user_test.user WHERE username = ? AND password = ?";
    private final static String INSERT = "INSERT INTO user_test.user(username,password, firstname,lastname) VALUES(?,?,?,?)";

    @Override
    public String addNewUser(User user) throws DAOException {

        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String username = user.getUsername();
        String password = user.getPassword();

        ConnectionPool pool = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = connectionPool.takeConnection();
            pstmt = con.prepareStatement(INSERT);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstname);
            pstmt.setString(4, lastname);
            pstmt.executeUpdate();

            return "SUCCESS REGISTER";

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during pool initializing!", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during inserting operation", e);
        } finally {

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    //logger.log(Level.ERROR, "SQLException in close connection save");
                }
            }
        }

        //  return "FAIL REGISTER";
    }

    @Override
    public String authorizeLogin(User loginUser) throws DAOException {

        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        String dbusername = "";
        String dbpassword = "";
        ConnectionPool pool = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = connectionPool.takeConnection();
            pstmt = con.prepareStatement(LOGIN);
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
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during pool initializing!");
        } catch (SQLException e) {
            throw new DAOException("Exception during sign in operation!");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    //logger.log(Level.ERROR, "SQLException in return connection", e);
                }
            }
        }
        return "WRONG USERNAME AND PASSWORD";
    }
}
