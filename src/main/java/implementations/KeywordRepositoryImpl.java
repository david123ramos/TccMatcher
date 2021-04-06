package implementations;

import models.Keyword;
import persistence.DatabaseConection;
import repositories.KeywordRepository;

import java.sql.SQLException;
import java.text.MessageFormat;

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
