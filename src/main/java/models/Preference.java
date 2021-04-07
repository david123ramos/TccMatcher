package models;

import java.io.Serializable;

public class Preference implements Serializable {
    private  String description;
    private long id;
    
    public Preference() {
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
