package servlet;

import java.sql.ResultSet;


public interface DatabaseInterface {
    
    public void create(String query);
    public ResultSet read(String query);
    public void update(String query);
    public void delete(String query);
}
