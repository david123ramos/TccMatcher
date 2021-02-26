package implementations;

import entities.SignInBean;
import entities.User;
import launch.UserRepository;
import servlet.DatabaseConection;
import servlet.MatcherAPI;
import servlet.SignInServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepositoryImpl implements UserRepository {

    private DatabaseConection db = new DatabaseConection();

    @Override
    public User get(SignInBean bean) throws SQLException {

        ResultSet rs = db.read("SELECT id, first_name, last_name, email FROM MOCK_DATA WHERE " +
                "email='"+bean.getEmail()+"' and psw='"+bean.getPassword()+"';");

        if(rs != null) {

            try {

                while(rs.next()) {
                    long id = rs.getInt("id");
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    String email = rs.getString("email");
                    return new User(id, first, last, email);
                }

            } catch (SQLException ex) {
                Logger.getLogger(MatcherAPI.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
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
    public int add(User user) {
        String insertStatement = "INSERT INTO MOCK_DATA (id, first_name, last_name"
                + ", email, gender, psw) VALUES('{0}', '{1}', '{2}', '{3}','{4}','{5}')";
        //TODO: Mudar a geração do ID;
        Object[] params = new Object[] {user.getEmail().hashCode(), user.getFirstName(), user.getLastName(),
        user.getEmail(), user.getGender(), user.getPassword()};
        String sql = MessageFormat.format(insertStatement, params);

        System.out.printf("Executing query: %s", sql);

        return db.update(sql);

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }
}
