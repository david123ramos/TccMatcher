package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tcc implements Serializable {
    private long id;
    private String title;
    private String description;
    private String id_user;
    private List<Keyword> keywords = new ArrayList<>();

    public Tcc() {
    }

    public Tcc(String t, String d) {
        this.title = t;
        this.description = d;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public boolean addKeyword(Keyword k) {
        return this.keywords.add(k);
    }
}
