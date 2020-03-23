package main.java.authorizationForm.dao;

import main.java.authorizationForm.bean.Order;
import main.java.authorizationForm.bean.User;

import java.util.Set;

public interface UserDAO {

    String addNewUser(User user) throws DAOException;

    String authorizeLogin(User loginUser) throws DAOException;

    Set<Order> getUserInfo(String info) throws DAOException;
}
