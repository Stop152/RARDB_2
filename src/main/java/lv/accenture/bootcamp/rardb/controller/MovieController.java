package lv.accenture.bootcamp.rardb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.accenture.bootcamp.rardb.model.*;
import lv.accenture.bootcamp.rardb.movieAPI.*;
import lv.accenture.bootcamp.webdemo.model.Cat;

@Controller
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieAPIService movieAPIService;

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

}
