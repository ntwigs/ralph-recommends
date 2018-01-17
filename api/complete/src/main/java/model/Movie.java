package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Movie {
    private int id;
    private String title;
    private HashSet<String> genre;
    private HashSet<Rating> ratings;

    public Movie() {
        this.id = 0;
        this.ratings = new HashSet<>();
        this.title = "";
        this.genre = new HashSet<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genres) {
        String[] genreArr = genres.split("\\|");
        this.genre.addAll(Arrays.asList(genreArr));
    }

    public String getTitle() {
        return this.title;
    }

    public void setRating(Rating rating) {
        this.ratings.add(rating);
    }

    public Collection<Rating> getRatings() {
        return this.ratings;
    }

    public String toString() {
        StringBuilder movie = new StringBuilder();
        movie.append("\n------------\n");
        movie.append("id: ").append(this.id);
        movie.append("\ntitle: ").append(this.title);
        movie.append("\ngenre: ");
        this.genre.forEach(movie::append);
        return movie.toString();
    }
}
