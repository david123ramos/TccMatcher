package implementations;

import models.Keyword;
import models.Preference;
import models.Tcc;
import models.User;
import persistence.DatabaseConection;
import repositories.PreferencesRepository;
import servlets.MatcherAPI;
import servlets.SignInServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreferencesRepositoryImpl implements PreferencesRepository {
    private DatabaseConection db = new DatabaseConection();

    @Override
    public Preference get(Preference bean) throws SQLException {
        return null;
    }

    @Override
    public Preference get(long id) throws SQLException {
        return null;
    }

    public List<Preference> getAll(long id) {
        String getAllStatement = "SELECT * FROM PREFERENCES WHERE id_user= '"+id+"' ";
        List<Preference> response = new ArrayList<>();

        ResultSet rs = db.read(getAllStatement);

        if(rs != null) {

            try {

                while(rs.next()) {


                    String description = rs.getString("description");
                    String pid = rs.getString("id");

                    System.out.println("Retrieving preferences from user: "+id);

                    Preference p = new Preference();
                    p.setDescription(description);
                    p.setId(Long.parseLong(pid));
                    response.add(p);
                }
                return response;

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

        return  null;
    }

    @Override
    public void add(User u) {
        StringBuilder sttm =  new StringBuilder("INSERT INTO PREFERENCES (description, id_user) VALUES ");

        u.getPreferenceList().stream().forEach(el -> {
            String aux =  "('"+el.getDescription()+"', '"+u.getId()+"'),";
            sttm.append(aux);
        });

        sttm.deleteCharAt(sttm.length()-1);
        sttm.append(";");
        System.out.printf("Executing query: %s \n", sttm.toString());

        int res = db.update(sttm.toString());

        if(res > 0) {
            System.out.println("Success!");
        }
    }
}
