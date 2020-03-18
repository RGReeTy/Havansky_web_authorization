package main.java.authorizationForm.controller.command;

import main.java.authorizationForm.bean.User;
import main.java.authorizationForm.controller.command.manager.ConfigurationManager;
import main.java.authorizationForm.controller.command.manager.MessageManager;
import main.java.authorizationForm.service.ServiceFactory;
import main.java.authorizationForm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        String page = null;
//извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        User loginBean = new User();
        loginBean.setUsername(login); //set username through loginBean object
        loginBean.setPassword(pass); //set password through loginBean object
        UserService service = ServiceFactory.getInstance().getUserDAO();

//проверка логина и пароля
        if (request.getParameter("btn_login") != null) {
            if (service.checkLogin(loginBean)) {
//                HttpSession session = request.getSession(); //session is created
//                session.setAttribute("login", loginBean.getUsername()); //session name is "login" and  store username in "getUsername()" get through loginBean object
//                RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp"); //redirect to welcome.jsp page
//                rd.forward(request, response);
                request.setAttribute("user", login);
//определение пути к main.jsp
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
            } else {
                request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        }
        return page;
    }


}
