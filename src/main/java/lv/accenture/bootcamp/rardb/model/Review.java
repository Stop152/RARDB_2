package lv.accenture.bootcamp.rardb.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {


    @Size(min = 2, max = 2000, message = "Please, make your review shorter. You exceeded maximum character size: 900.")
    private String text;

    @Id
    @GeneratedValue
    private Integer id;
    private String movieId;

    @Transient
    private Double averageRating = 0.0;
    @Transient
    private Integer ratingAmount;

    private String userName;

    private String movieTiltle;

    private String movieYear;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMovieTiltle() {
        return movieTiltle;
    }

    public void setMovieTiltle(String movieTiltle) {
        this.movieTiltle = movieTiltle;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public Integer getRatingAmount() {
        return ratingAmount;
    }

    public void setRatingAmount(Integer ratingAmount) {
        this.ratingAmount = ratingAmount;
    }
}
