package lv.accenture.bootcamp.rardb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoviePreview {

	@JsonProperty("Title")
	private String Title;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("imdbID")
	private String imdbID;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Poster")
	private String poster;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		this.Title = title;
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

	@Override
	public String toString() {
		return "MoviePreview [title=" + Title + ", year=" + year + ", imdbID=" + imdbID + ", type=" + type + ", poster="
				+ poster + "]";
	}

}
