package Application.InserimentoRecensione;

import jakarta.servlet.http.HttpSession;

public class InserimentoRecensioneProxy {
    public boolean isUser(HttpSession session){
        if(session.getAttribute("user") == null){ return false;
        }else return session.getAttribute("user") != null;
    }

    public boolean isHost(HttpSession session){
        if(session.getAttribute("host") == null){ return false;
        }else return session.getAttribute("host") != null;
    }
}
