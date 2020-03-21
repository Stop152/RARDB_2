package lv.accenture.bootcamp.rardb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @GetMapping("/search_results")
    public String searchIndex(Model model) {

        MovieSearch movieSearch = new MovieSearch();
        movieSearch = movieAPIService.searchMoviePhrase("robin");
        model.addAttribute("movieSearch", movieSearch);
        model.addAttribute("movieCandidates", movieSearch.getSearch());

        return "search-index";
    }


    @GetMapping("/movie/select-movie/{imdbID}")
    public void selectMovie(@PathVariable String imdbID) {


        MovieSearch movieSearch;
        // movieSearch.getSearch();

        Movie movie;


        //   movieRepository.save(movie);


    }


    @GetMapping("/movies/search")
    public String searchMovies(@RequestParam(value = "movieTitle") String movieTitle, Model model) {

        MovieSearch movieSearch = new MovieSearch();
        movieSearch = movieAPIService.searchMoviePhrase(movieTitle);
        model.addAttribute("movieSearch", movieSearch);
        model.addAttribute("movieCandidates", movieSearch.getSearch());

        return "search-index";
    }


    @PostMapping("/movie/add-review/{imdbID}")
    public String addReview(@PathVariable String imdbID, @Valid Review reviewToAdd, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-review";
        }
        Movie movie;

        reviewToAdd.setMovieId(imdbID);
        reviewRepository.save(reviewToAdd);
        //     movie.addReview(reviewToAdd);

        return "redirect:/movie";

    }


//    @GetMapping("/movie/delete-review/{imdbId}")
//    public String clearReviews(@PathVariable String imdbId) {
//        movieRepository.deleteAllReviews();
//        movieRepository.deleteAllRating();
//        return "redirect:/movie";
//    }
}
