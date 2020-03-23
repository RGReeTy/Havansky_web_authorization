package main.java.authorizationForm.service;

import main.java.authorizationForm.bean.Order;
import main.java.authorizationForm.bean.User;

import java.util.Set;

public interface UserService {

    String singIn(User user) throws ServiceException;

    String registration(User user) throws ServiceException;

    boolean checkLogin(User loginBean) throws ServiceException;

    Set<Order> getOrderInfo(String login) throws ServiceException;
}
