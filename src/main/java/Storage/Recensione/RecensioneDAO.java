package Storage.Recensione;

import Storage.Alloggio.Alloggio;
import Storage.Connessione;
import Storage.Struttura.Struttura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

    public Recensione doRetrieveById(int idRecensione) {
        //List<Recensione> recensioneList = new ArrayList<>();
        Recensione pr = new Recensione();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM recensioni WHERE idRecensione =?");
            ps.setInt(1, idRecensione);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pr.setIdRecensione(rs.getInt(1));
                pr.setEmailRecensore(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setVotoRecensione(rs.getInt(4));
                pr.setDataRecensione(rs.getDate(5));
                pr.setCodicePrenotazione(rs.getInt(6));
                pr.setNumeroAlloggio(rs.getInt(7));
                pr.setIdStrutturaAlloggio(rs.getInt(8));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pr;
    }

    public List<Recensione> doRetrieveByEmail(String email) {
        List<Recensione> recensioneList = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM recensioni WHERE fk_utente=?");
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
                pr.setIdStrutturaAlloggio(rs.getInt(8));
                recensioneList.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioneList;
    }

    public List<Recensione> doRetrieveByStruttura(int idStruttura)
    {
        List<Recensione> recensioneList = new ArrayList<>();
        try(Connection con = Connessione.getConnection()){
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM recensioni WHERE fk_codicestruttura = ?");
            ps.setInt(1, idStruttura);
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
                pr.setIdStrutturaAlloggio(rs.getInt(8));
                recensioneList.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioneList;
    }

    /*public List<Recensione> doRetrieveByAlloggio(int numeroAlloggio, int fkStruttura)
    {
        List<Recensione> recensioneList = new ArrayList<>();
        try(Connection con = Connessione.getConnection()){
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM recensioni WHERE fk_numeroalloggio = ? and fk_codicestruttura = ?");
            ps.setInt(1, numeroAlloggio);
            ps.setInt(2, fkStruttura);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Recensione pr = new Recensione();
                pr.setEmailRecensore(rs.getString(1));
                pr.setDescrizione(rs.getString(2));
                pr.setVotoRecensione(rs.getInt(3));
                pr.setDataRecensione(rs.getDate(4));
                pr.setCodicePrenotazione(rs.getString(5));
                pr.setNumeroAlloggio(rs.getInt(6));
                recensioneList.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioneList;
    }*/

    public Recensione doRetrieveByCodicePrenotazione(int codicePrenotazione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM recensioni WHERE fk_codiceprenotazione=?");
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
                pr.setIdStrutturaAlloggio(rs.getInt(8));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void doSave(Recensione recensione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO recensioni (fk_utente, descrizione, votorecensione, datarecensione, fk_codiceprenotazione, fk_numeroalloggio, fk_codicestruttura) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, recensione.getEmailRecensore());
            ps.setString(2, recensione.getDescrizione());
            ps.setInt(3, recensione.getVotoRecensione());
            ps.setDate(4, recensione.getDataRecensione());
            ps.setInt(5, recensione.getCodicePrenotazione());
            ps.setInt(6, recensione.getNumeroAlloggio());
            ps.setInt(7, recensione.getIdStrutturaAlloggio());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            /*ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            String email = rs.getString(1);
            recensione.setEmailRecensore(email);*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recensione> doRetrieveAll() {
        List<Recensione> list = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from recensioni");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Recensione product = new Recensione();
                product.setIdRecensione(rs.getInt(1));
                product.setEmailRecensore(rs.getString(2));
                product.setDescrizione(rs.getString(3));
                product.setVotoRecensione(rs.getInt(4));
                product.setDataRecensione(rs.getDate(5));
                product.setCodicePrenotazione(rs.getInt(6));
                product.setNumeroAlloggio(rs.getInt(7));
                product.setIdStrutturaAlloggio(rs.getInt(8));
                list.add(product);
            }

            con.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

            ps.setString(1, recensione.getEmailRecensore());
            ps.setString(2, recensione.getDescrizione());
            ps.setInt(3, recensione.getVotoRecensione());
            ps.setDate(4, recensione.getDataRecensione());
            ps.setInt(5, recensione.getCodicePrenotazione());
            ps.setInt(6, recensione.getNumeroAlloggio());
            ps.setInt(7, recensione.getIdStrutturaAlloggio());
            ps.setInt(8, idRecensione);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
