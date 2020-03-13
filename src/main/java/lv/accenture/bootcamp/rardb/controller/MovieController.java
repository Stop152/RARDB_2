package lv.accenture.bootcamp.rardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.accenture.bootcamp.rardb.model.Movie;


@Controller

public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	 @GetMapping("/movie")
	    public String movieIndex(Model model) {
		Iterable<Movie> movie = movieRepository.findAll();
		  model.addAttribute("movie", movie);
	        return "movies-index";
	    }
}
