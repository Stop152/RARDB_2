package lv.accenture.bootcamp.rardb.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {


    @Size(min = 2, max = 900, message = "Please, make your review shorter. You exceeded maximum character size: 900.")
    private String text;
    //   private User user; //japadoma, ka no sejienes id var izvilkt

    @Id
    @GeneratedValue
    private Integer id;
    private String movieId;


 //   private Movie movie;

    @Transient
    private Double averageRating = 0.0;


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


    //    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }

  /*  public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }*/

   /* public void addRating(Rating rating, Review review) {

        if (review.getRatingList() == null) {
            ratingList = new ArrayList<>();
        }

        rating.setReviewId(review.getId());
        ratingList.add(rating);
    }*/


  /*  public Double getAverageRating() {


        averageRating = 0.0;
        Rating rating;
        for (int i = 0; i < ratingList.size(); i++) {
            rating = ratingList.get(i);
            averageRating = averageRating + rating.getValue();

        }
        averageRating = averageRating / ratingList.size();
        return averageRating;
    }*/

}
