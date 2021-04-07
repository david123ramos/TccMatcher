package persistence;

import java.io.*;
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
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/tccmatcher";
    //static final String DB_URL = "jdbc:mariadb://6f16cb6d481e.ngrok.io/MOCK_DATA";
    static final String USER = "root";
    static final String PASS = "admin";
    
    private static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException, IOException {

        //STEP 2: Register JDBC driver
        Class.forName("org.mariadb.jdbc.Driver");
        if(DatabaseConection.itsInProduction()) {

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            URI jdbUri = new URI(System.getenv("JAWSDB_MARIA_URL"));

            String username = jdbUri.getUserInfo().split(":")[0];
            String password = jdbUri.getUserInfo().split(":")[1];
            String port = String.valueOf(jdbUri.getPort());
            String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

            return DriverManager.getConnection(jdbUrl, username, password);

        }
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connection established......");
        //runMigrations(conn);
        return conn;
    }

    public static void runMigrations() throws URISyntaxException, SQLException, IOException, ClassNotFoundException {
        Connection conn = getConnection();
        System.out.println("Connection established......");
        System.out.println("Executing migrations...");

        ScriptRunner sr = new ScriptRunner(conn);
        Reader reader = new BufferedReader(new InputStreamReader(DatabaseConection.class.getClassLoader().getResourceAsStream("MOCK_DATA.sql"), "UTF-8"));

        sr.runScript(reader);
    }

    public static void runMigrations(Connection conn) throws URISyntaxException, SQLException, IOException, ClassNotFoundException {

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(String query) {
        try {
            Connection conn = DatabaseConection.getConnection();
            
            Statement stmt = conn.createStatement();
            
            return stmt.executeUpdate(query);
        } catch (URISyntaxException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

    @Override
    public void delete(String query) {}

    public static boolean itsInProduction(){
        return System.getenv("ENV") != null;
    }
}
