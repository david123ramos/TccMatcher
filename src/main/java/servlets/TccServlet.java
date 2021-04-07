package servlets;

import com.google.gson.Gson;
import implementations.KeywordRepositoryImpl;
import implementations.PreferencesRepositoryImpl;
import implementations.TccRepositoryImpl;
import implementations.UserRepositoryImpl;
import models.MatchedTcc;
import models.Preference;
import models.Tcc;
import models.User;
import repositories.KeywordRepository;
import repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;


@WebServlet(name = "MatcherAPITCC", urlPatterns = {"/MatcherAPI/tcc"})
public class TccServlet extends HttpServlet {

    private void getMatched(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setHeaders(resp);
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        String param = req.getParameter("userid");

        PreferencesRepositoryImpl preferencesRepository = new PreferencesRepositoryImpl();

        List<Preference> preferenceList = preferencesRepository.getAll(Long.parseLong(param));

        KeywordRepositoryImpl keywordRepository = new KeywordRepositoryImpl();
        List<Tcc> tccSet = new ArrayList<>(); //remove tccs repetidos.
        Set<Long> tccIdSet = new HashSet<>();

        preferenceList.stream().forEach(preference -> {
            List<Tcc> aux =  keywordRepository.getTccsfromKeyWord(preference.getDescription());

            aux.removeIf(t -> !tccIdSet.add(t.getId()));

            tccSet.addAll(aux);

        });


        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        List<User> userList = new ArrayList<>();
        tccSet.stream().forEach(tcc -> {
            try {

                User u = userRepository.get(Long.parseLong(tcc.getId_user()));
                u.setTcc(tcc);
                userList.add(u);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        MatchedTcc responseJson = new MatchedTcc();
        responseJson.setYour_id(Long.parseLong(param));
        responseJson.setMatchedTccs(userList);
        responseJson.setYour_preferences(preferenceList);

        if(preferenceList != null && tccSet != null) {
            out.write(gson.toJson(responseJson));
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setHeaders(resp);
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        String param = req.getParameter("userid");
        String match = req.getParameter("match");

        if(match != null) {
            this.getMatched(req, resp);
            return;
        }

        TccRepositoryImpl repository = new TccRepositoryImpl();

        List<Tcc> updatedTccLists = repository.getAll(param);

        System.out.println("Consulta terminou");
        out.write(gson.toJson(updatedTccLists));

    }

    /**
     *      {
     *          title: "",
     *          description: "",
     *          keywords: [
     *              {title: ""},
     *              {title: ""},
     *              {title: ""}
     *          ],
     *          id_user: ""
     *      }
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setHeaders(resp);
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        BufferedReader reader = req.getReader();

        Tcc tccbean = gson.fromJson(reader, Tcc.class);

        TccRepositoryImpl repository = new TccRepositoryImpl();
        KeywordRepository kwRepo = new KeywordRepositoryImpl();

        Tcc aux = repository.add(tccbean);

        aux.getKeywords().stream().forEach(el -> {
            el.setId_tcc(aux.getId());
            kwRepo.add(el);
        });

        System.out.println("Cadastro de keywords finalizou");

        List<Tcc> updatedTccLists = repository.getAll(tccbean.getId_user());

        System.out.println("Consulta terminou");

        if(updatedTccLists != null) {
            out.write(gson.toJson(updatedTccLists));
        }else {
            resp.sendError(500, "Intern Error");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void setHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With, Accept");
    }
}
