package model;

import java.util.Collection;
import java.util.HashSet;

public class User {
    private int id;
    private HashSet<Rating> ratings;

    public User() {
        this.id = 0;
        this.ratings = new HashSet<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    public boolean matches(Rating rating) {
        boolean test = false;

        for (Rating rating1 : ratings) {
            if (rating1.getMovieId() == rating.getMovieId() && rating1.getRating() != 0 && rating.getRating() != 0) {
               test = true;
            }
        }

        return test;
    }

    public Collection<Rating> getRatings() {
        return this.ratings;
    }


}
