package util;

import model.Rating;
import model.User;

public class Euclidean {
    public double calculate(User usera, User userb) {
        double sim = 0.0;
        int cnt_sim = 0;

        for (Rating aRating: usera.getRatings()) {
            for (Rating bRating : userb.getRatings()) {
                if (aRating.getMovieId() == bRating.getMovieId() && aRating.getRating() != 0 && bRating.getRating() != 0) {
                    sim += Math.pow(aRating.getRating() - bRating.getRating(), 2);
                    cnt_sim++;
                }
            }
        }
        if (cnt_sim == 0) return 0;
        return 1.0 / (1.0 + sim);
    }
}
