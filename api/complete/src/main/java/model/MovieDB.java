package model;

import util.Filehandler;

import java.io.IOException;
import java.util.HashMap;

public class MovieDB {
    private HashMap<Integer, Movie> movies;

    public MovieDB() {
        this.movies = new HashMap<>();
    }

    public void storeMovies() throws IOException {
        Filehandler fh = new Filehandler();
        this.movies = fh.getMovies();
    }

    public HashMap<Integer, Movie> getMovies() {
        return this.movies;
    }

    private Movie getMovie(int id) {
        return movies.get(id);
    }
}
