package lv.accenture.bootcamp.rardb.repository;

import lv.accenture.bootcamp.rardb.model.Movie;
import lv.accenture.bootcamp.rardb.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

    public interface MovieRepository extends CrudRepository<Movie,String> {

    @Query("Select c FROM Review c where c.movieTitle LIKE %:title%")
    Movie findByTitle(@Param(value = "title") String title);


    @Query(value = "SELECT*FROM Movie ORDER BY averageRank DESC LIMIT 10", nativeQuery = true)
    Iterable<Movie> findTopRatings();

    default void addRanking(List<Movie> movieRatinglist) {
        for (int i = 0; i < movieRatinglist.size(); i++) {
            movieRatingList.get(i).setRanking(i + 1);
        }


    }

    @Query("Select c FROM Movie c where c.movieTitle LIKE %:title%")
    Movie findByTitle (@Param(value = "title") String title);


}