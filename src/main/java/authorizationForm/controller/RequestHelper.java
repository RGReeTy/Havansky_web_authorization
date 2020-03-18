package main.java.authorizationForm.controller;

import main.java.authorizationForm.controller.command.Command;
import main.java.authorizationForm.controller.command.LoginCommand;
import main.java.authorizationForm.controller.command.NoCommand;
import main.java.authorizationForm.controller.command.RegisterCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {

    private static RequestHelper instance = null;

    HashMap<String, Command> commands = new HashMap<String, Command>();

    private RequestHelper() {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
    }

    public Command getCommand(HttpServletRequest request) {
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
