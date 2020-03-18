package lv.accenture.bootcamp.rardb.controller;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.Optional;

import javax.validation.Valid;
>>>>>>> aa066ca99952a73783153be83fbb005d0075c463

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
<<<<<<< HEAD

	@GetMapping("/search_results")
	public String searchIndex(Model model) {

		MovieSearch movieSearch = new MovieSearch();
		movieSearch = movieAPIService.searchMoviePhrase("Batman");
		model.addAttribute("movieSearch", movieSearch);
		model.addAttribute("movieCandidates", movieSearch.getSearch());
		
		return "search-index";
	}

=======
	@GetMapping("/search_results")
	public String searchIndex(Model model) {
		MovieSearch movieSearch = new MovieSearch();
		movieSearch = movieAPIService.searchMoviePhrase("Batman");
		model.addAttribute("movieSearch", movieSearch);
		MoviePreview moviePreview = new MoviePreview();
		model.addAttribute("moviePreview", moviePreview);
		return "search-index";
	}

@PostMapping("/movie/add-comment")
public String addComment(@Valid Movie commentToAdd, @Valid Movie rankToAdd, BindingResult bindingResult) {
  if(bindingResult.hasErrors()) {
	  return "add-comment";
  }
movieRepository.save(commentToAdd);
movieRepository.save(rankToAdd);
  return "redirect:/movie";
}

@GetMapping("/movie/delete-comment/{imdbID}")
public String deleteAllComment(@PathVariable Long imdbID) {
  movieRepository.deleteComment(imdbID);
  movieRepository.deleteRank(imdbID);
  return "redirect:/movie";
}
>>>>>>> aa066ca99952a73783153be83fbb005d0075c463
}




