package com.jwd.authorizationForm.controller;

import com.jwd.authorizationForm.bean.RegisterBean;
import com.jwd.authorizationForm.dao.RegisterDao;

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
            String firstname = request.getParameter("txt_firstname");
            String lastname = request.getParameter("txt_lastname");
            String username = request.getParameter("txt_username");  //get all textbox name from register.jsp page
            String password = request.getParameter("txt_password");

            //this class contain  seeting up all received values from register.jsp page to setter and getter method for application require effectively
            RegisterBean registerBean = new RegisterBean();

            registerBean.setFirstname(firstname);
            registerBean.setLastname(lastname);
            registerBean.setUsername(username);  //set the all value through registerBean object
            registerBean.setPassword(password);

            RegisterDao registerdao = new RegisterDao(); //this class contain main logic to perform function calling and database operation

            String registerValidate = registerdao.authorizeRegister(registerBean); //send registerBean object values into authorizeRegister() function in RegisterDao class

            //check calling authorizeRegister() function receive "SUCCESS REGISTER" string message after redirect to index.jsp page
            if (registerValidate.equals("SUCCESS REGISTER")) {
                request.setAttribute("RegisterSuccessMsg", registerValidate); //apply register successfully message "RegiseterSuccessMsg"
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); //redirect to index.jsp page
                rd.forward(request, response);
            } else {
                request.setAttribute("RegisterErrorMsg", registerValidate); // apply register error message "RegiseterErrorMsg"
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp"); //show error same page register.jsp page
                rd.include(request, response);
            }
        }
    }

}
