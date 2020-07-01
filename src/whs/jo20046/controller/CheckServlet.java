package whs.jo20046.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Check")
public class CheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckHelper checkHelper;
        if (request.getSession().getAttribute("checkHelper") == null) {
            checkHelper = new CheckHelper(request, response);
        } else {
            checkHelper = (CheckHelper) request.getSession().getAttribute("checkHelper");
        }
        checkHelper.doGet();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CheckHelper checkHelper;
        if (request.getSession().getAttribute("checkHelper") == null) {
            checkHelper = new CheckHelper(request, response);
        } else {
            checkHelper = (CheckHelper) request.getSession().getAttribute("checkHelper");
        }
        checkHelper.doPost();
    }
}
