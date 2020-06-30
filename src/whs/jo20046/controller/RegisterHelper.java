package whs.jo20046.controller;

import de.whs.ina1.utils.PersistenceUtil;
import whs.jo20046.beans.Userdata;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RegisterHelper extends HelperBase {

    Userdata userdata;
    PersistenceUtil<Userdata> persistenceUtil;

    boolean registerSuccessful;

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

        if (request.getSession().getAttribute("persistenceUtil") == null) {
            persistenceUtil = new PersistenceUtil<>();
            request.getSession().setAttribute("persistenceUtil", persistenceUtil);
        } else {
            persistenceUtil = (PersistenceUtil<Userdata>) request.getSession().getAttribute("persistenceUtil");
        }

        registerSuccessful = setUserCredentials();
        redirect();
    }

    /**
     * Speichert die eingegebenen Anmeldedaten in der Datenbank und im Bean, insofern der Benutzername noch nicht verwendet wird
     * @return true wenn die Daten gespeichert wurden, sonst false
     */
    private boolean setUserCredentials() {

        List<Userdata> duplicate = persistenceUtil.obtainWhere(Userdata.class, "username", request.getParameter("usernameInput"));

        if (duplicate.isEmpty()) {
            request.setAttribute("usernameAlreadyRegistered", "");
            userdata.setUsername(request.getParameter("usernameInput"));
            userdata.setPassword(request.getParameter("password"));
            persistenceUtil.saveOrUpdate(userdata);
            return true;
        } else {
            request.setAttribute("usernameAlreadyRegistered", "<br>Dieser Benutzername wird bereits benutzt. Bitte wähle einen anderen Namen aus.");
            return false;
        }
    }

    private void redirect() throws IOException {

        response.sendRedirect(registerSuccessful ? "whs/jo20046/home.jsp" : "whs/jo20046/registrierung.jsp");
    }
}
