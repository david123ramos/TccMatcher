package implementations;

import models.Keyword;
import models.Tcc;
import models.User;
import persistence.DatabaseConection;
import repositories.KeywordRepository;
import servlets.MatcherAPI;
import servlets.SignInServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeywordRepositoryImpl implements KeywordRepository {
    private DatabaseConection db = new DatabaseConection();

    @Override
    public Keyword get(Keyword bean) throws SQLException {
        return null;
    }

    @Override
    public Keyword get(long id) throws SQLException {
        return null;
    }

    public List<Tcc> getTccsfromKeyWord(String keyword) {

        TccRepositoryImpl tccRepository = new TccRepositoryImpl();
        List<Tcc> tccList = new ArrayList<>();

        ResultSet rs = db.read("SELECT id_tcc FROM KEYWORD WHERE title='"+keyword+"';");

        if(rs != null) {

            try {

                while (rs.next()) {

                    String id_tcc = rs.getString("id_tcc");
                    Tcc tccMatched = tccRepository.get(Long.parseLong(id_tcc));
                    tccList.add(tccMatched);
                }
                return tccList;

            } catch (SQLException ex) {
                Logger.getLogger(MatcherAPI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SignInServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    @Override
    public Keyword add(Keyword keyword) {
        String insertStatement = "INSERT INTO KEYWORD (title, id_tcc ) VALUES(''{0}'', ''{1}'')";


        Object[] params = new Object[] {keyword.getTitle(), keyword.getId_tcc()};
        String sql = MessageFormat.format(insertStatement, params);

        System.out.printf("Executing query: %s", sql);

        int res = db.update(sql);

        if(res > 0) {
            return keyword;
        }

        return null;
    }
}
