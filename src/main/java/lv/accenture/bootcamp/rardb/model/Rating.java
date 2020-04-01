package lv.accenture.bootcamp.rardb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating {

    //TODO: access modifiers ?
    private Double value;
    private Integer reviewId;
    @Id
    @GeneratedValue
    private Integer ratingId;

    private String userName;

    //TODO : in such implementation any user can rate as much he wants (inluding review author)
    // good point is to save also userId (Spring Security has that data) and check before show
    // appropriate rating <form>


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

