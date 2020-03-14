package lv.accenture.bootcamp.rardb.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {

	String title;
	Integer year;

	@Id
	String imdbID;
	String poster;

	public Movie() {
	}

	public Movie(String title, Integer year, String poster) {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

}
