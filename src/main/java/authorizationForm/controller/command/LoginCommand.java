package main.java.authorizationForm.controller.command;

import main.java.authorizationForm.bean.User;
import main.java.authorizationForm.controller.command.manager.ConfigurationManager;
import main.java.authorizationForm.controller.command.manager.MessageManager;
import main.java.authorizationForm.service.ServiceException;
import main.java.authorizationForm.service.ServiceFactory;
import main.java.authorizationForm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        User loginBean = new User();
        loginBean.setUsername(login);
        loginBean.setPassword(pass);
        UserService service = ServiceFactory.getInstance().getUserDAO();


        if (request.getParameter("btn_login") != null) {
            try {
                if (service.checkLogin(loginBean)) {
                    request.setAttribute("user", login);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("login", login);

                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
                } else {
                    request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
                }
            } catch (ServiceException e) {
                request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        }
        return page;
    }
}
