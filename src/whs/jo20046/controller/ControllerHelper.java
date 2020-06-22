package whs.jo20046.controller;

import whs.jo20046.beans.Data;
import whs.jo20046.feedreader.Feedreader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ControllerHelper extends HelperBase {

    protected Data data = new Data();
    private boolean allConnectionsOk;

    public ControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doPost() throws IOException {


        HttpSession session = request.getSession();
        if (session.getAttribute("helper") == null) { // TODO: NullPointer an dieser Stelle wenn man zu oft sumbittet (?)
            request.getSession().setAttribute("helper", this);
        }

        if (request.getSession().getAttribute("Data") == null) {
            request.getSession().setAttribute("Data", data);
        }

        clearPreviousEntries();
        checkURLs();
        if (allConnectionsOk) getFeedContent();
        redirect();
    }

    /**
     * Clear URL-, NotFound-List and Articles-String. (For when the user goes back from the feed page to enter new sources)
     */
    private void clearPreviousEntries() {
        data.clearUrls();
        data.clearNotFound();
        data.clearArticles();
    }

    /**
     * Check if all entered URLs are valid sources. Sets "allConnectionsOk" accordingly.
     */
    private void checkURLs() {

        allConnectionsOk = true;

        data.setSources(Integer.parseInt(request.getParameter("sources")));
        for (int i = 0, sources = data.getSources(); i < sources; i++) {
            data.addUrl(request.getParameter("url" + i));
        }

        for (int i = 0, urlsLength = data.getUrls().size(); i < urlsLength; i++) {
            String urlInput = data.getUrl(i);

            if (!urlInput.startsWith("https://")) {
                urlInput = "https://" + urlInput;
            }

            try {
                URL url = new URL(urlInput);
                HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                huc.setRequestMethod("GET");
                huc.getResponseCode();  // throws Exception if URL not found
                data.removeNotFound(i);
            } catch (Exception e) {
                allConnectionsOk = false;
                data.addNotFound(i);
            }
        }
    }

    /**
     * Calls the Feedreader and saves content to Data.Articles
     */
    private void getFeedContent() {
        Feedreader feedreader = new Feedreader(data.getUrls());
        data.setArticles(feedreader.getRssContent());
    }

    /**
     * Redirects the to either the Output page (all sources were valid) or to the Hint page (1 or more sources didn't return OK earlier)
     * @throws IOException
     */
    private void redirect() throws IOException {

        if (allConnectionsOk) response.sendRedirect("whs/jo20046/ausgabe.jsp");
        else {
            data.setNotFoundUrls("Folgende URLs konnten nicht erreicht werden:<br>");
            for (Integer i : data.getNotFound()) {
                data.setNotFoundUrls(data.getNotFoundUrls() + data.getUrl(i) + "<br>");
            }
            data.clearUrls();
            response.sendRedirect("whs/jo20046/hinweis.jsp");
        }
    }

}
