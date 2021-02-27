package servlets;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

            response.setStatus(405);
            out.write("{\"msg\": \"Not allowed\"}");

        } catch (Exception e) {}
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");

        try ( PrintWriter out = response.getWriter()) {
            response.setStatus(405);
            out.write("{\"msg\": \"Not allowed\"}");
        }
    }
}
