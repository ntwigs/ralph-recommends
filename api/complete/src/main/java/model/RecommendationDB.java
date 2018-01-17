package model;

import java.util.HashMap;

public class RecommendationDB {
    private HashMap<Integer, User> users;

    public RecommendationDB() {
        this.users = new HashMap<>();
    }
}
