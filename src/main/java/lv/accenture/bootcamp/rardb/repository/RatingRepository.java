package lv.accenture.bootcamp.rardb.repository;

import lv.accenture.bootcamp.rardb.model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {

    List<Rating> findByReviewId(Integer id);

}
