package models;

import java.io.Serializable;

public class Preference implements Serializable {
    private  String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
