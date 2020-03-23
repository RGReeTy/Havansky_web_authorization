package main.java.authorizationForm.dao;

import main.java.authorizationForm.bean.Order;
import main.java.authorizationForm.bean.User;
import main.java.authorizationForm.dao.connectionPool.ConnectionPool;
import main.java.authorizationForm.dao.connectionPool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDAOImpl implements UserDAO {

    //private static Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private final static String LOGIN = "SELECT * FROM user_test.user WHERE username = ? AND password = ?";
    private final static String INSERT = "INSERT INTO user_test.user(username,password, firstname,lastname) VALUES(?,?,?,?)";
    private final static String GET_INFO = "SELECT user_test.orders.id, user_test.orders.CreatedAt, user_test.orders.Count" +
            " FROM user_test.orders INNER JOIN orders ON user_test.user.username=user_test.orders.Customer_nickname " +
            "WHERE user_test.orders.Customer_nickname=?";

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

    @Override
    public Set<Order> getUserInfo(String login) throws DAOException {
        ConnectionPool pool = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        Set<Order> orders = new HashSet<Order>();

        try {
            con = connectionPool.takeConnection();
            pstmt = con.prepareStatement(GET_INFO);
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = parseOrder(rs);
                orders.add(order);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during pool initializing!");
        } catch (SQLException e) {
            throw new DAOException("Exception during loading orders info operation!");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    //logger.log(Level.ERROR, "SQLException in return connection", e);
                }
            }
        }
        return orders;
    }

    private Order parseOrder(ResultSet resultSet) throws SQLException {
        int idOrder = resultSet.getInt("id");
        String dateOrder = resultSet.getString("CreatedAt");
        int countOrder = resultSet.getInt("Count");
        return new Order(idOrder, dateOrder, countOrder);

    }


}
