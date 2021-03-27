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


@WebServlet(name = "MatcherAPIUser", urlPatterns = {"/MatcherAPI/user"})
public class UserServlet extends HttpServlet{

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPut(req, resp);
        setHeaders(resp);
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        BufferedReader reader = req.getReader();
        User userBean = gson.fromJson(reader,  User.class);
        System.out.println(userBean.getInstitution());
        System.out.println(userBean.getPreferenceList().size()); // TODO
        UserRepositoryImpl repository = new UserRepositoryImpl();
        repository.addPreferences(userBean); //TODO
        repository.update(userBean); // TODO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException, UnsupportedEncodingException {

        setHeaders(response);

        try ( PrintWriter out = response.getWriter()) {

            out.println("{\"status\": \"200\", \"token\":\"AKSJDA98012IJDNAO8127HDABS\"}");


        } catch (Exception e) {}
    }

    private void setHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With, Accept");
    }
}
