package Storage.Utente;

import Storage.Connessione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public List<Utente> doRetrieveAll() {
        try (Connection con = Connessione.getConnection()) {

            List<Utente> list = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select * from utente");
            ResultSet rs = ps.executeQuery();

            copyResultIntoList(rs, list);

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveById(String email) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from utente where email=?");

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Utente u = new Utente();

                u.setEmail(rs.getString(1));
                u.setNome(rs.getString(2));
                u.setCognome(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setCitta(rs.getString(5));
                u.setNumeroCivico(rs.getString(6));
                u.setVia(rs.getString(7));
                u.setDataNascita(rs.getDate(8).toLocalDate());
                u.setRecapitoTelefonico(rs.getString(9));
                u.setAdmin(rs.getBoolean(10));

                return u;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByEmailAndPassword(String email, String password) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from utente where email=? and password_=SHA1(?)");

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Utente u = new Utente();

                u.setEmail(rs.getString(1));
                u.setNome(rs.getString(2));
                u.setCognome(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setCitta(rs.getString(5));
                u.setNumeroCivico(rs.getString(6));
                u.setVia(rs.getString(7));
                u.setDataNascita(rs.getDate(8).toLocalDate());
                u.setRecapitoTelefonico(rs.getString(9));
                u.setAdmin(false);

                return u;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Utente utente) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("insert into utente (email, nome, cognome, password_, citta, numero_civico, via, data_nascita, recapito_telefonico, isAdmin) values (?, ?, ?, SHA1(?), ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, utente.getEmail());
            ps.setString(2, utente.getNome());
            ps.setString(3, utente.getCognome());
            ps.setString(4, utente.getPassword());
            ps.setString(5, utente.getCitta());
            ps.setString(6, utente.getNumeroCivico());
            ps.setString(7, utente.getVia());
            ps.setString(8, utente.getDataNascita().toString());
            ps.setString(9, utente.getRecapitoTelefonico());
            ps.setBoolean(10, utente.isAdmin());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Utente utente, String email, String nome, String cognome, String password, String citta, String numeroCivico, String via, String recapitoTelefonico) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE utente SET email=?, nome=?, cognome=?, password_=SHA1(?), citta=?, numero_civico=?, via=?, recapito_telefonico=? WHERE email=?");

            ps.setString(1, email);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setString(4, password);
            ps.setString(5, citta);
            ps.setString(6, numeroCivico);
            ps.setString(7, via);
            ps.setString(8, recapitoTelefonico);
            ps.setString(9, utente.getEmail());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doDelete(String email) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM utente  WHERE email=?");

            ps.setString(1, email);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void copyResultIntoList(ResultSet rs, List<Utente> list) throws SQLException {
        while (rs.next()) {
            Utente u = new Utente();

            u.setEmail(rs.getString(1));
            u.setNome(rs.getString(2));
            u.setCognome(rs.getString(3));
            u.setPassword(rs.getString(4));
            u.setCitta(rs.getString(5));
            u.setNumeroCivico(rs.getString(6));
            u.setVia(rs.getString(7));
            u.setDataNascita(rs.getDate(8).toLocalDate());
            u.setRecapitoTelefonico(rs.getString(9));
            u.setAdmin(rs.getBoolean(10));

            list.add(u);
        }
    }
}
