package com.czy.bean;

import java.util.List;

public class Goods {


	private int gid;

	private String gname;

	private int number;

	
	private String described;
	
	private List<String> genres;
	
	private List<String> stars;
	
	private List<String> stId;
	
	private List<String> movieId;
	
	private String genresname;
		
	private int year;
	
	private String title;
	
	private String director;
	
	private String starsname;
	
	private float rating;
	
	private String mId;
	
	private int total;
	
	private int genresnumber;
	
	private int starsnumber;
	
	private String starsId;
	
	private int starsBirthYear;
	
	private int acLength;
	
	private int mIdLength;
	
	private List<String> actedMovies;
	
	

	public Goods() {

	}
	public Goods(int total) {
		this.total = total;
		}
	
	public Goods(String genresname) {
		this.setGenresname(genresname);
	}
	
	public Goods(List<String> genList, String id, int year, String title,String director,List<String> stars,float rating,int genresnumber,int starsnumber,String starsId,List<String> stId) {
		this.setGenres(genList);
		this.setmId(id);
		this.setYear(year);
		this.setTitle(title);
		this.setDirector(director);
		this.setStars(stars);
		this.setRating(rating);
		this.setGenresnumber(genresnumber);
		this.setStarsnumber(starsnumber);
		this.setStarsId(starsId);
		this.setStId(stId);
	}
	
	public Goods(String id, String name,int birthYear,List<String> actedMovies,List<String> movieId,int acLength,int mIdLength) {
		this.setStarsId(id);
		this.setStarsname(name);
		this.setYear(birthYear);
		this.setActedMovies(actedMovies);
		this.setMovieId(movieId);
		this.setAcLength(acLength);
		this.setmIdLength(mIdLength);
		
	}
	public Goods(String id, String title, String director) {
		this.setmId(id);
		this.setTitle(title);
		this.setDirector(director);
	}
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescribed() {
		return described;
	}

	public void setDescribed(String described) {
		this.described = described;
	}

	/**
	 * @return the genres
	 */
	public List<String> getGenres() {
		return genres;
	}

	/**
	 * @param string the genres to set
	 */
	public void setGenres(List<String> string) {
		this.genres = string;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * @return the stars
	 */
	public List<String> getStars() {
		return stars;
	}

	/**
	 * @param stars the stars to set
	 */
	public void setStars(List<String> stars) {
		this.stars = stars;
	}

	/**
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	 * @return the mId
	 */
	public String getmId() {
		return mId;
	}

	/**
	 * @param mId the mId to set
	 */
	public void setmId(String mId) {
		this.mId = mId;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the genresname
	 */
	public String getGenresname() {
		return genresname;
	}
	/**
	 * @param genresname the genresname to set
	 */
	public void setGenresname(String genresname) {
		this.genresname = genresname;
	}
	/**
	 * @return the genresnumber
	 */
	public int getGenresnumber() {
		return genresnumber;
	}
	/**
	 * @param genresnumber the genresnumber to set
	 */
	public void setGenresnumber(int genresnumber) {
		this.genresnumber = genresnumber;
	}
	/**
	 * @return the starsname
	 */
	public String getStarsname() {
		return starsname;
	}
	/**
	 * @param starsname the starsname to set
	 */
	public void setStarsname(String starsname) {
		this.starsname = starsname;
	}
	/**
	 * @return the starsnumber
	 */
	public int getStarsnumber() {
		return starsnumber;
	}
	/**
	 * @param starsnumber the starsnumber to set
	 */
	public void setStarsnumber(int starsnumber) {
		this.starsnumber = starsnumber;
	}
	/**
	 * @return the starsId
	 */
	public String getStarsId() {
		return starsId;
	}
	/**
	 * @param starsId the starsId to set
	 */
	public void setStarsId(String starsId) {
		this.starsId = starsId;
	}
	/**
	 * @return the starsBirthYear
	 */
	public int getStarsBirthYear() {
		return starsBirthYear;
	}
	/**
	 * @param starsBirthYear the starsBirthYear to set
	 */
	public void setStarsBirthYear(int starsBirthYear) {
		this.starsBirthYear = starsBirthYear;
	}
	/**
	 * @return the actedMovies
	 */
	public List<String> getActedMovies() {
		return actedMovies;
	}
	/**
	 * @param actedMovies2 the actedMovies to set
	 */
	public void setActedMovies(List<String> actedMovies2) {
		this.actedMovies = actedMovies2;
	}
	/**
	 * @return the stId
	 */
	public List<String> getStId() {
		return stId;
	}
	/**
	 * @param stId the stId to set
	 */
	public void setStId(List<String> stId) {
		this.stId = stId;
	}
	/**
	 * @return the movieId
	 */
	public List<String> getMovieId() {
		return movieId;
	}
	/**
	 * @param movieId the movieId to set
	 */
	public void setMovieId(List<String> movieId) {
		this.movieId = movieId;
	}
	/**
	 * @return the acLength
	 */
	public int getAcLength() {
		return acLength;
	}
	/**
	 * @param acLength the acLength to set
	 */
	public void setAcLength(int acLength) {
		this.acLength = acLength;
	}
	/**
	 * @return the mIdLength
	 */
	public int getmIdLength() {
		return mIdLength;
	}
	/**
	 * @param mIdLength the mIdLength to set
	 */
	public void setmIdLength(int mIdLength) {
		this.mIdLength = mIdLength;
	}

	

}
