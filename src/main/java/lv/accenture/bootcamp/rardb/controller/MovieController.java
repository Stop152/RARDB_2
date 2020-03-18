package lv.accenture.bootcamp.rardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.accenture.bootcamp.rardb.model.Movie;
import lv.accenture.bootcamp.rardb.model.*;
import lv.accenture.bootcamp.rardb.movieAPI.*;

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
		movieAPIService.getMovie("John Wick");
		movieAPIService.searchMoviePhrase("Batman");
		return "movies-index";
	}
}
