package main.java.authorizationForm.dao;

import main.java.authorizationForm.bean.User;
import main.java.authorizationForm.dao.connectionPool.ConnectionPool;
import main.java.authorizationForm.dao.connectionPool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

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
            pool = ConnectionPool.getInstance();
            pool.poolInitialization();

            con = pool.takeConnection();
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
            if (pstmt != null) {
                closeStatement(pstmt);
            }
            if (con != null) {
                closeConnection(con);
            }
            if (pool != null) {
                closePool(pool);
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
            pool = ConnectionPool.getInstance();
            pool.poolInitialization();

            con = pool.takeConnection();
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
            if (pstmt != null)
                closeStatement(pstmt);
            if (con != null)
                closeConnection(con);
            if (pool != null)
                closePool(pool);
        }
        return "WRONG USERNAME AND PASSWORD";
    }


    private static void closeStatement(PreparedStatement ps) throws DAOException {
        try {
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Exception during statement closing");
        }
    }

    private static void closeConnection(Connection connection) throws DAOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Exception during connection closing");
        }
    }

    private static void closePool(ConnectionPool pool) throws DAOException {
        try {
            pool.closeAllConnections();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during pool closing");
        }
    }
}
