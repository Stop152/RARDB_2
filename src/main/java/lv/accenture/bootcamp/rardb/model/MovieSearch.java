package lv.accenture.bootcamp.rardb.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSearch {

	@JsonProperty("Search")
	private List<MoviePreview> search = new ArrayList<>();
	@JsonProperty("totalResults")
	private Integer totalResults;
	@JsonProperty("Response")
	private boolean response;

	public List<MoviePreview> getSearch() {
		return search;
	}

	public void setSearch(List<MoviePreview> search) {
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
		return "MovieSearch [search=" + search + ", totalResults=" + totalResults + ", response=" + response + "]";
	}

}
