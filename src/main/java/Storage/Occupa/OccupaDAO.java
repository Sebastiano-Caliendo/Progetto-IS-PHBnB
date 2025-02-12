package Storage.Occupa;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Connessione;
import Storage.Host.Host;
import Storage.Host.HostDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OccupaDAO {

    public List<Occupa> doRetrieveAll() {
        List<Occupa> list = new ArrayList<>();

        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from ((prenotazione join occupa on prenotazione.codice_prenotazione = occupa.fk_prenotazione) join " +
                                                        "alloggio on occupa.fk_alloggio = alloggio.numero_alloggio and occupa.fk_strutturaAlloggio = alloggio.fk_struttura)");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Occupa occupa = new Occupa();

                Utente utente = new UtenteDAO().doRetrieveById(rs.getString("fk_utente"));
                Prenotazione prenotazione = new PrenotazioneDAO().doRetrieveById(rs.getInt("codice_prenotazione"));
                Alloggio alloggio = new AlloggioDAO().doRetrieveById(rs.getInt("numero_alloggio"), rs.getInt("fk_strutturaAlloggio"));

                occupa.setAlloggio(alloggio);
                occupa.setPrenotazione(prenotazione);
                occupa.setCostoPrenotazione(rs.getDouble("costo_prenotazione"));

                list.add(occupa);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Occupa> doRetrieveByUtente(String email) {
        List<Occupa> list = new ArrayList<>();

        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from ((prenotazione join occupa on prenotazione.codice_prenotazione = occupa.fk_prenotazione) join " +
                    "alloggio on occupa.fk_alloggio = alloggio.numero_alloggio and occupa.fk_strutturaAlloggio = alloggio.fk_struttura) where prenotazione.fk_utente = ?");

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Occupa occupa = new Occupa();

                Utente utente = new UtenteDAO().doRetrieveById(rs.getString("fk_utente"));
                Prenotazione prenotazione = new PrenotazioneDAO().doRetrieveById(rs.getInt("codice_prenotazione"));
                Alloggio alloggio = new AlloggioDAO().doRetrieveById(rs.getInt("numero_alloggio"), rs.getInt("fk_strutturaAlloggio"));

                occupa.setAlloggio(alloggio);
                occupa.setPrenotazione(prenotazione);
                occupa.setCostoPrenotazione(rs.getDouble("costo_prenotazione"));

                list.add(occupa);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Occupa doRetrieveOccupaByPrenotazione(int codicePrenotazione) {
        Occupa occupa = new Occupa();

        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from ((prenotazione join occupa on prenotazione.codice_prenotazione = occupa.fk_prenotazione) join " +
                    "alloggio on occupa.fk_alloggio = alloggio.numero_alloggio and occupa.fk_strutturaAlloggio = alloggio.fk_struttura) where occupa.fk_prenotazione = ?");

            ps.setInt(1, codicePrenotazione);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                //Utente utente = new UtenteDAO().doRetrieveById(rs.getString("fk_utente"));
                Prenotazione prenotazione = new PrenotazioneDAO().doRetrieveById(rs.getInt("codice_prenotazione"));
                Alloggio alloggio = new AlloggioDAO().doRetrieveById(rs.getInt("numero_alloggio"), rs.getInt("fk_strutturaAlloggio"));

                occupa.setAlloggio(alloggio);
                occupa.setPrenotazione(prenotazione);
                occupa.setCostoPrenotazione(rs.getDouble("costo_prenotazione"));

                //list.add(occupa);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return occupa;
    }

    public void doSave(Occupa occupa) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("insert into occupa (fk_prenotazione, fk_alloggio, fk_strutturaAlloggio, costo_prenotazione) values (?, ?, ?, ?)");

            ps.setInt(1, occupa.getPrenotazione().getCodicePrenotazione());
            ps.setInt(2, occupa.getAlloggio().getNumeroAlloggio());
            ps.setInt(3, occupa.getAlloggio().getStruttura().getIdStruttura());
            ps.setDouble(4, occupa.getCostoPrenotazione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            /*ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Occupa occupa) {
        //System.out.println("sto modificando id numero = " +idStruttura);
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE occupa SET costo_prenotazione=? WHERE fk_prenotazione=? and fk_alloggio=? and fk_strutturaAlloggio=?");

            ps.setDouble(1, occupa.getCostoPrenotazione());
            ps.setInt(2, occupa.getPrenotazione().getCodicePrenotazione());
            ps.setInt(3, occupa.getAlloggio().getNumeroAlloggio());
            ps.setInt(4, occupa.getAlloggio().getStruttura().getIdStruttura());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
