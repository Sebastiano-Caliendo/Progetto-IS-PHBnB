package Storage.Recensione;

import Storage.Alloggio.Alloggio;
import Storage.Connessione;
import Storage.Prenotazione.Prenotazione;
import Storage.Struttura.StrutturaDAO;
import Storage.Utente.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

    public Recensione doRetrieveById(int idRecensione) {
        Recensione pr = new Recensione();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ((recensioni JOIN utente ON recensioni.fk_utente = utente.email)" +
                            " JOIN alloggio ON recensioni.fk_numeroalloggio = alloggio.numero_alloggio AND recensioni.fk_codicestruttura = alloggio.fk_struttura) JOIN prenotazione ON recensioni.fk_codiceprenotazione = prenotazione.codice_prenotazione WHERE idRecensione =?");
            ps.setInt(1, idRecensione);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                StrutturaDAO strutturaDAO = new StrutturaDAO();
                Utente user = new Utente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getString("citta"), rs.getString("numero_civico"), rs.getString("via"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"), rs.getBoolean("isAdmin"));
                Prenotazione prenotazione = new Prenotazione(rs.getString("nomeIntestatario"), rs.getString("cognomeIntestatario"), rs.getDate("check_in").toLocalDate(), rs.getDate("check_out").toLocalDate(), user, rs.getInt("numero_persone"), rs.getString("numero_carta"), rs.getDate("data_scadenza_carta").toLocalDate(), rs.getString("cvi_carta"));
                prenotazione.setCodicePrenotazione(rs.getInt("codice_prenotazione"));
                Alloggio alloggio = new Alloggio(rs.getInt("numero_alloggio"), strutturaDAO.doRetrieveById(rs.getInt("fk_struttura")), rs.getDouble("prezzo_notte"), rs.getInt("numero_posti_letto"), rs.getString("tipo_alloggio"), rs.getString("alloggio.descrizione"), rs.getString("url_immagine"));

                pr.setIdRecensione(rs.getInt("idRecensione"));
                pr.setUtente(user);
                pr.setDescrizione(rs.getString("recensioni.descrizione"));
                pr.setVotoRecensione(rs.getInt("votorecensione"));
                pr.setDataRecensione(rs.getDate("data_recensione").toLocalDate());
                pr.setPrenotazione(prenotazione);
                pr.setAlloggio(alloggio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pr;
    }

    public List<Recensione> doRetrieveByEmail(String email) {
        List<Recensione> recensioniList = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ((recensioni JOIN utente ON recensioni.fk_utente = utente.email)" +
                            " JOIN alloggio ON recensioni.fk_numeroalloggio = alloggio.numero_alloggio AND recensioni.fk_codicestruttura = alloggio.fk_struttura) JOIN prenotazione ON recensioni.fk_codiceprenotazione = prenotazione.codice_prenotazione WHERE recensioni.fk_utente =?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Recensione pr = new Recensione();
                StrutturaDAO strutturaDAO = new StrutturaDAO();
                Utente user = new Utente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getString("citta"), rs.getString("numero_civico"), rs.getString("via"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"), rs.getBoolean("isAdmin"));
                Prenotazione prenotazione = new Prenotazione(rs.getString("nomeIntestatario"), rs.getString("cognomeIntestatario"), rs.getDate("check_in").toLocalDate(), rs.getDate("check_out").toLocalDate(), user, rs.getInt("numero_persone"), rs.getString("numero_carta"), rs.getDate("data_scadenza_carta").toLocalDate(), rs.getString("cvi_carta"));
                prenotazione.setCodicePrenotazione(rs.getInt("codice_prenotazione"));
                Alloggio alloggio = new Alloggio(rs.getInt("numero_alloggio"), strutturaDAO.doRetrieveById(rs.getInt("fk_struttura")), rs.getDouble("prezzo_notte"), rs.getInt("numero_posti_letto"), rs.getString("tipo_alloggio"), rs.getString("alloggio.descrizione"), rs.getString("url_immagine"));

                pr.setIdRecensione(rs.getInt("idRecensione"));
                pr.setUtente(user);
                pr.setDescrizione(rs.getString("recensioni.descrizione"));
                pr.setVotoRecensione(rs.getInt("votorecensione"));
                pr.setDataRecensione(rs.getDate("data_recensione").toLocalDate());
                pr.setPrenotazione(prenotazione);
                pr.setAlloggio(alloggio);

                recensioniList.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioniList;
    }

    public List<Recensione> doRetrieveByStruttura(int idStruttura)
    {
        List<Recensione> recensioniList = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ((recensioni JOIN utente ON recensioni.fk_utente = utente.email)" +
                            " JOIN alloggio ON recensioni.fk_numeroalloggio = alloggio.numero_alloggio AND recensioni.fk_codicestruttura = alloggio.fk_struttura) JOIN prenotazione ON recensioni.fk_codiceprenotazione = prenotazione.codice_prenotazione WHERE recensioni.fk_codicestruttura =?");
            ps.setInt(1, idStruttura);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Recensione pr = new Recensione();
                StrutturaDAO strutturaDAO = new StrutturaDAO();
                Utente user = new Utente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getString("citta"), rs.getString("numero_civico"), rs.getString("via"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"), rs.getBoolean("isAdmin"));
                Prenotazione prenotazione = new Prenotazione(rs.getString("nomeIntestatario"), rs.getString("cognomeIntestatario"), rs.getDate("check_in").toLocalDate(), rs.getDate("check_out").toLocalDate(), user, rs.getInt("numero_persone"), rs.getString("numero_carta"), rs.getDate("data_scadenza_carta").toLocalDate(), rs.getString("cvi_carta"));
                prenotazione.setCodicePrenotazione(rs.getInt("codice_prenotazione"));
                Alloggio alloggio = new Alloggio(rs.getInt("numero_alloggio"), strutturaDAO.doRetrieveById(rs.getInt("fk_struttura")), rs.getDouble("prezzo_notte"), rs.getInt("numero_posti_letto"), rs.getString("tipo_alloggio"), rs.getString("alloggio.descrizione"), rs.getString("url_immagine"));

                pr.setIdRecensione(rs.getInt("idRecensione"));
                pr.setUtente(user);
                pr.setDescrizione(rs.getString("recensioni.descrizione"));
                pr.setVotoRecensione(rs.getInt("votorecensione"));
                pr.setDataRecensione(rs.getDate("data_recensione").toLocalDate());
                pr.setPrenotazione(prenotazione);
                pr.setAlloggio(alloggio);

                recensioniList.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioniList;
    }

    public Recensione doRetrieveByCodicePrenotazione(int codicePrenotazione) {
        Recensione pr = new Recensione();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ((recensioni JOIN utente ON recensioni.fk_utente = utente.email)" +
                            " JOIN alloggio ON recensioni.fk_numeroalloggio = alloggio.numero_alloggio AND recensioni.fk_codicestruttura = alloggio.fk_struttura) JOIN prenotazione ON recensioni.fk_codiceprenotazione = prenotazione.codice_prenotazione WHERE recensioni.fk_codiceprenotazione =?");
            ps.setInt(1, codicePrenotazione);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                StrutturaDAO strutturaDAO = new StrutturaDAO();
                Utente user = new Utente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getString("citta"), rs.getString("numero_civico"), rs.getString("via"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"), rs.getBoolean("isAdmin"));
                Prenotazione prenotazione = new Prenotazione(rs.getString("nomeIntestatario"), rs.getString("cognomeIntestatario"), rs.getDate("check_in").toLocalDate(), rs.getDate("check_out").toLocalDate(), user, rs.getInt("numero_persone") , rs.getString("numero_carta"), rs.getDate("data_scadenza_carta").toLocalDate(), rs.getString("cvi_carta"));
                prenotazione.setCodicePrenotazione(rs.getInt("codice_prenotazione"));
                Alloggio alloggio = new Alloggio(rs.getInt("numero_alloggio"), strutturaDAO.doRetrieveById(rs.getInt("fk_struttura")), rs.getDouble("prezzo_notte"), rs.getInt("numero_posti_letto"), rs.getString("tipo_alloggio"), rs.getString("alloggio.descrizione"), rs.getString("url_immagine"));

                pr.setIdRecensione(rs.getInt("idRecensione"));
                pr.setUtente(user);
                pr.setDescrizione(rs.getString("recensioni.descrizione"));
                pr.setVotoRecensione(rs.getInt("votorecensione"));
                pr.setDataRecensione(rs.getDate("data_recensione").toLocalDate());
                pr.setPrenotazione(prenotazione);
                pr.setAlloggio(alloggio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pr;
    }

    public void doSave(Recensione recensione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO recensioni (fk_utente, descrizione, votorecensione, data_recensione, fk_numeroalloggio, fk_codicestruttura, fk_codiceprenotazione) VALUES (?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, recensione.getUtente().getEmail());
            ps.setString(2, recensione.getDescrizione());
            ps.setInt(3, recensione.getVotoRecensione());
            ps.setDate(4, Date.valueOf(recensione.getDataRecensione()));
            ps.setInt(5, recensione.getAlloggio().getNumeroAlloggio());
            ps.setInt(6, recensione.getAlloggio().getStruttura().getIdStruttura());
            ps.setInt(7, recensione.getPrenotazione().getCodicePrenotazione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> codiciPrenotazioniRecensite(Utente utente) {
        List<Integer> list = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select recensioni.fk_codiceprenotazione from recensioni where recensioni.fk_utente = ?");
            ps.setString(1, utente.getEmail());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Recensione> doRetrieveAll() {
        List<Recensione> list = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ((recensioni JOIN utente ON recensioni.fk_utente = utente.email) JOIN alloggio ON recensioni.fk_numeroalloggio = alloggio.numero_alloggio AND recensioni.fk_codicestruttura = alloggio.fk_struttura) JOIN prenotazione ON recensioni.fk_codiceprenotazione = prenotazione.codice_prenotazione");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Recensione r = new Recensione();
                StrutturaDAO strutturaDAO = new StrutturaDAO();
                Utente user = new Utente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getString("citta"), rs.getString("numero_civico"), rs.getString("via"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"), rs.getBoolean("isAdmin"));
                Prenotazione prenotazione = new Prenotazione(rs.getString("nomeIntestatario"), rs.getString("cognomeIntestatario"), rs.getDate("check_in").toLocalDate(), rs.getDate("check_out").toLocalDate(), user, rs.getInt("numero_persone"), rs.getString("numero_carta"), rs.getDate("data_scadenza_carta").toLocalDate(), rs.getString("cvi_carta"));
                prenotazione.setCodicePrenotazione(rs.getInt("codice_prenotazione"));
                Alloggio alloggio = new Alloggio(rs.getInt("numero_alloggio"), strutturaDAO.doRetrieveById(rs.getInt("fk_struttura")), rs.getDouble("prezzo_notte"), rs.getInt("numero_posti_letto"), rs.getString("tipo_alloggio"), rs.getString("alloggio.descrizione"), rs.getString("url_immagine"));

                r.setIdRecensione(rs.getInt("idRecensione"));
                r.setUtente(user);
                r.setDescrizione(rs.getString("recensioni.descrizione"));
                r.setVotoRecensione(rs.getInt("votorecensione"));
                r.setDataRecensione(rs.getDate("data_recensione").toLocalDate());
                r.setPrenotazione(prenotazione);
                r.setAlloggio(alloggio);
                list.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void doDelete(int idRecensione) {
        try(Connection con = Connessione.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM recensioni WHERE idRecensione = ? ",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRecensione);
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Recensione recensione, int idRecensione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE recensioni SET fk_utente = ?, descrizione = ?, votorecensione = ?, datarecensione = ?, fk_codiceprenotazione = ?, fk_numeroalloggio =?, fk_codicestruttura =? WHERE idRecensione=? ");

            ps.setString(1, recensione.getUtente().getEmail());
            ps.setString(2, recensione.getDescrizione());
            ps.setInt(3, recensione.getVotoRecensione());
            ps.setDate(4, Date.valueOf(recensione.getDataRecensione()));
            ps.setInt(5, recensione.getPrenotazione().getCodicePrenotazione());
            ps.setInt(6, recensione.getAlloggio().getNumeroAlloggio());
            ps.setInt(7, recensione.getAlloggio().getStruttura().getIdStruttura());
            ps.setInt(8, idRecensione);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
