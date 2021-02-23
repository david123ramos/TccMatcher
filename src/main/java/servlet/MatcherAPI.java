package servlet;

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


@WebServlet(name = "MatcherAPI", urlPatterns = {"/MatcherAPI"})
public class MatcherAPI extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException, UnsupportedEncodingException {
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        try ( PrintWriter out = response.getWriter()) {

            out.println("{status: '200', token:'AKSJDA98012IJDNAO8127HDABS',  }");
            String userId = request.getParameter("id");
        
            if(userId != null) {
                System.out.println("ID DO USUÁRIO --> "+userId);
                DatabaseConection db = new DatabaseConection();
                ResultSet rs = db.read("SELECT id, first_name, last_name, email FROM MOCK_DATA WHERE id='"+userId+"';");
                try {
                    //STEP 5: Extract data from result set
                    while(rs.next()){
                        //Retrieve by column name
                        int id  = rs.getInt("id");
                        String first = rs.getString("first_name");
                        String last = rs.getString("last_name");
                        String email = rs.getString("email");

                        out.printf("{\"userid\": \"%s\", \"fist_name\": \"%s\","
                                + "\"last_name\": \"%s\",\"email\": \"%s\"}", id, first, last, email);


                      
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MatcherAPI.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                     rs.close();
                }
            }
            
        } catch (Exception e) {}
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        String type = request.getParameter("type");
        
        if(type.equals("signin")) {
            
        }else if(type.equals(("signup"))) {
            
        }

        
        try ( PrintWriter out = response.getWriter()) {

            out.println("{status: '200', token:'AKSJDA98012IJDNAO8127HDABS',  }");
        }
    }
}
