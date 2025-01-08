package Storage.Alloggio;

import Storage.Connessione;
import Storage.Struttura.Struttura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlloggioDAO  {
    public void copyResultIntoList(ResultSet rs, List<Alloggio> list) throws SQLException {
        while (rs.next()) {
            Alloggio a = new Alloggio();

            a.setNumeroAlloggio(rs.getInt(1));
            a.setFkStruttura(rs.getInt(2));
            a.setPrezzoNotte(rs.getDouble(3));
            a.setPostiletto(rs.getInt(4));
            a.setTipoAlloggio(rs.getString(5));
            a.setDescrizione(rs.getString(6));
            a.setUrlImmagine(rs.getString(7));

            list.add(a);
        }
    }
    public List<Alloggio> doRetrieveAll() {
        try (Connection con = Connessione.getConnection()) {
            List<Alloggio> list = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("select * from alloggio");
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
                    con.prepareStatement("select * from\n" +
                            "alloggio where numero_alloggio = ? and fk_struttura = ? ");
            ps.setInt(1, numeroAlloggio);
            ps.setInt(2, fkStruttura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Alloggio a = new Alloggio();

                a.setNumeroAlloggio(rs.getInt(1));
                a.setFkStruttura(rs.getInt(2));
                a.setPrezzoNotte(rs.getDouble(3));
                a.setPostiletto(rs.getInt(4));
                a.setTipoAlloggio(rs.getString(5));
                a.setDescrizione(rs.getString(6));
                a.setUrlImmagine(rs.getString(7));

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
                    "select distinct alloggio.* from alloggio join struttura on fk_struttura = struttura.id_struttura" +
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
            ps.setInt(2, alloggio.getFkStruttura());
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
            chiavi.add(alloggio.getFkStruttura());

            return chiavi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Alloggio alloggio, int numeroAlloggio, int fkStruttura) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE alloggio SET numero_alloggio =?, fk_struttura =?, prezzo_notte =?, numero_posti_letto =?, tipo_alloggio =?, descrizione=?, url_immagine=? WHERE numero_alloggio=? and id_struttura =?");

            ps.setInt(1, alloggio.getNumeroAlloggio());
            ps.setInt(2, alloggio.getFkStruttura());
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
