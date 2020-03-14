package com.testAuthorization.logic;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request) throws CommandException;
}
