package main.java.authorizationForm.service;

import main.java.authorizationForm.bean.User;
import main.java.authorizationForm.dao.DAOException;
import main.java.authorizationForm.dao.DAOFactory;
import main.java.authorizationForm.dao.UserDAO;


public class UserServiceImpl implements UserService {

    @Override
    public String singIn(User user) throws ServiceException {

        UserDAO dao = DAOFactory.getInstance().getUserDAO();

        try {
            return dao.authorizeLogin(user);
        } catch (DAOException e) {
            //throw new ServiceException("Exception during sing in!");
            e.printStackTrace();
        }
        return "Sign In error! Sorry!";
    }

    @Override
    public String registration(User user) throws ServiceException {

        UserDAO dao = DAOFactory.getInstance().getUserDAO();

        try {
            return dao.addNewUser(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception during registration!");
        }
    }
}
