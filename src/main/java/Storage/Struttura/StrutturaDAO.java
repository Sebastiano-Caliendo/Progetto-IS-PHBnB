package Storage.Struttura;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Connessione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StrutturaDAO {

    public void copyResultIntoList(ResultSet rs, List<Struttura> list) throws SQLException {
        while (rs.next()) {
            Struttura s = new Struttura();

            s.setIdStruttura(rs.getInt(1));
            s.setFkHost(rs.getString(2));
            s.setNomeStruttura(rs.getString(3));
            s.setVia(rs.getString(4));
            s.setNumCivico(Integer.parseInt(rs.getString(5)));
            s.setCitta(rs.getString(6));
            s.setNumAlloggi(rs.getInt(7));
            s.setDescrizione(rs.getString(8));

            list.add(s);
        }
    }

    public List<Alloggio> doRetrieveAlloggiByStruttura(Struttura struttura) {
        try (Connection con = Connessione.getConnection()) {
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            List<Alloggio> list = new ArrayList<>();

            PreparedStatement ps = con.prepareStatement(
                    "select distinct alloggio.* from alloggio join struttura on alloggio.fk_struttura = ?" );

            ps.setInt(1, struttura.getIdStruttura());
            ResultSet rs = ps.executeQuery();

            alloggioDAO.copyResultIntoList(rs, list);

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Struttura> doRetrieveAll() {
        List<Struttura> list = new ArrayList<>();
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from struttura");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Struttura struttura = new Struttura();

                struttura.setFkHost(rs.getString("fk_host"));
                struttura.setNomeStruttura(rs.getString("nome_struttura"));
                struttura.setVia(rs.getString("via"));
                struttura.setNumCivico(rs.getInt("numero_civico"));
                struttura.setCitta(rs.getString("citta"));
                struttura.setNumAlloggi(rs.getInt("numero_alloggi"));
                struttura.setDescrizione(rs.getString("descrizione"));
                list.add(struttura);
            }

            copyResultIntoList(rs, list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Struttura doRetrieveById(int idStruttura) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("select * from\n" +
                            "struttura where id_struttura = ?");
            ps.setInt(1, idStruttura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Struttura s = new Struttura();

                s.setIdStruttura(rs.getInt(1));
                s.setFkHost(rs.getString(2));
                s.setNomeStruttura(rs.getString(3));
                s.setVia(rs.getString(4));
                s.setNumCivico(Integer.parseInt(rs.getString(5)));
                s.setCitta(rs.getString(6));
                s.setNumAlloggi(rs.getInt(7));
                s.setDescrizione(rs.getString(8));

                return s;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Struttura> doRetrieveByCriteria(String field, String criteria) {
        try (Connection con = Connessione.getConnection()) {
            List<Struttura> list = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement(
                    "select distinct struttura.* from struttura join host on fk_host = host.email" +
                            " where " + field + " = ?");
            ps.setString(1, criteria);

            this.copyResultIntoList(ps.executeQuery(), list);

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Struttura struttura) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into struttura (fk_host, nome_struttura, via, numero_civico, citta, numero_alloggi, descrizione) " +
                            "values (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, struttura.getFkHost());
            ps.setString(2, struttura.getNomeStruttura());
            ps.setString(3, struttura.getVia());
            ps.setString(4, String.valueOf(struttura.getNumCivico()));
            ps.setString(5, struttura.getCitta());
            ps.setInt(6, struttura.getNumAlloggi());
            ps.setString(7, struttura.getDescrizione());

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

    public void doUpdate(Struttura struttura, int idStruttura) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE prodotti SET fk_host=?, nome_struttura=?, via=?, numero_civico=?, citta=?, numero_alloggi=?, descrizione=? WHERE id_struttura=?");

            ps.setString(1, struttura.getFkHost());
            ps.setString(2, struttura.getNomeStruttura());
            ps.setString(3, struttura.getVia());
            ps.setString(4, String.valueOf(struttura.getNumCivico()));
            ps.setString(5, struttura.getCitta());
            ps.setInt(6, struttura.getNumAlloggi());
            ps.setString(7, struttura.getDescrizione());
            ps.setInt(8, idStruttura); // id da passare come parametro

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // quando elimino una struttura con on delete cascade -> eliminerò anche gli alloggi di quella struttura
    public int doDelete(int idStruttura) {
        try (Connection con = Connessione.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("delete from struttura where id_struttura = ?");
            ps.setInt(1, idStruttura);
            ps.executeUpdate();

            return idStruttura;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
