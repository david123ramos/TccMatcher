package servlet;

import com.google.gson.Gson;
import entities.SignUpBean;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MatcherAPISignUp", urlPatterns = {"/MatcherAPI/signup"})
public class SignUpServlet extends HttpServlet{
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        Gson gson = new Gson();

        BufferedReader reader = request.getReader();
 
        SignUpBean s = gson.fromJson(reader, SignUpBean.class);
        PrintWriter out = response.getWriter();
        DatabaseConection db = new DatabaseConection();
         
        
        //TODO: Mudar a gera�ao do ID;
        
        //TODO verficar se o comando � alter table.
        int res = db.update("INSERT INTO MOCK_DATA (id, first_name, last_name"
        + ", email, gender, psw) VALUES('"+s.toString().hashCode()+"', '"+s.getFirstName()+"', '"+s.getLastName()+"',"
                + " '"+s.getEmail()+"','"+s.getGender()+"','"+s.getPassword()+"')");
        
        if(res > 0) {
            out.printf("{\"status\": %s}",res == 0);
            out.flush();
            
        }
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException, UnsupportedEncodingException {
        
        
//        try {
//            DatabaseConection.runMigrations();
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        try ( PrintWriter out = response.getWriter()) {

            out.println("{status: '200', token:'AKSJDA98012IJDNAO8127HDABS',  }");
            
            
        } catch (Exception e) {}
    }
}
