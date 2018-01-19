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

    public boolean hasRated(int id) {
        for (Rating rating : this.ratings) {
            if (rating.getMovieId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNotRated(int movie_id) {
        boolean exists = true;

        for (Rating rating : this.ratings) {
            if (rating.getMovieId() == movie_id) {
                exists = false;
            }
        }

        return exists;
    }

    public Collection<Rating> getRatings() {
        return this.ratings;
    }


}
