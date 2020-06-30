package whs.jo20046.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RegisterHelper registerHelper;
        if (req.getSession().getAttribute("registerHelper") == null) {
            registerHelper = new RegisterHelper(req, resp);
        } else {
            registerHelper = (RegisterHelper) req.getSession().getAttribute("registerHelper");
        }
        registerHelper.doPost();
    }
}
