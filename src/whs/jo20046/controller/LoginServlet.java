package whs.jo20046.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Deprecated
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginHelper loginHelper;
        if (req.getSession().getAttribute("loginHelper") == null) {
            loginHelper = new LoginHelper(req, resp);
        } else {
            loginHelper = (LoginHelper) req.getSession().getAttribute("loginHelper");
        }
        loginHelper.doGet();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
