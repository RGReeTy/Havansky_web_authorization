package main.java.authorizationForm.dao;

import main.java.authorizationForm.bean.User;

public interface UserDAO {

    String addNewUser(User user) throws DAOException;

    String authorizeLogin(User loginUser) throws DAOException;

    //public ArrayList<String> getUserInfo(String info) throws DAOException;
}
