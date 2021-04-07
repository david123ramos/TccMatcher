package models;

import java.util.ArrayList;
import java.util.List;

public class MatchedTcc {

    private long your_id;
    private List<User> matchedTccs = new ArrayList<>();
    private List<Preference> your_preferences = new ArrayList<>();

    public MatchedTcc(){}

    public long getYour_id() {
        return your_id;
    }

    public void setYour_id(long your_id) {
        this.your_id = your_id;
    }

    public List<User> getMatchedTccs() {
        return matchedTccs;
    }

    public void setMatchedTccs(List<User> matchedTccs) {
        this.matchedTccs = matchedTccs;
    }

    public List<Preference> getYour_preferences() {
        return your_preferences;
    }

    public void setYour_preferences(List<Preference> your_preferences) {
        this.your_preferences = your_preferences;
    }
}
