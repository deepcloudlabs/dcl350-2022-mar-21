package com.example.imdb.service;

import java.util.Collection;

import com.example.imdb.boundary.CriteriaBean;
import com.example.imdb.domain.Director;
import com.example.imdb.domain.Genre;
import com.example.imdb.domain.Movie;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com> Ctrl + Shift + O
 */
public interface MovieService {
	Movie findMovieById(int id);

	Collection<Movie> findAllMovies();

	Collection<Movie> findAllMoviesByYearRange(int fromYear, int toYear);

	Collection<Movie> findAllMoviesByDirectorId(int directorId);

	Collection<Movie> findAllMoviesByYearRangeAndGenre(String genre, int fromYear, int toYear);

	Collection<Movie> findAllMoviesByGenre(String genre);

	Collection<Movie> findAllMoviesByCriteria(CriteriaBean criteria);

	Movie addMovie(Movie movie);

	Genre findGenreByName(String genre);

	Collection<Genre> findAllGenres();

	Collection<Director> findAllDirectors();
}
