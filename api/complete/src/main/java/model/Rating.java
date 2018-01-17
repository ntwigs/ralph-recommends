package model;

public class Rating {
    private int userId;
    private int movieId;
    private double rating;

    public Rating() {
        this.userId = 0;
        this.rating = 0;
        this.movieId = 0;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public void setMovieId(int id) {
        this.movieId = id;
    }

    public double getRating() {
        return this.rating;
    }

    public int getUser() {
        return this.userId;
    }

    public int getMovieId() {
        return this.movieId;
    }
}
