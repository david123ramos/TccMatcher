package repositories;

import models.Keyword;

import java.sql.SQLException;

public interface KeywordRepository {
    Keyword get(Keyword bean) throws SQLException;
    Keyword get(long id) throws SQLException;
    Keyword add(Keyword tcc);
}
