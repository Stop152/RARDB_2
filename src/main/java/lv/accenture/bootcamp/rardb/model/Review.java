package lv.accenture.bootcamp.rardb.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {

    @Size(min = 2, max = 256, message = "The comment is too long")
    private String text;
    //   private User user; //japadoma, ka no sejienes id var izvilkt
    @ElementCollection
    private List<Rating> ratingList;
    @Id
    @GeneratedValue
    private Integer id;
    private String movieId;


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

//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public void addRating (Rating rating, Review review) {

        if (review.getRatingList() == null) {
            ratingList = new ArrayList<>();
        }

        rating.setReviewId(review.getId());
        ratingList.add(rating);
    }



//    public Double getAverageRating() {
//        Double sum = 0.0;
//        for(int i = 0; i < ratingList.size(); i++) {
//            sum =+ ratingList.get(i);
//        }
//        sum = sum / ratingList.size();
//        return sum;
//    }


}
