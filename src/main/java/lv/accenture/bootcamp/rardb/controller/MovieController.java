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
        movieAPIService.getMovieByTitle("John Wick");
        movieAPIService.searchMoviePhrase("Back to");
        movieAPIService.getMovieById("tt0096895");
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
//
//    @PostMapping("/movie/add-comment")
//    public String addComment(@Valid Movie commentToAdd, @Valid Movie rankToAdd, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "add-comment";
//        }
//        movieRepository.save(commentToAdd);
//        movieRepository.save(rankToAdd);
//        return "redirect:/movie";
//    }

//    @PostMapping("/movie/add-comment/{imdbID}")
    public String addComment(@Valid Movie reviewToAdd, BindingResult bindingResult, @PathVariable Long imdbID) {
        if (bindingResult.hasErrors()) {
            return "add-comment";
        }



        movieRepository.findAllById();


        reviewToAdd = reviewRepository.save(reviewToAdd);



        return "redirect:/movie";
    }


//	@GetMapping("/movie/delete-comment/{imdbID}")
//	public String deleteAllComment(@PathVariable Long imdbID) {
//		movieRepository.deleteComment(imdbID);
//		movieRepository.deleteRank(imdbID);
//		return "redirect:/movie";
//	}
}
