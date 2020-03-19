package main.java.authorizationForm.controller.command;

import main.java.authorizationForm.controller.command.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOut implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "";

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        return page;
    }
}
