package servlets;

import com.google.gson.Gson;
import models.User;
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

        User res = repository.add(userBean);
        System.out.println("Consulta terminou");

        if(res != null) {
            out.write(gson.toJson(res));
        }else {
            response.sendError(500, "Intern Error");
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
