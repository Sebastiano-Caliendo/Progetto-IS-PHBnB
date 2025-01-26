package Storage.Alloggio;

import Storage.Connessione;
import Storage.Host.Host;
import Storage.Struttura.Struttura;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlloggioDAO  {
    public void copyResultIntoList(ResultSet rs, List<Alloggio> list) throws SQLException {
        while (rs.next()) {
            Alloggio a = new Alloggio();

            Host host = new Host(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"));
            Struttura struttura = new Struttura(rs.getInt("id_struttura"), host , rs.getString("nome_struttura"), rs.getString("struttura.via"), rs.getString("struttura.numero_civico"), rs.getString("struttura.citta"), rs.getString("struttura.descrizione"), rs.getString("struttura.url_immagine"));

            a.setNumeroAlloggio(rs.getInt("numero_alloggio"));
            a.setStruttura(struttura);
            a.setPrezzoNotte(rs.getDouble("prezzo_notte"));
            a.setPostiletto(rs.getInt("numero_posti_letto"));
            a.setTipoAlloggio(rs.getString("tipo_alloggio"));
            a.setDescrizione(rs.getString("alloggio.descrizione"));
            a.setUrlImmagine(rs.getString("alloggio.url_immagine"));

            list.add(a);
        }
    }

    // filtro utilizzato per cercare gli alloggi disponibili
    public List<Alloggio> doRetrieveAlloggiDisponibili(LocalDate checkIn, LocalDate checkOut, String luogo, int numPostiLetto) {
        List<Alloggio> alloggi = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ((alloggio JOIN struttura ON alloggio.fk_struttura = struttura.id_struttura) " +
                                "JOIN host ON struttura.fk_host = host.email)\n" +
                                "WHERE struttura.citta = ? AND alloggio.numero_posti_letto >= ? AND (numero_alloggio, fk_struttura) NOT IN \n" +
                                "(SELECT occupa.fk_alloggio, occupa.fk_strutturaAlloggio FROM occupa JOIN prenotazione ON occupa.fk_prenotazione = prenotazione.codice_prenotazione\n" +
                                "WHERE (prenotazione.check_in BETWEEN ? AND ? OR prenotazione.check_out BETWEEN ? AND ?));" );

            ps.setString(1, luogo);
            ps.setInt(2, numPostiLetto);
            ps.setDate(3, Date.valueOf(checkIn));
            ps.setDate(4, Date.valueOf(checkOut));
            ps.setDate(5, Date.valueOf(checkIn));
            ps.setDate(6, Date.valueOf(checkOut));
            ResultSet rs = ps.executeQuery();

            copyResultIntoList(rs, alloggi);

            return alloggi;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Alloggio> doRetrieveAll() {
        try (Connection con = Connessione.getConnection()) {
            List<Alloggio> list = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select * from ((alloggio join struttura on \n" +
                    "alloggio.fk_struttura = struttura.id_struttura) join host on fk_host = email)");
            ResultSet rs = ps.executeQuery();

            copyResultIntoList(rs, list);

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Alloggio doRetrieveById(int numeroAlloggio, int fkStruttura) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select * from ((alloggio join struttura on alloggio.fk_struttura = struttura.id_struttura) join host on struttura.fk_host = host.email)\n" +
                                             "where numero_alloggio = ? and fk_struttura = ?");
            ps.setInt(1, numeroAlloggio);
            ps.setInt(2, fkStruttura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Host host = new Host(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"));
                Struttura struttura = new Struttura(rs.getInt("id_struttura"), host , rs.getString("nome_struttura"), rs.getString("struttura.via"), rs.getString("struttura.numero_civico"), rs.getString("struttura.citta"), rs.getString("struttura.descrizione"), rs.getString("struttura.url_immagine"));
                Alloggio a = new Alloggio();

                a.setNumeroAlloggio(rs.getInt("numero_alloggio"));
                a.setStruttura(struttura);
                a.setPrezzoNotte(rs.getDouble("prezzo_notte"));
                a.setPostiletto(rs.getInt("numero_posti_letto"));
                a.setTipoAlloggio(rs.getString("tipo_alloggio"));
                a.setDescrizione(rs.getString("alloggio.descrizione"));
                a.setUrlImmagine(rs.getString("alloggio.url_immagine"));

                return a;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Alloggio> doRetrieveByCriteria(String field, String criteria) {
        try (Connection con = Connessione.getConnection()) {
            List<Alloggio> list = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(
                    "select distinct * from ((alloggio join struttura on fk_struttura = struttura.id_struttura) join host on fk_host = email" +
                            " where " + field + " = ?");
            ps.setString(1, criteria);

            this.copyResultIntoList(ps.executeQuery(), list);

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> doSave(Alloggio alloggio) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into alloggio (numero_alloggio, fk_struttura, prezzo_notte, numero_posti_letto, tipo_alloggio, descrizione, url_immagine) " +
                            "values (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alloggio.getNumeroAlloggio());
            ps.setInt(2, alloggio.getStruttura().getIdStruttura());
            ps.setDouble(3, alloggio.getPrezzoNotte());
            ps.setInt(4, alloggio.getPostiletto());
            ps.setString(5, alloggio.getTipoAlloggio());
            ps.setString(6, alloggio.getDescrizione());
            ps.setString(7, alloggio.getUrlImmagine());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            List<Integer> chiavi = new ArrayList<>();
            /*ResultSet rs = ps.getGeneratedKeys();

            rs.next();*/

            chiavi.add(alloggio.getNumeroAlloggio());
            chiavi.add(alloggio.getStruttura().getIdStruttura());

            return chiavi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Alloggio alloggio, int numeroAlloggio, int fkStruttura) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE alloggio SET numero_alloggio =?, fk_struttura =?, prezzo_notte =?, numero_posti_letto =?, tipo_alloggio =?, descrizione=?, url_immagine=? WHERE numero_alloggio=? and fk_struttura =?");

            ps.setInt(1, alloggio.getNumeroAlloggio());
            ps.setInt(2, alloggio.getStruttura().getIdStruttura());
            ps.setDouble(3, alloggio.getPrezzoNotte());
            ps.setInt(4, alloggio.getPostiletto());
            ps.setString(5, alloggio.getTipoAlloggio());
            ps.setString(6, alloggio.getDescrizione());
            ps.setString(7, alloggio.getUrlImmagine());
            ps.setInt(8, numeroAlloggio);
            ps.setInt(9, fkStruttura); // id da passare come parametro

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Integer> doDelete(int numeroAlloggio, int fkStruttura) {         // chiave composta
        List<Integer> chiaviAlloggio = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from alloggio where numero_alloggio = ? and fk_struttura = ?");
            ps.setInt(1, numeroAlloggio);
            ps.setInt(2, fkStruttura);
            ps.executeUpdate();

            chiaviAlloggio.add(numeroAlloggio);
            chiaviAlloggio.add(fkStruttura);

            return chiaviAlloggio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
