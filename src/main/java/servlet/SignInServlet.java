package servlet;

import com.google.gson.Gson;
import entities.SignInBean;
import entities.User;
import implementations.UserRepositoryImpl;
import org.apache.catalina.filters.ExpiresFilter;

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
        setHeaders(response);
        
        Gson gson = new Gson();

        BufferedReader reader = request.getReader();
 
        SignInBean s = gson.fromJson(reader, SignInBean.class);
        PrintWriter out = response.getWriter();
        UserRepositoryImpl repository = new UserRepositoryImpl();

        try {
            User foundUser = repository.get(s);

            if(foundUser != null) {
                out.write(gson.toJson(foundUser));

            }else {
                out.write("{\"status\": \"404\", \"msg\": \"User not found\" }");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException, UnsupportedEncodingException {
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
