package Storage.Recensione;

import Storage.Alloggio.Alloggio;
import Storage.Connessione;
import Storage.Struttura.Struttura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {
    public List<Recensione> doRetrieveByEmail(String email) {
        List<Recensione> recensioneList = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idRecensione, fk_utente, descrizione, votorecensione, datarecensione, fk_codiceprenotazione, fk_numeroalloggio, fk_codicestruttura FROM recensioni WHERE fk_utente=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recensione pr = new Recensione();
                pr.setIdRecensione(rs.getInt(1));
                pr.setEmailRecensore(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setVotoRecensione(rs.getInt(4));
                pr.setDataRecensione(rs.getDate(5));
                pr.setCodicePrenotazione(rs.getInt(6));
                pr.setNumeroAlloggio(rs.getInt(7));
                pr.setFk_codiceStruttura(rs.getInt(8));
                recensioneList.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioneList;
    }

    public List<Recensione> doRetrieveByStruttura(String idStruttura)
    {
        List<Recensione> recensioneList = new ArrayList<>();
        try(Connection con = Connessione.getConnection()){
            PreparedStatement ps =
                    con.prepareStatement("SELECT idRecensione, fk_utente, descrizione, votorecensione, datarecensione, fk_codiceprenotazione, fk_numeroalloggio, fk_idstruttura FROM recensioni WHERE fk_idstruttura = ?");
            ps.setString(1, idStruttura);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Recensione pr = new Recensione();
                pr.setIdRecensione(rs.getInt(1));
                pr.setEmailRecensore(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setVotoRecensione(rs.getInt(4));
                pr.setDataRecensione(rs.getDate(5));
                pr.setCodicePrenotazione(rs.getInt(6));
                pr.setNumeroAlloggio(rs.getInt(7));
                pr.setFk_codiceStruttura(rs.getInt(8));
                recensioneList.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioneList;
    }

    public Recensione doRetrieveByCodicePrenotazione(int codicePrenotazione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idRecensione, fk_utente, descrizione, votorecensione, datarecensione, fk_codiceprenotazione, fk_numeroalloggio, fk_idstruttura FROM recensioni WHERE fk_codiceprenotazione=?");
            ps.setInt(1, codicePrenotazione);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Recensione pr = new Recensione();
                pr.setIdRecensione(rs.getInt(1));
                pr.setEmailRecensore(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setVotoRecensione(rs.getInt(4));
                pr.setDataRecensione(rs.getDate(5));
                pr.setCodicePrenotazione(rs.getInt(6));
                pr.setNumeroAlloggio(rs.getInt(7));
                pr.setFk_codiceStruttura(rs.getInt(8));
                return pr;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void doSave(Recensione recensione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO recensioni (idRecensioni, fk_utente, descrizione, votorecensione, datarecensione, fk_codiceprenotazione, fk_numeroalloggio, fk_codicestruttura) VALUES(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, recensione.getIdRecensione());
            ps.setString(2, recensione.getEmailRecensore());
            ps.setString(3, recensione.getDescrizione());
            ps.setInt(4, recensione.getVotoRecensione());
            ps.setDate(5, recensione.getDataRecensione());
            ps.setInt(6, recensione.getCodicePrenotazione());
            ps.setInt(7, recensione.getNumeroAlloggio());
            ps.setInt(8, recensione.getCodicePrenotazione());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            String email = rs.getString(2);
            recensione.setEmailRecensore(email);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recensione> doRetrieveAll() {
        List<Recensione> list = new ArrayList<>();

        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM recensioni");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Recensione recensione = new Recensione();

                recensione.setIdRecensione(rs.getInt("idRecensione"));
                recensione.setEmailRecensore(rs.getString("fk_utente"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setVotoRecensione(rs.getInt("votorecensione"));
                recensione.setDataRecensione(rs.getDate("datarecensione"));
                recensione.setCodicePrenotazione(rs.getInt("fk_codiceprenotazione"));
                recensione.setNumeroAlloggio(rs.getInt("fk_numeroalloggio"));
                recensione.setFk_codiceStruttura(rs.getInt("fk_codicestruttura"));

                list.add(recensione);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero delle recensioni", e);
        }

        return list;
    }

    public void doDelete(String email, String codicePrenotazione, int numeroAlloggio) {
        try(Connection con = Connessione.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM recensioni WHERE idRecensioneemail = ? AND fk_codiceprenotazione = ? AND fk_numeroalloggio = ?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, codicePrenotazione);
            ps.setInt(3, numeroAlloggio);
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Recensione recensione, String email, String descrizione, int votoRecensione, Date dataRecensione, int codicePrenotazione, int numeroAlloggio) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE recensioni SET email = ?, descrizione = ?, votorecensione = ?, datarecensione = ?, codiceprenotazione = ?, numeroalloggio =? WHERE idRecensione = ?");

            ps.setString(1, email);
            ps.setString(2, descrizione);
            ps.setInt(3, votoRecensione);
            ps.setDate(4,dataRecensione);
            ps.setInt(5, codicePrenotazione);
            ps.setInt(6, numeroAlloggio);
            ps.setInt(7, recensione.getIdRecensione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
