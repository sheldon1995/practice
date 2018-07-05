package Search;
import java.util.ArrayList;
import java.util.List;

public class Star {
	
	
	public String id;
	public String name;
	public String birthYear;
	
	public List<String> MoviesIn = new ArrayList<String>(); 
	
	//constructor
	public Star() {}
	
	//Methods
	
	
	//setters
	public void setId(String idnum) {
		this.id = idnum;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setYear(String year) {
		this.birthYear = year;
	}
	
	
	//getters
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getYear() {
		return this.birthYear;
	}
	

}
