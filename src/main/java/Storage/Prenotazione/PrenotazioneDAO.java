package Storage.Prenotazione;

import Storage.Alloggio.Alloggio;
import Storage.Connessione;
import Storage.Struttura.Struttura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {

    public List<Prenotazione> doRetrieveAll() {
        try (Connection con = Connessione.getConnection()) {

            List<Prenotazione> list = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select * from prenotazione");
            ResultSet rs = ps.executeQuery();

            copyResultIntoList(rs, list);

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prenotazione> doRetrievePrenotazioniByAlloggio(Alloggio alloggio) {
        try (Connection con = Connessione.getConnection()) {
            List<Prenotazione> prenotazioniAlloggio = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(
                    "select distinct prenotazione.* from prenotazione " +
                            "join occupa on prenotazione.codice_prenotazione = occupa.fk_prenotazione " +
                            "join alloggio on occupa.fk_strutturaAlloggio = ? " +
                            "AND occupa.fk_alloggio = ?");
            ps.setInt(1, alloggio.getFkStruttura());
            ps.setInt(2, alloggio.getNumeroAlloggio());


            this.copyResultIntoList(ps.executeQuery(), prenotazioniAlloggio);

            return prenotazioniAlloggio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Prenotazione prenotazione) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("insert into prenotazione (check_in, check_out, fk_utente, numero_persone) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, (Date) prenotazione.getCheckIn());
            ps.setDate(2, (Date) prenotazione.getCheckOut());
            ps.setString(3, prenotazione.getFkUtente());
            ps.setInt(4, prenotazione.getNumeroPersone());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Prenotazione prenotazione, Date checkIn, Date checkOut, int numeroPersone) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE prenotazione SET check_in=?, check_out=?, numero_persone=? WHERE codice_prenotazione=?");

            ps.setDate(1, checkIn);
            ps.setDate(2, checkOut);
            ps.setInt(3, numeroPersone);
            ps.setInt(4, prenotazione.getCodicePrenotazione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private void copyResultIntoList(ResultSet rs, List<Prenotazione> list) throws SQLException {
        while (rs.next()) {
            Prenotazione p = new Prenotazione();

            p.setCodicePrenotazione(rs.getInt(1));
            p.setCheckIn(rs.getDate(2));
            p.setCheckOut(rs.getDate(3));
            p.setFkUtente(rs.getString(4));
            p.setNumeroPersone(rs.getInt(5));

            list.add(p);
        }
    }
}
