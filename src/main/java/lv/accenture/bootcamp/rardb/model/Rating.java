package lv.accenture.bootcamp.rardb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating {

    //TODO: access modifiers ?
    Double value;
    Integer reviewId;
    @Id
    @GeneratedValue
    Integer ratingId;

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

}

