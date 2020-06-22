package whs.jo20046.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Check")
public class Check extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ControllerHelper helper;
        if (request.getSession().getAttribute("helper") == null) {
            helper = new ControllerHelper(request, response);
        } else {
            helper = (ControllerHelper) request.getSession().getAttribute("helper");
        }
        helper.doPost();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
