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

        setHeaders(response);
        
        Gson gson = new Gson();

        BufferedReader reader = request.getReader();
 
        SignUpBean s = gson.fromJson(reader, SignUpBean.class);
        PrintWriter out = response.getWriter();
        DatabaseConection db = new DatabaseConection();
         
        
        //TODO: Mudar a geração do ID;
        
        //TODO verficar se o comando alter table.
        int res = db.update("INSERT INTO MOCK_DATA (id, first_name, last_name"
        + ", email, gender, psw) VALUES('"+s.toString().hashCode()+"', '"+s.getFirstName()+"', '"+s.getLastName()+"',"
                + " '"+s.getEmail()+"','"+s.getGender()+"','"+s.getPassword()+"')");

        System.out.println("Consulta terminou");

        if(res > 0) {
            out.printf("{\"status\": %s}",res);
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


         setHeaders(response);
        
        try ( PrintWriter out = response.getWriter()) {

            out.println("{status: '200', token:'AKSJDA98012IJDNAO8127HDABS',  }");
            
            
        } catch (Exception e) {}
    }

    private void setHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With, Accept");
    }
}
