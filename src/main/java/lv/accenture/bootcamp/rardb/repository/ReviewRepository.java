package lv.accenture.bootcamp.rardb.repository;

import lv.accenture.bootcamp.rardb.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
     List<Review> findByMovieId(String movieID);

     @Query(value = "select rew.id as reviewId, rew.text as text, m.title as movieTitle, m.year as movieYear, rew.user_name as user_name, avg(rat.value) as averageRating from review rew \n" +
             "    join rating rat\n" +
             "        on rew.id = rat.review_id\n" +
             "    join movie m on rew.movie_id = m.imdbid\n" +
             "    group by rew.id\n" +
             "    order by avg(rat.value) desc\n" +
             "    limit 10", nativeQuery = true)
     List<Object[]> getTop10();


     List<Review> findByUserName(String username);





//    @Query("Select c FROM Review c Where c.movieTitle LIKE %:title%")
//    List<Review> findByIbmId(@Param(value = "id") String id);
//    @Query("Select c FROM Review c Where c.movieTitle LIKE %:title% AND c.movieTitle LIKE %:title%")
//    Review findByTitle(@Param(value = "title") String title, @Param(value = "title") String title);
//    default Long calculateAverageRating(List<Review> movieRatingList) {
//        int rankSum = 0;
//        int rankCalculation = movieRatingList.size()+1;
//        int averageRank;
//        if (movieRatingList.size() > 0) {
//            for (int i = 0; i < movieRatingList.size(); i++) {
//                rankSum = rankSum + movieRatingList.get(i).getRating();
//                System.out.println("Average rank " + movieRatingList.get(i).toString());
//            }
//            averageRank = (long) (rankSum / rankCalculation);
//        } else {
//            averageRank = 0L;
//        }
//        return averageRank;
//    }
}



