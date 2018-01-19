package util;

import model.Movie;
import model.Rating;
import model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Filehandler {
    public HashMap<Integer, Movie> getMovies() throws IOException {
        String path = "/home/thunders/Code/school/Java/ralph-recommends/api/complete/src/main/resources/x_movies.csv";
        HashMap<Integer, Movie> movies = new HashMap<>();
        Files.lines(Paths.get(path)).skip(1).forEach(movie -> {

            // Regex: https://www.javacodeexamples.com/java-split-string-by-comma-example/740
            String[] splitMovie = movie.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            Movie nm = new Movie();
            nm.setId(Integer.parseInt(splitMovie[0]));
            nm.setTitle(splitMovie[1]);
            nm.setGenre(splitMovie[2]);
            movies.put(Integer.parseInt(splitMovie[0]), nm);
        });

        return movies;
    }

    public Collection<Rating> getRatings() throws IOException {
        String path = "/home/thunders/Code/school/Java/ralph-recommends/api/complete/src/main/resources/x_ratings.csv";
        HashSet<Rating> ratings = new HashSet<>();
        Files.lines(Paths.get(path)).skip(1).forEach(rate -> {
            String[] splitRate = rate.split(",");
            Rating rating = new Rating();
            rating.setRating(Double.parseDouble(splitRate[2]));
            rating.setUserId(Integer.parseInt(splitRate[0]));
            rating.setMovieId(Integer.parseInt(splitRate[1]));
            ratings.add(rating);
        });
        return ratings;
    }

    public HashMap<Integer, User> getUsers() throws IOException {
        String path = "/home/thunders/Code/school/Java/ralph-recommends/api/complete/src/main/resources/x_ratings.csv";
        HashMap<Integer, User> users = new HashMap<>();
        HashSet<Integer> ids = new HashSet<>();

        Files.lines(Paths.get(path)).skip(1).forEach(rate -> {
            String[] splitRate = rate.split(",");
            ids.add(Integer.parseInt(splitRate[0]));
        });

        ids.forEach(id -> {
            User user = new User();
            user.setId(id);
            users.put(id, user);
        });

        return users;
    }
}
