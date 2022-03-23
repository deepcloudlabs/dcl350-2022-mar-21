package com.example.imdb.service;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface SequenceService {
	long nextId(String sequenceName);

	long nextId(String sequenceName, int step);

	String nextId(String sequenceName, String prefix);

	String nextId(String sequenceName, String prefix, int step);
}
