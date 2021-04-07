package repositories;

import models.Keyword;
import models.Preference;
import models.User;

import java.sql.SQLException;

public interface PreferencesRepository {
    Preference get(Preference bean) throws SQLException;
    Preference get(long id) throws SQLException;
    void add(User user);
}
