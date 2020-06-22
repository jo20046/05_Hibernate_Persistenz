package whs.jo20046.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelperBase {

    protected final HttpServletRequest request;
    protected final HttpServletResponse response;

    public HelperBase(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void doGet() throws ServletException, IOException {
    }

    public void doPost() throws ServletException, IOException {
    }
}
