package main.java.authorizationForm.controller;

import main.java.authorizationForm.bean.User;
import main.java.authorizationForm.service.ServiceException;
import main.java.authorizationForm.service.ServiceFactory;
import main.java.authorizationForm.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //check button click event not null from register.jsp page button
        if (request.getParameter("btn_register") != null) {
            String username = request.getParameter("txt_username");  //get all textbox name from register.jsp page
            String password = request.getParameter("txt_password");
            String firstname = request.getParameter("txt_firstname");
            String lastname = request.getParameter("txt_lastname");

            //this class contain  seeting up all received values from register.jsp page to setter and getter method for application require effectively
            User user = new User();

            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUsername(username);  //set the all value through registerBean object
            user.setPassword(password);

            UserService service = ServiceFactory.getInstance().getUserDAO();

            //send loginBean object values into authorizeLogin() function in LoginDao class
            String registerValidate = "RegisterErrorMsg";
            try {
                registerValidate = service.registration(user);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            //check calling authorizeRegister() function receive "SUCCESS REGISTER" string message after redirect to index.jsp page
            if (registerValidate.equals("SUCCESS REGISTER")) {
                request.setAttribute("RegisterSuccessMsg", registerValidate); //apply register successfully message "RegisterSuccessMsg"
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); //redirect to index.jsp page
                rd.forward(request, response);
            } else {
                request.setAttribute("RegisterErrorMsg", registerValidate); // apply register error message "RegisterErrorMsg"
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp"); //show error same page register.jsp page
                rd.include(request, response);
            }
        }
    }

}
