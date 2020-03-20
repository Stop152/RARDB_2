package lv.accenture.bootcamp.rardb.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashMap;

@Entity
public class Review {

    @Size(min = 2, max = 256, message ="The comment is too long")
    private String text;
 //   private User user; //japadoma, ka no sejienes id var izvilkt
    @Positive(message ="The rank should be between 1 and 10. Please enter the appropriate figure")
    private Double rating;
    @Id
    private Integer id;

    private HashMap<Integer, Object> reviewMap = new HashMap<Integer, Object>();

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

//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
