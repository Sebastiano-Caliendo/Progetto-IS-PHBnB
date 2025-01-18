package Storage.Prenotazione;

import Storage.Alloggio.Alloggio;
import Storage.Connessione;
import Storage.Struttura.Struttura;
import Storage.Utente.Utente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {

    public List<Prenotazione> doRetrieveAll() {
        List<Prenotazione> list = new ArrayList<>();

        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from prenotazione join utente on prenotazione.fk_utente = utente.email");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Prenotazione p = new Prenotazione();
                Utente utente = new Utente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getString("citta"), rs.getString("numero_civico"), rs.getString("via"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"), rs.getBoolean("isAdmin"));

                p.setCheckIn(rs.getDate("check_in").toLocalDate());
                p.setCheckOut(rs.getDate("check_out").toLocalDate());
                p.setUtente(utente);
                p.setNumeroPersone(rs.getInt("numero_persone"));
                p.setNumeroCartaCredito(rs.getString("numero_carta"));
                p.setDataScadenzaCarta(rs.getDate("data_scadenza_carta").toLocalDate());
                p.setCviCarta(rs.getString("cvi_carta"));

                list.add(p);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Prenotazione doRetrieveById(int codPrenotazione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select * from prenotazione join utente on prenotazione.fk_utente = utente.email where prenotazione.codice_prenotazione = ?");
            ps.setInt(1, codPrenotazione);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Prenotazione p = new Prenotazione();
                Utente utente = new Utente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getString("citta"), rs.getString("numero_civico"), rs.getString("via"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"), rs.getBoolean("isAdmin"));

                p.setCodicePrenotazione(rs.getInt("codice_prenotazione"));
                p.setUtente(utente);
                p.setCheckIn(rs.getDate("check_in").toLocalDate());
                p.setCheckOut(rs.getDate("check_out").toLocalDate());
                p.setNumeroPersone(rs.getInt("numero_persone"));
                p.setNumeroCartaCredito(rs.getString("numero_carta"));
                p.setDataScadenzaCarta(rs.getDate("data_scadenza_carta").toLocalDate());
                p.setCviCarta(rs.getString("cvi_carta"));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prenotazione> doRetrievePrenotazioniByAlloggio(Alloggio alloggio) {
        List<Prenotazione> list = new ArrayList<>();

        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select distinct * from (prenotazione join utente on prenotazione.fk_utente = utente.email) join " +
                                                "occupa on prenotazione.codice_prenotazione = occupa.fk_prenotazione where occupa.fk_alloggio = ? and occupa.fk_strutturaAlloggio = ?");

            ps.setInt(1, alloggio.getNumeroAlloggio());
            ps.setInt(2, alloggio.getStruttura().getIdStruttura());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prenotazione p = new Prenotazione();
                Utente utente = new Utente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password_"), rs.getString("citta"), rs.getString("numero_civico"), rs.getString("via"), rs.getDate("data_nascita").toLocalDate(), rs.getString("recapito_telefonico"), rs.getBoolean("isAdmin"));

                p.setCodicePrenotazione(rs.getInt("codice_prenotazione"));
                p.setUtente(utente);
                p.setCheckIn(rs.getDate("check_in").toLocalDate());
                p.setCheckOut(rs.getDate("check_out").toLocalDate());
                p.setNumeroPersone(rs.getInt("numero_persone"));
                p.setNumeroCartaCredito(rs.getString("numero_carta"));
                p.setDataScadenzaCarta(rs.getDate("data_scadenza_carta").toLocalDate());
                p.setCviCarta(rs.getString("cvi_carta"));

                list.add(p);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Prenotazione prenotazione) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("insert into prenotazione (check_in, check_out, fk_utente, numero_persone, numero_carta, data_scadenza_carta, cvi_carta) values (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, Date.valueOf(prenotazione.getCheckIn()));
            ps.setDate(2, Date.valueOf(prenotazione.getCheckOut()));
            ps.setString(3, prenotazione.getUtente().getEmail());
            ps.setInt(4, prenotazione.getNumeroPersone());
            ps.setString(5, prenotazione.getNumeroCartaCredito());
            ps.setDate(6, Date.valueOf(prenotazione.getDataScadenzaCarta()));
            ps.setString(7, prenotazione.getCviCarta());

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

    public void doUpdate(Prenotazione prenotazione, LocalDate checkIn, LocalDate checkOut, int numeroPersone) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE prenotazione SET check_in=?, check_out=?, numero_persone=? WHERE codice_prenotazione=?");

            ps.setDate(1, Date.valueOf(checkIn));
            ps.setDate(2, Date.valueOf(checkOut));
            ps.setInt(3, numeroPersone);
            ps.setInt(4, prenotazione.getCodicePrenotazione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int codPrenotazione) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from prenotazione where numero_alloggio = ?");
            ps.setInt(1, codPrenotazione);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
