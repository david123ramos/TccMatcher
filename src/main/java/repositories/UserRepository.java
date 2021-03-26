package repositories;

import models.SignInBean;
import models.User;

import java.sql.SQLException;

public interface UserRepository {
    User get(SignInBean bean) throws SQLException;
    User get(long id) throws SQLException;
    User add(User user);
    void update(User user);
    void remove(User user);
    void addPreferences(User u);
}
