package main.java.authorizationForm.service;

import main.java.authorizationForm.bean.User;

public interface UserService {

    String singIn(User user) throws ServiceException;

    String registration(User user) throws ServiceException;

    boolean checkLogin(User loginBean) throws ServiceException;
}
