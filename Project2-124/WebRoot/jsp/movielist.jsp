<%@page import="Search.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, java.math.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie results</title>
</head>
<body>

	<h1>
	Search Results
	</h1>

	<table border=1px>
		<tr>
			<td>MovieID</td>
			<td>Title</td>
			<td>Year</td>
			<td>Director</td>
			<td>Genres</td>
			<td>Stars</td>
			<td>Rating</td>
		</tr>
		
		<%
		
		int page_num = (Integer)request.getAttribute("cur_page");
		int pageSize=10;
		
		List<Movie> ListofMovies = (List)request.getAttribute("movieList");
		
		int pageNumbers = (int)((ListofMovies.size()/pageSize));
		
		for(int i = (page_num-1)*pageSize;i<(page_num-1)*pageSize+pageSize;++i){
			
			if(i<ListofMovies.size()){
		%>
		<tr>
			<td><%=ListofMovies.get(i).id%></td>

			<td><%=ListofMovies.get(i).title%></td>

			<td><%=ListofMovies.get(i).year%></td>

			<td><%=ListofMovies.get(i).director%></td>
			<td>
			<%List<String> genreList = new ArrayList<String>();
			int genreSize = ListofMovies.get(i).genres.size();
			for(int x = 0; x<genreSize;++x){
				genreList.add(ListofMovies.get(i).genres.get(x));}%>
			
			<%for(String genre : genreList){ %>
			<p> <%=genre%></p>			

			<%} %>
			</td>
			
			<td>
			<%List<String> starList = new ArrayList<String>();
			int starSize = ListofMovies.get(i).stars.size();
			for(int y = 0; y<starSize;++y){
				starList.add(ListofMovies.get(i).stars.get(y).name);}%>
			
			<%for(String star : starList){ %>
			<p> <%=star%></p>			

			<%} %>
			</td>			
			<td><%=ListofMovies.get(i).rating%></td>
		</tr>
		<%} %>
		<%} %>
	</table>
</body>
</html>