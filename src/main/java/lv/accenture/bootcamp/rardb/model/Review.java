package lv.accenture.bootcamp.rardb.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class Review {

    private String text;
    private User user; //japadoma, ka no sejienes id var izvilkt
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
