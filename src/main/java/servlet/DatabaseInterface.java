package servlet;


public interface DatabaseInterface {
    
    public void create(String query);
    public void read(String query);
    public void update(String query);
    public void delete(String query);
}
