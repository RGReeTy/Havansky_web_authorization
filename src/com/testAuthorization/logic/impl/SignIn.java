package com.testAuthorization.logic.impl;

import com.testAuthorization.logic.Command;
import com.testAuthorization.logic.CommandException;

import javax.servlet.http.HttpServletRequest;

public class SignIn implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = null;

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        DAOFactory daoFactory = DAOFactoryProvider.getFactory(DAOType.SQL);
        UserDAO userDao = daoFactory.getUserDao();
        page = "jsp/signInPage.jsp";

        try {
            User userByLogin = userDao.getUserByLogin(login);
            if (userByLogin.getPassword().equals(password)) {


            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return page;
    }
}
