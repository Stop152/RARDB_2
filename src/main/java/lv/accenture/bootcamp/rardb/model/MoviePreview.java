package lv.accenture.bootcamp.rardb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MoviePreview {

	@JsonProperty("Title")
	private String title;
	@JsonProperty("Year")
	private String year;

	@JsonProperty("imdbID")
	private String imdbID;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Poster")
	private String poster;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	//TODO : 1) unused 2) line 69 : comparing Strings by "==" is serious crime
	public MoviePreview selectPreviewItem(String imdbID) {

		MoviePreview moviePreview = new MoviePreview();
		MovieSearch movieSearch = new MovieSearch();
		List<MoviePreview> previewList = movieSearch.getSearch();

		for (int i = 0; i < previewList.size(); i++) {
			if (moviePreview.getImdbID() == imdbID) {
				return moviePreview;
			}
		}

		return null;
	}








	@Override
	public String toString() {
		return "MoviePreview [title=" + title + ", year=" + year + ", imdbID=" + imdbID + ", type=" + type + ", poster="
				+ poster + "]";
	}

}
