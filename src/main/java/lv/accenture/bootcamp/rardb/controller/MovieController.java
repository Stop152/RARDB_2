package lv.accenture.bootcamp.rardb.controller;

import java.util.*;

import javax.validation.Valid;

import lv.accenture.bootcamp.rardb.repository.MovieRepository;
import lv.accenture.bootcamp.rardb.repository.RatingRepository;
import lv.accenture.bootcamp.rardb.repository.ReviewRepository;
import lv.accenture.bootcamp.rardb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }


    @GetMapping("/movie")
    public String movieIndex(Model model) {

        List<Object[]> reviews = reviewRepository.getTop10();

        List<TopReview> topReviews = new ArrayList<>();
        for (int i = 0; i < reviews.size(); i++) {
            Object[] reviewData = reviews.get(i);
            TopReview topReview = new TopReview();
            topReview.setReviewId((Integer) reviewData[0]);
            topReview.setText((String) reviewData[1]);
            topReview.setMovieTitle((String) reviewData[2]);
            topReview.setYear((String) reviewData[3]);
            topReview.setUserName((String) reviewData[4]);
            topReview.setAverageRating((Double) reviewData[5]);

            List<Rating> ratingList = ratingRepository.findByReviewId(topReview.getReviewId());
            topReview.setRatingAmount(ratingList.size());

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

        String username = getUsername();

        Movie movie;
        if (movieRepository.existsById(imdbID)) {
            Optional<Movie> optional = movieRepository.findById(imdbID);
            movie = optional.get();
        } else {
            movie = movieAPIService.getMovieById(imdbID);
        }

        review.setMovieId(imdbID);
        review.setMovieYear(movie.getYear());
        review.setMovieTiltle(movie.getTitle());
        review.setUserName(username);
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


        String username = getUsername();

        boolean userCanRate = true;

        Optional<Review> optional = reviewRepository.findById(id);
        Review review = optional.get();
        model.addAttribute("review", review);
        List<Rating> allRatingsForTheReview = ratingRepository.findByReviewId(id);
        if (!review.getUserName().equals(username)) {

            for (int i = 0; i < allRatingsForTheReview.size(); i++) {
                if (allRatingsForTheReview.get(i).getUserName().equals(username)) {
                    userCanRate = false;
                    break;
                } else {
                    userCanRate = false;
                }
            }
        }

        model.addAttribute("userCanRate", userCanRate);

        Optional<Movie> optionalMovie = movieRepository.findById(imdbID);
        Movie movie = optionalMovie.get();
        model.addAttribute("movie", movie);

        Rating rating = new Rating();

        model.addAttribute("rating", rating);

        return "selected-review";
    }

    @PostMapping("/movie/select_movie/{imdbID}/review/{id}/rate")
    public String rateReview(@PathVariable(value = "imdbID") String imdbID, @PathVariable(value = "id") Integer
            id,
                             Rating rating, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/movie";
        }


        String username = getUsername();

        boolean userCanRate = true;


        List<Rating> allRatingsForTheReview = ratingRepository.findByReviewId(id);
        for (int i = 0; i < allRatingsForTheReview.size(); i++) {
            if (allRatingsForTheReview.get(i).getUserName().equals(username)) {
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

    @GetMapping("/{username}/reviews")
    public String getMyReviews(@PathVariable(value = "username") String username, Model model) {
        boolean usersOwnPage = false;
        if (username == getUsername()) {
            username = getUsername();
            usersOwnPage = true;
        }

        model.addAttribute("usersOwnPage", usersOwnPage);


        User user = userRepository.findByUserName(username);

        List<Review> reviews = reviewRepository.findByUserName(username);

        model.addAttribute("user", user);


        model.addAttribute("reviews", reviews);

        return "my-reviews";
    }


}