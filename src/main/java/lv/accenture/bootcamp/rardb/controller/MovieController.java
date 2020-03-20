package lv.accenture.bootcamp.rardb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieAPIService movieAPIService;

    @Autowired
    private ReviewRepository reviewRepository;

//    @Autowired
//    private Movie movieObject;


    @GetMapping("/movie")
    public String movieIndex(Model model) {
        Iterable<Movie> movie = movieRepository.findAll();
        model.addAttribute("movie", movie);
        movieAPIService.getMovieByTitle("John Wick"); //shis ir testam
        movieAPIService.searchMoviePhrase("Back to"); //shis ir testam
        movieAPIService.getMovieById("tt0096895"); //shis ir testam
        return "movies-index";
    }

    @GetMapping("/search_results")
    public String searchIndex(Model model) {

        MovieSearch movieSearch = new MovieSearch();
        movieSearch = movieAPIService.searchMoviePhrase("Batman");
        model.addAttribute("movieSearch", movieSearch);
        model.addAttribute("movieCandidates", movieSearch.getSearch());

        return "search-index";
    }


    //    @PostMapping("/movie/add-review/{imdbID}")
    public String addReview(@Valid Review reviewToAdd, @Valid Integer ratingToAdd, BindingResult bindingResult, @PathVariable Long imdbID) {
        if (bindingResult.hasErrors()) {
            return "add-review";
        }
        reviewRepository.save(reviewToAdd);
        return "redirect:/movie";

    }


//    @GetMapping("/movie/delete-review/{imdbId}")
//    public String clearReviews(@PathVariable String imdbId) {
//        movieRepository.deleteAllReviews();
//        movieRepository.deleteAllRating();
//        return "redirect:/movie";
//    }
}
