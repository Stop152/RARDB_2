package lv.accenture.bootcamp.rardb.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSearch {

	@JsonProperty("Search")
	private Movie[] search = new Movie[10];
	@JsonProperty("totalResults")
	private Integer totalResults;
	@JsonProperty("Response")
	private boolean response;

	public Movie[] getSearch() {
		return search;
	}

	public void setSearch(Movie[] search) {
		this.search = search;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "MovieSearch [search=" + Arrays.toString(search) + ", totalResults=" + totalResults + ", response="
				+ response + "]";
	}

}
