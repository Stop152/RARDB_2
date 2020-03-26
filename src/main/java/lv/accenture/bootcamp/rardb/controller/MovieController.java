package lv.accenture.bootcamp.rardb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import lv.accenture.bootcamp.rardb.repository.RatingRepository;
import lv.accenture.bootcamp.rardb.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.accenture.bootcamp.rardb.model.*;
import lv.accenture.bootcamp.rardb.movieAPI.*;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieAPIService movieAPIService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RatingRepository ratingRepository;

    //TODO: Model objects never should be @Component (at least singleton), therefore not @Autowirable
    // And BTW it is unused
    @Autowired
    private Optional<Movie> movieObject;


    @GetMapping("/movie")
    public String movieIndex(Model model) {
//        Iterable<Movie> movie = movieRepository.findAll();
//        model.addAttribute("movie", movie);
//        movieAPIService.getMovieByTitle("John Wick"); //shis ir testam
//        movieAPIService.searchMoviePhrase("Back to"); //shis ir testam
//        movieAPIService.getMovieById("tt0096895"); //shis ir testam
        return "bestComments";
    }

    @GetMapping("/movie/search")
    public String searchMovies(@RequestParam(value = "movieTitle") String movieTitle, Model model) {

        //TODO: unnecessary split on declaration / initialisation
        MovieSearch movieSearch = new MovieSearch();
        movieSearch = movieAPIService.searchMoviePhrase(movieTitle);
        model.addAttribute("movieSearch", movieSearch);
        model.addAttribute("movieCandidates", movieSearch.getSearch());

        return "search-index";
    }

    @GetMapping("/movie/select_movie/{imdbID}")
    public String selectMovie(@PathVariable(value = "imdbID") String imdbID, Model model) {
        Review review = new Review();
        review.setMovieId(imdbID);
        //TODO: If movie already has reviews (and saved in DB) no need to call
        // external API for getting movie data, we can take it from our DB
        Movie movie = movieAPIService.getMovieById(imdbID);
        model.addAttribute("movie", movie);
        model.addAttribute("userReview", review);
        List<Review> matchedReviews = reviewRepository.findByMovieId(imdbID);
        model.addAttribute("reviews", matchedReviews);

        return "oneMovieComment";
    }

    @PostMapping("/movie/add_review/{imdbID}")
    public String addReview(@PathVariable(value = "imdbID") String imdbID, Model model, @Valid Review review, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("binding result error");
            return "oneMovieComment";
        }

        review.setMovieId(imdbID);
        reviewRepository.save(review);
        Movie movie = movieAPIService.getMovieById(imdbID);
        model.addAttribute("movie", movie);
        List<Review> matchedReviews = reviewRepository.findByMovieId(imdbID);


        model.addAttribute("reviews", matchedReviews);

        return "comment-is-added";

    }

    @GetMapping("/movie/select_movie/{imdbID}/review/{id}")
    public String selectReview(@PathVariable(value = "imdbID") String imdbID, @PathVariable(value = "id") Integer id, Model model) {

        Movie movie = movieAPIService.getMovieById(imdbID);
        model.addAttribute("movie", movie);
        Optional<Review> review = reviewRepository.findById(id);
        model.addAttribute("review", review.get());
        Rating rating = new Rating();
        model.addAttribute("rating", rating);

        return "selected-review";
    }

    @PostMapping("/movie/select_movie/{imdbID}/review/{id}/rate")
    public String rateReview(@PathVariable(value = "imdbID") String imdbID, @PathVariable(value = "id") Integer id,
                             Rating rating, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("binding result error");
            return "redirect:/movie";
        }

        Optional<Review> optional = reviewRepository.findById(id);
        Review review = optional.get();
        rating = ratingRepository.save(rating);
        review.addRating(rating, review);

        //TODO: Here it is not necessary to assign new value after save (review = ...)
        review = reviewRepository.save(review);

        return "redirect:/movie";

    }


//    @GetMapping("/movie/bestComments")
//    public String topRatings (Model model) {
//     //   List<Review> movieList = (List<Review>) movieRepository.findTop10ReviewByRating();
//        Iterable<Review> movies = movieRepository.findTop10ReviewByRating();
//        model.addAttribute("bestComments", movies);
//        return "bestComment";
//    }


}
