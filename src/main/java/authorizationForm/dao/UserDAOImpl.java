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

    private final static String INSERT = "INSERT INTO task3.users (login, password) VALUES(?,?)";
    private final static String SELECT = "SELECT login, password FROM task3.users";

    //@Override
    public boolean addNewUser(User user) throws DAOException {

        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        boolean flag = false;

        try {
            pool = ConnectionPool.getInstance();
            pool.poolInitialization();

            connection = pool.takeConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            flag = true;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during pool initializing!");
        } catch (SQLException e) {
            throw new DAOException("Exception during inserting operation");
        } finally {
            if (ps != null)
                closeStatement(ps);
            if (connection != null)
                closeConnection(connection);
            if (pool != null)
                closePool(pool);
        }

        return flag;
    }

    //@Override
    public ArrayList<String> getUserInfo(String info) throws DAOException {

        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ArrayList<String> users = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            pool.poolInitialization();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(SELECT);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(rs.getString(info));
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception during pool initializing!");
        } catch (SQLException e) {
            throw new DAOException("Exception during select operation!");
        } finally {
            if (ps != null)
                closeStatement(ps);
            if (connection != null)
                closeConnection(connection);
            if (pool != null)
                closePool(pool);
        }
        return users;
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
