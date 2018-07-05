package com.czy.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Search.Movie;
import Search.Star;

import java.util.*;


/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		response.setContentType("text/html;charset=UTF-8");
		String loginUrl = "jdbc:mysql://localhost:3306/shopping";
        Connection connection =null;
        try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			connection = DriverManager.getConnection(loginUrl,"root","123456");
	
			//Statement statement = connection.createStatement();		
			
			int cur_page = 1;
			
			String query = "";
					
			//query parameters
			
			String title = request.getParameter("title");
			String year = request.getParameter("year");
			String director = request.getParameter("director");
			String name = request.getParameter("name");
			
			String[] queryList = {title, year, director, name};
			query += createQuery(query, queryList);
		
			List<Movie> ListofMovies = ExecuteQuery(query, connection);
			
			request.setAttribute("cur_page", cur_page);
			request.setAttribute("movieList", ListofMovies);
			request.setAttribute("ListofMoviesSize",ListofMovies.size());		
			request.getRequestDispatcher("../jsp/movielist.jsp").forward(request, response);
        }
		catch (Exception e) {
			
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private List<Movie> ExecuteQuery(String finalquery, Connection connection){
		
		List<Movie> resultList = new ArrayList<Movie>();		
		try {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(finalquery);
		
		//statement.close();	
		//Feed result into createMovieList
		resultList = createMovieList(result, connection);	
		return resultList;}	
		catch(Exception e) {
			
		}
		return resultList;	
	}
	
	private static List<Movie> createMovieList(ResultSet result, Connection connection){
		List<Movie> resultList = new ArrayList<Movie>();		
		
		try {
			while(result.next()) {						
				Movie movie = new Movie();
				movie.id = result.getString("movies.id");
				movie.title = result.getString("movies.title");
				movie.year = result.getString("movies.year");
				movie.director = result.getString("movies.director");
				movie.rating = result.getString("ratings.rating");
				
				//create list stars
				Statement starsStatement = connection.createStatement();
				
				String starsQuery ="SELECT DISTINCT * \r\n" + 
						"FROM stars JOIN stars_in_movies \r\n" + 
						"WHERE stars_in_movies.starID = stars.id\r\n" + 
						"AND stars_in_movies.movieID = '" + movie.id +"'"; 
				
				ResultSet result_of_stars = starsStatement.executeQuery(starsQuery);
				
				while(result_of_stars.next()) {
					Star star = new Star();
					star.id = result_of_stars.getString("stars.id");
					star.name = result_of_stars.getString("stars.name");
					star.birthYear = result_of_stars.getString("stars.birthYear");
					movie.stars.add(star);
					//System.out.println(star.name);
				}
				result_of_stars.close();
				starsStatement.close();						
				Statement genresStatement = connection.createStatement();
				
				String genresQuery ="SELECT DISTINCT * \r\n" + 
						"FROM genres JOIN genres_in_movies \r\n" + 
						"WHERE genres_in_movies.genreID = genres.id\r\n" + 
						"AND genres_in_movies.movieID = '" + movie.id +"'"; 
				
				ResultSet result_of_genres = genresStatement.executeQuery(genresQuery);
				
				while(result_of_genres.next()) {
					String genre = result_of_genres.getString("genres.name");
					movie.genres.add(genre);
				}				
				result_of_genres.close();
				genresStatement.close();				
				resultList.add(movie);
				
			}
		} catch(Exception e) {
			
		}
		return resultList;
	}
	private String createQuery(String query_input, String[] queryFields) {		
		String final_query = query_input;		
		final_query += "\r\n" + 
				"SELECT DISTINCT movies.id, movies.title, movies.year,movies.director, ratings.rating\r\n" + 
				"\r\n" + 
				"from stars JOIN stars_in_movies JOIN movies JOIN ratings\r\n" + 
				"\r\n" + 
				"WHERE stars.id = stars_in_movies.starId \r\n" + 
				"AND stars_in_movies.movieId=movies.Id \r\n" + 
				"AND movies.Id=ratings.movieId ";
		//queryFields[0] = title
		//queryFields[1] = year
		//queryFields[2] = director
		//queryFields[3] = name		
		if(queryFields[0] != "") {			
			final_query += " AND title like \"%" + queryFields[0] + "%\"";
		}
		if(queryFields[1] != "") {		
			final_query += " AND year like \"%" + queryFields[1] + "%\"";			
		}		
		if(queryFields[2] != "") {			
			final_query += " AND director like \"%" + queryFields[2] + "%\"";			
		}		
		if(queryFields[3] != "") {			
			final_query += " AND stars.name like \"%" + queryFields[3] + "%\"";			
		}		
		//final_query += " LIMIT 2;";
			return final_query;
	}
}




	
	
	
	
