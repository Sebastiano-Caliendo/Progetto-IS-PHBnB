package Storage.Host;

import Storage.Connessione;
import Storage.Utente.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostDAO {

    public List<Host> doRetrieveAll() {
        List<Host> list = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from host");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Host host = new Host();
                host.setEmail(rs.getString(1));
                host.setNome(rs.getString(2));
                host.setCognome(rs.getString(3));
                host.setPassword(rs.getString(4));
                host.setDataNascita(rs.getDate(5).toLocalDate());
                host.setRecapitoTelefonico(rs.getString(6));
                list.add(host);
            }
            copyResultIntoList(rs, list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Host doRetrieveById(String email) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from host where email=?");

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Host h = new Host();

                h.setEmail(rs.getString(1));
                h.setNome(rs.getString(2));
                h.setCognome(rs.getString(3));
                h.setPassword(rs.getString(4));
                h.setDataNascita(rs.getDate(5).toLocalDate());
                h.setRecapitoTelefonico(rs.getString(6));

                return h;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Host doRetrieveByEmailAndPassword(String email, String password) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from host where email=? and password_=SHA1(?)");

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Host h = new Host();

                h.setEmail(rs.getString(1));
                h.setNome(rs.getString(2));
                h.setCognome(rs.getString(3));
                h.setPassword(rs.getString(4));
                h.setDataNascita(rs.getDate(5).toLocalDate());
                h.setRecapitoTelefonico(rs.getString(6));

                return h;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Host host) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("insert into host (email, nome, cognome, password_, data_nascita, recapito_telefonico) values (?, ?, ?, SHA1(?), ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, host.getEmail());
            ps.setString(2, host.getNome());
            ps.setString(3, host.getCognome());
            ps.setString(4, host.getPassword());
            ps.setString(5, host.getDataNascita().toString());
            ps.setString(6, host.getRecapitoTelefonico());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Host host, String email, String nome, String cognome, String password, String recapitoTelefonico) {
        try (Connection con = Connessione.getConnection()) {

            System.out.println("sono in update");

            PreparedStatement ps = con.prepareStatement("UPDATE host SET email=?, nome=?, cognome=?, password_=SHA1(?), recapito_telefonico=? WHERE email=?");

            ps.setString(1, email);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setString(4, password);
            ps.setString(5, recapitoTelefonico);
            ps.setString(6, host.getEmail());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String email) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM host  WHERE email=?");

            ps.setString(1, email);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void copyResultIntoList(ResultSet rs, List<Host> list) throws SQLException {
        while (rs.next()) {
            Host h = new Host();

            h.setEmail(rs.getString(1));
            h.setNome(rs.getString(2));
            h.setCognome(rs.getString(3));
            h.setPassword(rs.getString(4));
            h.setDataNascita(rs.getDate(5).toLocalDate());
            h.setRecapitoTelefonico(rs.getString(6));

            list.add(h);
        }
    }
}
