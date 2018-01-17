package model;

import util.Filehandler;

import java.io.IOException;
import java.util.HashSet;

public class RatingDB {
    private HashSet<Rating> ratings;

    public RatingDB() {
        ratings = new HashSet<>();
    }

    public HashSet<Rating> getRatings() {
        return this.ratings;
    }

    public void storeRatings() throws IOException {
        Filehandler fh = new Filehandler();
        this.ratings.addAll(fh.getRatings());
    }

}
