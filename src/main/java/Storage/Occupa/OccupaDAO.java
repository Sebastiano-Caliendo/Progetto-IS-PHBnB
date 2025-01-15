package Storage.Occupa;

import Storage.Connessione;
import java.sql.*;

public class OccupaDAO {

    public int doSave(Occupa occupa) {
        try (Connection con = Connessione.getConnection()) {

            PreparedStatement ps = con.prepareStatement("insert into occupa (fk_prenotazione, fk_alloggio, fk_strutturaAlloggio, costo_prenotazione) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, occupa.getFkPrenotazione());
            ps.setInt(2, occupa.getFkAlloggio());
            ps.setInt(3, occupa.getFkStrutturaAlloggio());
            ps.setDouble(4, occupa.getCostoPrenotazione());

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
}
