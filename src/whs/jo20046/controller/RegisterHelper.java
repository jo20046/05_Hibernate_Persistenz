package whs.jo20046.controller;

import whs.jo20046.beans.Userdata;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterHelper extends HelperBase {

    Userdata userdata;

    public RegisterHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doPost() throws ServletException, IOException {

        if (request.getSession().getAttribute("registerHelper") == null) {
            request.getSession().setAttribute("registerHelper", this);
        }

        if (request.getSession().getAttribute("userdata") == null) {
            userdata = new Userdata();
            request.getSession().setAttribute("userdata", userdata);
        } else {
            userdata = (Userdata) request.getSession().getAttribute("userdata");
        }

        setUserCredentials();
        int i = 0;
    }

    private void setUserCredentials() {

        userdata.setUsername(request.getParameter("usernameInput"));
        userdata.setPassword(request.getParameter("password"));
    }
}
