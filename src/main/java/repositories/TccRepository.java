package repositories;

import models.Tcc;

import java.sql.SQLException;

public interface TccRepository {
    Tcc get(Tcc bean) throws SQLException;
    Tcc get(long id) throws SQLException;
    Tcc add(Tcc tcc);
    void update(Tcc tcc);
    void remove(Tcc tcc);
    void addPreference(Tcc tcc);
}
