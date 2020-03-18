package main.java.authorizationForm.controller.command;

import main.java.authorizationForm.controller.command.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response){
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
    }
}
