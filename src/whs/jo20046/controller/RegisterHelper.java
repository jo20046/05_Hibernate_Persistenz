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

    boolean successful;

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

        switch (request.getParameter("intent")) {
            case "Login":
                doLogin();
                break;
            case "Registrierung":
                doRegister();
                break;
            default:
                break;
        }

//        successful = setUserCredentials();
//        redirect();
    }

    private void doLogin() throws IOException {
        successful = checkCredentials();
        redirect();
    }

    private void doRegister() throws IOException {
        successful = setUserCredentials();
        redirect();
    }

    /**
     * Speichert die eingegebenen Anmeldedaten in der Datenbank und im Bean, insofern der Benutzername noch nicht verwendet wird
     * @return true wenn die Daten gespeichert wurden, sonst false
     */
    private boolean setUserCredentials() {

        List<Userdata> duplicate = persistenceUtil.obtainWhere(Userdata.class, "username", request.getParameter("usernameInput"));

        if (duplicate.isEmpty()) {
            request.getSession().setAttribute("failureMessage", "");
            userdata.setUsername(request.getParameter("usernameInput"));
            userdata.setPassword(request.getParameter("password"));
            persistenceUtil.saveOrUpdate(userdata);
            return true;
        } else {
            request.getSession().setAttribute("failureMessage", "<br>Dieser Benutzername wird bereits benutzt. Bitte wähle einen anderen Namen aus.");
            return false;
        }
    }

    /**
     * Überprüft die eingegebenen Anmeldedaten
     * @return true, wenn die Anmeldedaten in der Datenbank gefunden wurde, sonst false
     */
    private boolean checkCredentials() {

        String usernameInput = request.getParameter("usernameInput");
        String passwordInput = request.getParameter("password");

        List<Userdata> userdataList = persistenceUtil.obtainWhere(Userdata.class, "username", usernameInput);

        if (userdataList.isEmpty()) {
            request.getSession().setAttribute("failureMessage", "Der eingegebene Benutzername konnte nicht gefunden werden.");
            return false;
        }

        if (!userdataList.get(0).getPassword().equals(passwordInput)) {
            request.getSession().setAttribute("failureMessage", "Das eingegebene Passwort ist falsch.");
            return false;
        }

        copyUserdata(userdataList.get(0));
        request.getSession().setAttribute("failureMessage", "");
        return true;
    }

    private void copyUserdata(Userdata copyFrom) {
        userdata.setId(copyFrom.getId());
        userdata.setUsername(copyFrom.getUsername());
        userdata.setPassword(copyFrom.getPassword());
    }

    private void redirect() throws IOException {
        response.sendRedirect(successful ? "whs/jo20046/home.jsp" : "whs/jo20046/registrierung.jsp?intent=" + request.getParameter("intent"));
    }
}