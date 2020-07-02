package whs.jo20046.controller;

import de.whs.ina1.utils.PersistenceUtil;
import whs.jo20046.beans.Data;
import whs.jo20046.beans.Userdata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper für eingabe.jsp ("Checkt" die eingegebenen URLs und/oder löscht bestehende)
 */
public class CheckHelper extends HelperBase {

    protected Data data = new Data();
    private Userdata userdata;
    private PersistenceUtil<Userdata> persistenceUtil;
    private boolean allConnectionsOk;

    public CheckHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws IOException {


        HttpSession session = request.getSession();
        if (session.getAttribute("checkHelper") == null) {
            request.getSession().setAttribute("checkHelper", this);
        }

        if (request.getSession().getAttribute("userdata") == null) {
            userdata = new Userdata();
            request.getSession().setAttribute("userdata", userdata);
        } else {
            userdata = (Userdata) request.getSession().getAttribute("userdata");
        }

        if (request.getSession().getAttribute("persistenceUtil") == null) {
            persistenceUtil = new PersistenceUtil<>();
            request.getSession().setAttribute("persistenceUtil", persistenceUtil);
        } else {
            persistenceUtil = (PersistenceUtil<Userdata>) request.getSession().getAttribute("persistenceUtil");
        }


        userdata.setUrlList("");
        persistenceUtil.saveOrUpdate(userdata);
        response.sendRedirect("whs/jo20046/eingabe.jsp");
    }

    @Override
    public void doPost() throws IOException {


        HttpSession session = request.getSession();
        if (session.getAttribute("checkHelper") == null) {
            request.getSession().setAttribute("checkHelper", this);
        }

        if (request.getSession().getAttribute("Data") == null) {
            request.getSession().setAttribute("Data", data);
        }

        if (request.getSession().getAttribute("userdata") == null) {
            userdata = new Userdata();
            request.getSession().setAttribute("userdata", userdata);
        } else {
            userdata = (Userdata) request.getSession().getAttribute("userdata");
        }

        if (request.getSession().getAttribute("persistenceUtil") == null) {
            persistenceUtil = new PersistenceUtil<>();
            request.getSession().setAttribute("persistenceUtil", persistenceUtil);
        } else {
            persistenceUtil = (PersistenceUtil<Userdata>) request.getSession().getAttribute("persistenceUtil");
        }


        clearPreviousEntries();
        checkURLs();
        if (allConnectionsOk) saveNewURLsInBean();
        redirect();

    }

    /**
     * Löscht etwaige Einträge von früheren Besuchen
     */
    private void clearPreviousEntries() {
        clearUrls();
        clearNotFound();
        clearArticles();
    }

    /**
     * Überprüft, ob alle vom Benutzer übergegebenen URLs gültig sind
     */
    private void checkURLs() {

        allConnectionsOk = true;

        String sourcesStr = request.getParameter("sources");
        if (sourcesStr == null || sourcesStr.equals("")) sourcesStr = "0";
        data.setSources(Integer.parseInt(sourcesStr));
        for (int i = 0, sources = data.getSources(); i < sources; i++) {
            addUrl(request.getParameter("url" + i));
        }

        for (int i = 0, urlsLength = data.getUrls().size(); i < urlsLength; i++) {
            String urlInput = getUrl(i);

            if (!urlInput.startsWith("https://")) {
                urlInput = "https://" + urlInput;
            }

            try {
                URL url = new URL(urlInput);
                HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                huc.setRequestMethod("GET");
                huc.getResponseCode();  // throws Exception if URL not found
                removeNotFound(i);
            } catch (Exception e) {
                allConnectionsOk = false;
                addNotFound(i);
            }
        }
    }

    /**
     * Speichert die eingegebenen URLs im Benutzer-Bean
     */
    private void saveNewURLsInBean() {

        String urlList = userdata.getUrlList();
        if (urlList == null) urlList = "";
        List<String> savedURLs = new ArrayList<>(Arrays.asList(urlList.split(";")));
        savedURLs.removeAll(Arrays.asList("", null));

        for (int i = 0, urlsLength = data.getUrls().size(); i < urlsLength; i++) {
            String urlInput = getUrl(i);
            if (!savedURLs.contains(urlInput)) {
                savedURLs.add(urlInput);
            }
        }

        String updatedURLsList = String.join(";", savedURLs);
        userdata.setUrlList(updatedURLsList);
        persistenceUtil.saveOrUpdate(userdata);
    }

    /**
     * Weiterleitung zu home.jsp wenn alle eingegebenen URLs korrekt waren, sonst zu hinweis.jsp
     *
     * @throws IOException
     */
    private void redirect() throws IOException {

        if (allConnectionsOk) response.sendRedirect("whs/jo20046/home.jsp");
        else {
            data.setNotFoundUrls("Folgende URLs konnten nicht erreicht werden:<br>");
            for (Integer i : data.getNotFound()) {
                data.setNotFoundUrls(data.getNotFoundUrls() + getUrl(i) + "<br>");
            }
            clearUrls();
            response.sendRedirect("whs/jo20046/hinweis.jsp");
        }
    }


    public String getUrl(int index) {
        return index >= 0 && index < data.getUrls().size() ? data.getUrls().get(index) : "Index " + index + " out of bounds";
    }

    public void addUrl(String newUrl) {
        data.getUrls().add(newUrl);
    }

    public void clearUrls() {
        data.getUrls().clear();
    }

    public void addNotFound(int newValue) {
        data.getNotFound().add(newValue);
    }

    public void removeNotFound(int val) {
        data.getNotFound().remove(val);
    }

    public void clearNotFound() {
        data.getNotFound().clear();
    }

    public void clearArticles() {
        data.setArticles("");
    }

}
