package Application.GestioneAmministratore;

import Storage.Alloggio.Alloggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "modificaDatiSistemaAlloggioServlet", value = "/modificaDatiSistemaAlloggioServlet")
public class ModificaDatiSistemaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldNumeroAlloggio = req.getParameter("oldNumAlloggio");
        String numeroAlloggio = req.getParameter("numAlloggio");
        String fkStruttura = req.getParameter("fkStruttura");
        String prezzoNotte = req.getParameter("prezzoNotte");
        String postiLetto = req.getParameter("postiLetto");
        String tipoAlloggio = req.getParameter("tipoAlloggio");
        String descrizione = req.getParameter("descAlloggio");


        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        boolean flag = gestioneAmministratoreFacade.modificaDatiSistemaAlloggio(numeroAlloggio, prezzoNotte, postiLetto, tipoAlloggio, descrizione, oldNumeroAlloggio, fkStruttura);

        String address;

        if(flag) {
            address = "Interface/indexAdminGUI.jsp?op=ok";
        } else {
            address = "Interface/indexAdminGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
