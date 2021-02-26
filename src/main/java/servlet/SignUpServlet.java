package servlet;

import com.google.gson.Gson;
import entities.User;
import implementations.UserRepositoryImpl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        PrintWriter out = response.getWriter();
        
        Gson gson = new Gson();
        BufferedReader reader = request.getReader();

        User userBean = gson.fromJson(reader,  User.class);
        UserRepositoryImpl repository = new UserRepositoryImpl();

        int res = repository.add(userBean);

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
