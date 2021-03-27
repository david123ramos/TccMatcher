package implementations;

import jwt.JwtController;
import models.SignInBean;
import models.User;
import repositories.UserRepository;
import persistence.DatabaseConection;
import servlets.MatcherAPI;
import servlets.SignInServlet;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
                + ", email, gender, psw) VALUES(''{0}'', ''{1}'', ''{2}'', ''{3}'',''{4}'')";
        //TODO: Mudar a geracao do ID;

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
    	StringBuilder sttm =  new StringBuilder("UPDATE MOCK_DATA SET first_name= '"+user.getFirstName()+"', "
    			+"last_name= '"+user.getLastName()+"', "
    			+"email= '"+user.getEmail()+"', "
    			+"gender= '"+user.getGender()+"', "
    			+"psw= '"+user.getPassword()+"', "
    			+"institution= '"+user.getInstitution()+"' WHERE id='"+user.getId()+"';");
    	
    	System.out.printf("Executing query: %s \n",sttm.toString());
    	
    	//TODO salvar no banco, e verificas se so campos est�o null
    	
    }

    @Override
    public void remove(User user) {}

    @Override
    public void addPreferences(User u) {
        StringBuilder sttm =  new StringBuilder("INSERT INTO PREFERENCES (description, id_user) VALUES ");
        
        u.getPreferenceList().stream().forEach(el -> {
        	
            String aux =  "('"+el.getDescription()+"', '"+u.getId()+"'),";
            sttm.append(aux);
        });

        sttm.deleteCharAt(sttm.length()-1);
        sttm.append(";");
        System.out.printf("Executing query: %s \n", sttm.toString());
        
        //TODO Salvar no banco

    }
}
