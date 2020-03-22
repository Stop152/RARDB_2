package lv.accenture.bootcamp.rardb.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Movie {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;

    @JsonProperty("Type")
    private String type;

    @Id
    @JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Poster")
    private String poster;




    @ElementCollection
    private List<Review> reviewList;

//	private Movie movie = new Movie();
//	String movieId = movie.getImdbID();
//	private HashMap<String, Object> movieMap = new HashMap<String, Object>();

    public Movie() {
    }

    public Movie(String title, String year, String type, String poster) {

    }

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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }




    public void addReview(Review review) {

        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i) == null) {
                reviewList.add(review);
            }
        }

    }




    @Override
    public String toString() {
        return "Movie [title=" + title + ", year=" + year + ", imdbID=" + imdbID + ", poster=" + poster + "]";
    }

}
