package servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseConection implements DatabaseInterface{
    
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI jdbUri = new URI(System.getenv("JAWSDB_MARIA_URL"));

        String username = jdbUri.getUserInfo().split(":")[0];
        String password = jdbUri.getUserInfo().split(":")[1];
        String port = String.valueOf(jdbUri.getPort());
        String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

        return DriverManager.getConnection(jdbUrl, username, password);
    }
    
    public static void runMigrations() throws URISyntaxException, SQLException, FileNotFoundException{
        Connection conn = getConnection();
        System.out.println("Connection established......");
        System.out.println("Executing migrations...");
        
        ScriptRunner sr = new ScriptRunner(conn);
        Reader reader = new BufferedReader(new FileReader("resources/MOCK_DATA.sql"));
        
        sr.runScript(reader);
    }
    
    @Override
    public void create(String query) {
       
    }

    @Override
    public void read(String query) {
        
    }

    @Override
    public void update(String query) {
        
    }

    @Override
    public void delete(String query) {
        
    }
}
