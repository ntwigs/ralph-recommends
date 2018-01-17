package controller;

import model.*;
import util.Euclidean;

import java.io.IOException;
import java.util.HashMap;

public class Start {
    private UserDB udb;
    private MovieDB mdb;
    private RatingDB rdb;
    private HashMap<Integer, Double> similarity;
    private HashMap<Integer, Double> wsdb;
    private HashMap<Integer, Double> simsums;
    private int id;

    public Start(int id) {
        this.id = id;
        udb = new UserDB();
        mdb = new MovieDB();
        rdb = new RatingDB();
        similarity = new HashMap<>();
        wsdb = new HashMap<>();
        simsums = new HashMap<>();
    }


    public HashMap<String, Double> now() throws IOException {
        udb.storeUsers();
        mdb.storeMovies();
        rdb.storeRatings();

        addRatingsToUser();
        calculateSimilarity();
        calculateWeighted();
        calculateSimilaritySum();
        return getMovieRecommendation();

    }

    private void addRatingsToUser() {
        rdb.getRatings().forEach(rating ->
                udb.getUser(rating.getUser()).addRating(rating)
        );
    }

    private void calculateSimilarity() {
        Euclidean eu = new Euclidean();
        User user = udb.getUser(id);

        udb.getUsers().forEach((user_id, userb) -> {
            if (user_id != id) {
                double sim = eu.calculate(user, userb);
                similarity.put(user_id, sim);
            }
        });
    }

    private void calculateWeighted() {
        similarity.forEach((id, sim) -> {
            if (this.id != id) {
                udb.getUser(id).getRatings().forEach(rating -> {
                    int movieId = rating.getMovieId();
                    if (wsdb.containsKey(movieId)) {
                        wsdb.replace(movieId, sim * rating.getRating() + wsdb.get(movieId));
                    } else {
                        wsdb.put(movieId, sim * rating.getRating());
                    }
                });
            }
        });
    }

    private void calculateSimilaritySum() {
        for (Rating rating : rdb.getRatings()) {
            int userId = rating.getUser();
            if (userId != id && rating.getRating() != 0) {
                int movieId = rating.getMovieId();
                double sim = similarity.get(userId);
                if (simsums.containsKey(movieId)) {
                    simsums.replace(movieId, simsums.get(movieId) + sim);
                } else {
                    simsums.put(movieId, sim);
                }
            }
        }
    }

    private HashMap<String, Double> getMovieRecommendation() {
        HashMap<String, Double> recommendations = new HashMap<>();

        simsums.forEach((movie_id, similarity) -> {
            String title = mdb.getMovies().get(movie_id).getTitle();
            double weight = wsdb.get(movie_id);
            double total = weight/similarity;
            recommendations.put(title, total);
        });

        return recommendations;
    }
}

