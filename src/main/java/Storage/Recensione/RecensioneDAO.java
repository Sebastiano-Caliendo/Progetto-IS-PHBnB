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
                    con.prepareStatement("SELECT email, descrizione, votorecensione, datarecensione, codiceprenotazione, numeroalloggio FROM recensioni WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
    }

    public Recensione doRetrieveByCodicePrenotazione(String codicePrenotazione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT email, descrizione, votorecensione, datarecensione, codiceprenotazione, numeroalloggio FROM recensioni WHERE codiceprenotazione=?");
            ps.setString(1, codicePrenotazione);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Recensione pr = new Recensione();
                pr.setEmailRecensore(rs.getString(1));
                pr.setDescrizione(rs.getString(2));
                pr.setVotoRecensione(rs.getInt(3));
                pr.setDataRecensione(rs.getDate(4));
                pr.setCodicePrenotazione(rs.getString(5));
                pr.setNumeroAlloggio(rs.getInt(6));
                return pr;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Recensione recensione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO recensioni (email, descrizione, votorecensione, datarecensione, codiceprenotazione, numeroalloggio) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, recensione.getEmailRecensore());
            ps.setString(2, recensione.getDescrizione());
            ps.setInt(3, recensione.getVotoRecensione());
            ps.setDate(4, recensione.getDataRecensione());
            ps.setString(5, recensione.getCodicePrenotazione());
            ps.setInt(6, recensione.getNumeroAlloggio());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            String email = rs.getString(1);
            recensione.setEmailRecensore(email);

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

                product.setEmailRecensore(rs.getString(1));
                product.setDescrizione(rs.getString(2));
                product.setVotoRecensione(rs.getInt(3));
                product.setDataRecensione(rs.getDate(4));
                product.setCodicePrenotazione(rs.getString(5));
                product.setNumeroAlloggio(rs.getInt(6));
                list.add(product);

            }
            con.close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String email, String codicePrenotazione, int numeroAlloggio) {
        try(Connection con = Connessione.getConnection()){
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO recensioni (email, descrizione, votorecensione, datarecensione, codiceprenotazione, numeroalloggio) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, codicePrenotazione);
            ps.setInt(3, numeroAlloggio);
            ps.setInt(4, numeroAlloggio);
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Recensione recensione, String email, String codicePrenotazione, int numeroAlloggio) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE recensioni SET email = ?, descrizione = ?, votorecensione = ?, datarecensione = ?, codiceprenotazione = ?, numeroalloggio =? WHERE codiceprenotazione=? and numeroalloggio =?");

            ps.setString(1, recensione.getEmailRecensore());
            ps.setString(2, recensione.getDescrizione());
            ps.setInt(3, recensione.getVotoRecensione());
            ps.setDate(4, recensione.getDataRecensione());
            ps.setString(5, recensione.getCodicePrenotazione());
            ps.setInt(6, recensione.getNumeroAlloggio());
            ps.setString(7, codicePrenotazione);
            ps.setInt(8, numeroAlloggio);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
