package whs.jo20046.controller;

import de.whs.ina1.utils.PersistenceUtil;
import whs.jo20046.beans.Userdata;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Deprecated
public class LoginHelper extends HelperBase {

    Userdata userdata;
    PersistenceUtil<Userdata> persistenceUtil;

    boolean loginSuccessful;

    public LoginHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws ServletException, IOException {

        if (request.getSession().getAttribute("loginHelper") == null) {
            request.getSession().setAttribute("loginHelper", this);
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

        loginSuccessful = checkCredentials();
        redirect();
    }

    @Override
    public void doPost() throws ServletException, IOException {
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
            request.getSession().setAttribute("loginFailedCause", "Der eingegebene Benutzername konnte nicht gefunden werden.");
            return false;
        }

        if (!userdataList.get(0).getPassword().equals(passwordInput)) {
            request.getSession().setAttribute("loginFailedCause", "Das eingegebene Passwort ist falsch.");
            return false;
        }

        copyUserdata(userdataList.get(0));
        request.getSession().setAttribute("loginFailedCause", "");
        return true;
    }

    private void copyUserdata(Userdata copyFrom) {
        userdata.setId(copyFrom.getId());
        userdata.setUsername(copyFrom.getUsername());
        userdata.setPassword(copyFrom.getPassword());
    }

    private void redirect() throws IOException {
        response.sendRedirect(loginSuccessful ? "/whs/jo20046/home.jsp" : "/whs/jo20046/login.jsp");
    }
}
