package whs.jo20046.controller;

import de.whs.ina1.utils.PersistenceUtil;
import whs.jo20046.beans.Data;
import whs.jo20046.beans.Userdata;
import whs.jo20046.feedreader.Feedreader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderHelper extends HelperBase {

    private Data data;
    private Userdata userdata;
    private PersistenceUtil<Userdata> persistenceUtil;

    public ReaderHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws ServletException, IOException {

        if (request.getSession().getAttribute("checkHelper") == null) {
            request.getSession().setAttribute("checkHelper", this);
        }

        if (request.getSession().getAttribute("userdata") == null) {
            userdata = new Userdata();
            request.getSession().setAttribute("userdata", userdata);
        } else {
            userdata = (Userdata) request.getSession().getAttribute("userdata");
        }

        if (request.getSession().getAttribute("Data") == null) {
            data = new Data();
            request.getSession().setAttribute("Data", data);
        } else {
            data = (Data) request.getSession().getAttribute("Data");
        }

        if (request.getSession().getAttribute("persistenceUtil") == null) {
            persistenceUtil = new PersistenceUtil<>();
            request.getSession().setAttribute("persistenceUtil", persistenceUtil);
        } else {
            persistenceUtil = (PersistenceUtil<Userdata>) request.getSession().getAttribute("persistenceUtil");
        }

        clearArticles();
        ArrayList<String> urls = getUrls();
        getFeedContent(urls);
        response.sendRedirect("whs/jo20046/ausgabe.jsp");
    }

    private ArrayList<String> getUrls() {
        if (userdata.getUrlList() == null) userdata.setUrlList("");
        ArrayList<String> urls = new ArrayList<>(Arrays.asList(userdata.getUrlList().split(";")));
        for (int i = 0, urlsSize = urls.size(); i < urlsSize; i++) {
            urls.set(i, "https://" + urls.get(i));
        }
        return urls;
    }

    private void getFeedContent(ArrayList<String> urls) {
        Feedreader feedreader = new Feedreader(urls);
        data.setArticles(feedreader.getRssContent());
    }


    public void clearArticles() {
        data.setArticles("");
    }
}