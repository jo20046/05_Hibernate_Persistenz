package whs.jo20046.beans;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

/**
 * Zentrales Benutzer-Bean: Benutzername, Passwort und gespeicherte URLs
 */
@Entity
public class Userdata {

    protected Long id;
    private String username;
    private String password;
    private String urlList;

    public Userdata() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Pattern(regexp = "\\w+", message = "Der Benutzername darf nur Buchstaben, Zahlen und Unterstriche enthalten")
    @Length(min = 3, max = 15, message = "Die Länge des Benutzernamens muss zwischen 3 und 15 liegen")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 8, max = 64, message = "Die Länge des Passworts muss zwischen 8 und 64 liegen")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlList() {
        return urlList;
    }

    public void setUrlList(String urlList) {
        this.urlList = urlList;
    }

    public String urlsToHTMLString() {
        return "Deine aktuell gespeicherten Quellen sind:<br><br>" + urlList.replace(";", "<br>");
    }
}
