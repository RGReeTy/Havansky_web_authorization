package main.java.authorizationForm.controller;

import main.java.authorizationForm.controller.command.Command;
import main.java.authorizationForm.controller.command.manager.ConfigurationManager;
import main.java.authorizationForm.controller.command.manager.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private String ATTRIBUT_ERROR_MESSAGE = "errorMessage";

    private RequestHelper requestHelper = RequestHelper.getInstance();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = null;
        try {

            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);

        } catch (ServletException e) {
            request.setAttribute(ATTRIBUT_ERROR_MESSAGE, MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
            request.setAttribute(ATTRIBUT_ERROR_MESSAGE, MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                request.setAttribute(ATTRIBUT_ERROR_MESSAGE, MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            } catch (IOException e) {
                request.setAttribute(ATTRIBUT_ERROR_MESSAGE, MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            }
        } else {
            page = ConfigurationManager.getInstance().getProperty(MessageManager.UNKNOWN_ERROR_MESSAGE);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
