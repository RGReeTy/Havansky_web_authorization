package main.java.authorizationForm.controller;

import main.java.authorizationForm.controller.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {

    private static RequestHelper instance = null;

    private HashMap<String, Command> commands = new HashMap<String, Command>();

    private RequestHelper() {
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegisterCommand());
        commands.put("signout", new SignOut());
    }

    Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
