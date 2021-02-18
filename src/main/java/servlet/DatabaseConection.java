package servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseConection implements DatabaseInterface{
    
    
    //  Database credentials on test server (Docker)
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3306/";
    static final String USER = "root";
    static final String PASS = "admin";
    
    private static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
        
        //STEP 2: Register JDBC driver
        Class.forName("org.mariadb.jdbc.Driver");

        //STEP 3: Open a connection
        System.out.println("Connecting to a selected database...");

        URI jdbUri = new URI(System.getenv("JAWSDB_MARIA_URL"));

        String username = jdbUri.getUserInfo().split(":")[0];
        String password = jdbUri.getUserInfo().split(":")[1];
        String port = String.valueOf(jdbUri.getPort());
        String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

        return DriverManager.getConnection(jdbUrl, username, password);
    }
    
    public static void runMigrations() throws URISyntaxException, SQLException, FileNotFoundException, ClassNotFoundException, UnsupportedEncodingException{
        Connection conn = getConnection();
        System.out.println("Connection established......");
        System.out.println("Executing migrations...");

        ScriptRunner sr = new ScriptRunner(conn);
        Reader reader = new BufferedReader(new InputStreamReader(DatabaseConection.class.getClassLoader().getResourceAsStream("MOCK_DATA.sql"), "UTF-8"));

        sr.runScript(reader);
    }
    
    @Override
    public void create(String query) {
       
    }

    @Override
    public ResultSet read(String query) {
        try {
            Connection conn = DatabaseConection.getConnection();
            
            Statement stmt = conn.createStatement();
            //usado apenas no ambiente de testes;
            //stmt.executeQuery("use tccmatcher");
            return stmt.executeQuery(query);
        } catch (URISyntaxException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void update(String query) {
        
    }

    @Override
    public void delete(String query) {
        
    }
}
