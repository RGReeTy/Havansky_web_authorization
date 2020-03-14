package com.testAuthorization.controller;

import com.testAuthorization.logic.Command;
import com.testAuthorization.logic.CommandException;
import com.testAuthorization.logic.CommandProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(RequestParamName.COMMAND_NAME);
        Command command = CommandProvider.getInstance().getCommand(commandName.toUpperCase());

        String page = null;
        try {
            page = command.execute(req);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
        if (requestDispatcher != null) {
            requestDispatcher.forward(req, resp);
        } else {
            errorMessage(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private void errorMessage(HttpServletResponse response) {
        response.setContentType("text/html");
        try {
            response.getWriter().write("Ooops, something goes wrong!");
        } catch (IOException e) {
            System.out.println("Ooops, something goes wrong!");
            e.printStackTrace();
        }

    }
}
