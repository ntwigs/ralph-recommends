package controller;

import model.*;
import util.Euclidean;

import java.io.IOException;
import java.util.Collections;
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


    public HashMap<Integer, Double> recUsers() throws IOException {
        udb.storeUsers();
        mdb.storeMovies();
        rdb.storeRatings();

        addRatingsToUser();
        calculateSimilarity();

        return similarity;
    }

    private void addRatingsToUser() {
        rdb.getRatings().forEach(rating -> {
            udb.getUser(rating.getUser()).addRating(rating);
        });
    }

    private void calculateSimilarity() {
        Euclidean eu = new Euclidean();
        User userA = udb.getUser(id);

        for (User userB : udb.getUsers().values()) {
            similarity.put(userB.getId(), eu.calculate(userA, userB));
        }
    }

    private void calculateWeighted() {
        mdb.getMovies().forEach((id, movie) -> {
            if (!wsdb.containsKey(id)) {
                wsdb.put(id, 0.0);
            }
        });

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
            if (userId != id) {
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
        double max = Collections.max(wsdb.values());

        simsums.forEach((movie_id, similarity) -> {
            if (udb.getUser(this.id).hasNotRated(movie_id)) {
                String title = mdb.getMovies().get(movie_id).getTitle();
                double weight = wsdb.get(movie_id);
                double total = (weight / similarity);
                recommendations.put(title, total);
            }
        });

        return recommendations;
    }
}

