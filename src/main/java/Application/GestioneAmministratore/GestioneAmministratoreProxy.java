package Application.GestioneAmministratore;

import jakarta.servlet.http.HttpSession;

public class GestioneAmministratoreProxy {
    public boolean isAutenticato(HttpSession session) {
        if(session.getAttribute("admin") == null) {
            return false;
        }else{
            return session.getAttribute("admin") != null;
        }
    }
}
