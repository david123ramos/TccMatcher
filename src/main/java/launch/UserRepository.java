package launch;

import entities.SignInBean;
import entities.User;

import java.sql.SQLException;

public interface UserRepository {
    User get(SignInBean bean) throws SQLException;
    int add(User user);
    void update(User user);
    void remove(User user);
}
