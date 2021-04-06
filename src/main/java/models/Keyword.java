package models;

import java.io.Serializable;

public class Keyword implements Serializable {

    private long id;
    private String title;
    private long id_tcc;

    public long getId_tcc() {
        return id_tcc;
    }

    public void setId_tcc(long id_tcc) {
        this.id_tcc = id_tcc;
    }



    public Keyword(){}

    public Keyword(String t) {
        this.title = t;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
