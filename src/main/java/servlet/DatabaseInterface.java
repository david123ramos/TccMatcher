package servlet;

import java.sql.ResultSet;


public interface DatabaseInterface {
    
    public void create(String query);
    public ResultSet read(String query);
    public int update(String query);
    public void delete(String query);
}
