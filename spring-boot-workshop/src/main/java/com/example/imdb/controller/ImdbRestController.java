package com.example.imdb.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Movie;
import com.example.imdb.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestController
@RequestScope // @Scope("request")
@RequestMapping("/movies")
@CrossOrigin
public class ImdbRestController { // POJO, Declarative Programming through @nnotations

	private MovieService movieSrv;

	// Constructor Injection
	public ImdbRestController(MovieService movieSrv) {
		this.movieSrv = movieSrv;
	}

	// http://localhost:2021/imdb/api/v1/movies?fromYear=1970&toYear=1980 // query
	// parameters
	@GetMapping(params = { "fromYear", "toYear" })
	public Collection<Movie> findMoviesByRange(@RequestParam int fromYear, @RequestParam int toYear) {
		return movieSrv.findAllMoviesByYearRange(fromYear, toYear);
	}
}
