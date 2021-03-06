package main.java.authorizationForm.controller.command;

import main.java.authorizationForm.bean.User;
import main.java.authorizationForm.controller.command.manager.ConfigurationManager;
import main.java.authorizationForm.controller.command.manager.MessageManager;
import main.java.authorizationForm.service.ServiceException;
import main.java.authorizationForm.service.ServiceFactory;
import main.java.authorizationForm.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "txt_username";
    private static final String PARAM_NAME_PASSWORD = "txt_password";
    private static final String PARAM_NAME_FIRSTNAME = "txt_firstname";
    private static final String PARAM_NAME_LASTNAME = "txt_lastname";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String page = null;
        String username = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String firstname = request.getParameter(PARAM_NAME_FIRSTNAME);
        String lastname = request.getParameter(PARAM_NAME_LASTNAME);

        User user = new User();

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(password);

        HttpSession session = request.getSession();

        UserService service = ServiceFactory.getInstance().getUserDAO();

            String registerValidate = "Register Error";
            try {
                registerValidate = service.registration(user);
            } catch (ServiceException e) {
                request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }

            if (registerValidate.equals("SUCCESS REGISTER")) {
                request.setAttribute("user", username);
                session.setAttribute("isRegistered", registerValidate);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
            } else {
                session.setAttribute("isRegistered", registerValidate);
                request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.REGISTER_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        return page;
    }
}
