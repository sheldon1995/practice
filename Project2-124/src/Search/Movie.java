package Search;
import java.util.ArrayList;
import java.util.List;


public class Movie {	
	public String id;
	public String title;
	public String year;
	public String director;
	public String rating;
	
	public List<Star> stars = new ArrayList<Star>();
	public List<String> genres = new ArrayList<String>();
	
	//constructor
	
	public Movie() {}
	
	//Methods
	
	//setters
	public void setId(String idnum) {
		this.id = idnum;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setStars(List<Star> stars) {
		this.stars = stars;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	//getters
	
	public String getId() {
		return this.id;
	}
	public String getTitle() {
		return this.title;
	}
	public String getYear() {
		return this.year;
	}
	public String getDirector() {
		return this.director;
	}
	public List<Star> getStars(){
		return this.stars;
	}
	public List<String> getGenres(){
		return this.genres;
	}
	public String getRating() {
		return this.rating;
	}
	
	
}
