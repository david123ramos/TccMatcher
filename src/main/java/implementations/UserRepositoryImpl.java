package implementations;

import jwt.JwtController;
import models.SignInBean;
import models.User;
import repositories.UserRepository;
import persistence.DatabaseConection;
import servlets.MatcherAPI;
import servlets.SignInServlet;

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
    public User get(long id) throws SQLException {

        ResultSet rs = db.read("SELECT id, first_name, last_name, email FROM MOCK_DATA WHERE id='"+id+"';");

        if(rs != null) {

            try {

                while(rs.next()) {

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
    public User add(User user) {
        String insertStatement = "INSERT INTO MOCK_DATA (first_name, last_name"
                + ", email, gender, psw) VALUES('{0}', '{1}', '{2}', '{3}','{4}')";
        //TODO: Mudar a geração do ID;
        Object[] params = new Object[] {user.getFirstName(), user.getLastName(),
        user.getEmail(), user.getGender(), user.getPassword()};
        String sql = MessageFormat.format(insertStatement, params);

        System.out.printf("Executing query: %s", sql);

        int res = db.update(sql);

        if(res > 0) {
               SignInBean b = new SignInBean();
               b.setEmail(user.getEmail());
               b.setPassword(user.getPassword());
               try{
                    User response = this.get(b);
                    response.setToken(JwtController.generate(response.getEmail()));
                    return response;

               }catch (Exception s) {

               }

        }

        return null;

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }
}
