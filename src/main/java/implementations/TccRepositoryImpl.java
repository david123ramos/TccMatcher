package implementations;

import jwt.JwtController;
import models.Keyword;
import models.SignInBean;
import models.Tcc;
import models.User;
import org.apache.ibatis.jdbc.SQL;
import persistence.DatabaseConection;
import repositories.TccRepository;
import servlets.MatcherAPI;
import servlets.SignInServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TccRepositoryImpl implements TccRepository {

    private DatabaseConection db = new DatabaseConection();

    @Override
    public Tcc get(Tcc bean) throws SQLException {
        ResultSet rs = db.read("SELECT id, title, description, id_user FROM TCC WHERE " +
                "title='"+bean.getTitle()+"' and description='"+bean.getDescription()+"';");

        if(rs != null) {

            try {

                while(rs.next()) {
                    long id = rs.getInt("id");
                    String title = rs.getString("title");
                    String desc = rs.getString("description");
                    String id_user = rs.getString("id_user");
                    Tcc tcc = new Tcc(title, desc);
                    tcc.setId(id);
                    tcc.setId_user(id_user);
                    return tcc;
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
    public Tcc get(long id) throws SQLException {
        return null;
    }

    public List<Tcc> getAll(String id_user) {
        String getAllStatement = "SELECT * FROM TCC WHERE id_user= '"+id_user+"' ";
        List <Tcc> response = new ArrayList<>();

        ResultSet rs = db.read(getAllStatement);

        if(rs != null) {

            try {

                while(rs.next()) {

                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String id = rs.getString("id");

                    System.out.println("Retrieving keywords from tcc: "+title+" "+description+" "+" "+id);

                    Tcc t = new Tcc(title, description);
                    List<Keyword> tccKeyWords = this.getKeywordList(id);

                    t.setKeywords(tccKeyWords);
                    t.setId(Long.parseLong(id));
                    response.add(t);
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

    public List<Keyword> getKeywordList(String tccId) {

        String selectStatementGetKeywordList = "SELECT * FROM KEYWORD WHERE id_tcc= '"+tccId+"' ";
        List <Keyword> keywords = new ArrayList<>();
        ResultSet rs = db.read(selectStatementGetKeywordList);

        if(rs != null) {

            try {

                while(rs.next()) {

                    String title = rs.getString("title");
                    Keyword temp =  new Keyword(title);
                    keywords.add(temp);
                }

                return keywords;

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
    public Tcc add(Tcc tcc) {
        String insertStatement = "INSERT INTO TCC (title, description , id_user) VALUES(''{0}'', ''{1}'', ''{2}'')";

        //TODO: Mudar a geracao do ID;

        Object[] params = new Object[] {tcc.getTitle(), tcc.getDescription(), tcc.getId_user()};
        String sql = MessageFormat.format(insertStatement, params);

        System.out.printf("Executing query: %s", sql);

        int res = db.update(sql);

        if(res > 0) {
            try {
                Tcc response  = this.get(tcc);
                response.setKeywords(tcc.getKeywords());
                return  response;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void update(Tcc tcc) {

    }

    @Override
    public void remove(Tcc tcc) {

    }

    @Override
    public void addPreference(Tcc tcc) {

    }
}
