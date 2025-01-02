package Storage.Host;

import Storage.Connessione;
import Storage.Utente.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostDAO {

    public List<Host> doRetrieveAll() {
        try (Connection con = Connessione.getConnection()) {

            List<Host> list = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select * from host");
            ResultSet rs = ps.executeQuery();

            copyResultIntoList(rs, list);

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int doSave(Host host) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("insert into host (email, nome, cognome, password_, data_nascita, recapito_telefonico) values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, host.getEmail());
            ps.setString(2, host.getNome());
            ps.setString(3, host.getCognome());
            ps.setString(4, host.getPassword());
            ps.setDate(5, (Date) host.getDataNascita());
            ps.setString(6, host.getRecapitoTelefonico());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            //controllare
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Host host, String email, String nome, String cognome, String password, Date dataNascita, String recapitoTelefonico) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE utente SET email=?, nome=?, cognome=?, password_=?, data_nascita=?, recapito_telefonico=? WHERE email=?");

            ps.setString(1, email);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setString(4, password);
            ps.setDate(5, dataNascita);
            ps.setString(6, recapitoTelefonico);
            ps.setString(7, host.getEmail());

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
            h.setDataNascita(rs.getDate(5));
            h.setRecapitoTelefonico(rs.getString(6));

            list.add(h);
        }
    }
}
