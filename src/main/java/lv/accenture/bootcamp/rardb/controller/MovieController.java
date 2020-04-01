package lv.accenture.bootcamp.rardb.controller;

import java.util.*;

import javax.validation.Valid;

import lv.accenture.bootcamp.rardb.repository.MovieRepository;
import lv.accenture.bootcamp.rardb.repository.RatingRepository;
import lv.accenture.bootcamp.rardb.repository.ReviewRepository;
import lv.accenture.bootcamp.rardb.repository.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lv.accenture.bootcamp.rardb.model.*;
import lv.accenture.bootcamp.rardb.movieAPI.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/movie")
    public String movieIndex(Model model) {

        List<Object[]> reviews = reviewRepository.getTop10();
//        //1.variants
//        List<TopReview> topReviews = reviews.get();
//
//
        List<TopReview> topReviews = new ArrayList<>();
        for (int i = 0; i < reviews.size(); i++) {
            Object[] reviewData = reviews.get(i);
            TopReview topReview = new TopReview();
            topReview.setReviewId((Integer) reviewData[0]);
            topReview.setMovieTitle((String) reviewData[1]);
            topReview.setAverageRating((Double) reviewData[2]);

            topReviews.add(topReview);
        }

        model.addAttribute("reviews", topReviews);


        return "bestComments";
    }

    @GetMapping("/movie/search")
    public String searchMovies(@RequestParam(value = "movieTitle") String movieTitle, Model model) {


        MovieSearch movieSearch = movieAPIService.searchMoviePhrase(movieTitle);
        model.addAttribute("movieSearch", movieSearch);
        model.addAttribute("movieCandidates", movieSearch.getSearch());

        return "search-index";
    }

    @GetMapping("/movie/select_movie/{imdbID}")
    public String selectMovie(@PathVariable(value = "imdbID") String imdbID, Model model) {
        Review review = new Review();
        review.setMovieId(imdbID);

        Optional<Movie> optional;
        Movie movie;
        if (movieRepository.existsById(imdbID)) {
            optional = movieRepository.findById(imdbID);
            movie = optional.get();
        } else {
            movie = movieAPIService.getMovieById(imdbID);
        }
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


        Movie movie;
        if (movieRepository.existsById(imdbID)) {
            Optional<Movie> optional = movieRepository.findById(imdbID);
            movie = optional.get();
        } else {
            movie = movieAPIService.getMovieById(imdbID);
        }

        review.setMovieId(imdbID);

        reviewRepository.save(review);


        model.addAttribute("movie", movie);
        List<Review> matchedReviews = reviewRepository.findByMovieId(imdbID);

        if (movieRepository.existsById(imdbID) == false) {
            movieRepository.save(movie);
        }


        model.addAttribute("reviews", matchedReviews);

        return "comment-is-added";

    }

    @GetMapping("/movie/select_movie/{imdbID}/review/{id}")
    public String selectReview(@PathVariable(value = "imdbID") String imdbID, @PathVariable(value = "id") Integer id, Model model) {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        boolean userCanRate = true;

        List<Rating> allRatingsForTheReview = ratingRepository.findByReviewId(id);
        for (int i = 0; i < allRatingsForTheReview.size(); i++) {
            if (allRatingsForTheReview.get(i).getUserName().equals(username)){
                userCanRate = false;
                break;
            }
        }

        model.addAttribute("userCanRate", userCanRate);

        Optional<Movie> optional = movieRepository.findById(imdbID);
        Movie movie = optional.get();
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
            return "redirect:/movie";
        }
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        boolean userCanRate = true;




        List<Rating> allRatingsForTheReview = ratingRepository.findByReviewId(id);
        for (int i = 0; i < allRatingsForTheReview.size(); i++) {
            if (allRatingsForTheReview.get(i).getUserName().equals(username)){
                userCanRate = false;
                break;
            }
        }

        if (userCanRate == true) {
            Optional<Review> optional = reviewRepository.findById(id);
            Review review = optional.get();
            rating.setReviewId(id);
            rating.setUserName(username);
            rating = ratingRepository.save(rating);
        }

       // th:if="${userCanRate}"

        return "redirect:/movie";

    }


}