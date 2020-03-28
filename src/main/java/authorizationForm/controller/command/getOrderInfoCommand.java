package main.java.authorizationForm.controller.command;

import main.java.authorizationForm.bean.Order;
import main.java.authorizationForm.controller.command.manager.ConfigurationManager;
import main.java.authorizationForm.service.ServiceException;
import main.java.authorizationForm.service.ServiceFactory;
import main.java.authorizationForm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class getOrderInfoCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        String page = null;
        HttpSession session = request.getSession(true);

        String login = (String) session.getAttribute(PARAM_NAME_LOGIN);

        UserService service = ServiceFactory.getInstance().getUserDAO();

        try {
            Set<Order> orders = new HashSet<>();
            orders = service.getOrderInfo(login);
            if (!orders.isEmpty()) {
                request.setAttribute("orders", orders);
                session.setAttribute("orders", orders);

                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
            } else {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        } catch (ServiceException e) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
