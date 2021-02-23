package servlet;

import com.google.gson.Gson;
import entities.SignInBean;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MatcherAPIAcess", urlPatterns = {"/MatcherAPI/signin"})
public class SignInServlet extends HttpServlet {
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        Gson gson = new Gson();

        BufferedReader reader = request.getReader();
 
        SignInBean s = gson.fromJson(reader, SignInBean.class);
        PrintWriter out = response.getWriter();
        DatabaseConection db = new DatabaseConection();
         
        ResultSet rs = db.read("SELECT id, first_name, last_name, email FROM MOCK_DATA WHERE email='"+s.getEmail()+"' and psw='"+s.getPassword()+"';");
        try {
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                String email = rs.getString("email");

                out.printf("{\"userid\": \"%s\", \"fist_name\": \"%s\","
                        + "\"last_name\": \"%s\",\"email\": \"%s\", \"authorized\": \"%s\"}", id, first, last, email, true);



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
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException, UnsupportedEncodingException {
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        try ( PrintWriter out = response.getWriter()) {

            out.println("{status: '200', token:'AKSJDA98012IJDNAO8127HDABS',  }");
            
            
        } catch (Exception e) {}
    }
}
