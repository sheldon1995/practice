package com.czy.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.czy.bean.Goods;
import com.czy.dao.GoodsDao;
import com.mysql.jdbc.Statement;
import com.sun.org.apache.regexp.internal.recompile;


public class GoodsDaoImpl implements GoodsDao {

	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public GoodsDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean addStars(String stars, Integer year) throws Exception {
		pstmt = null;
		String sqlMaxId ="select id from stars order by id desc limit 1";
		Statement st = null;
		ResultSet rs = null;
		String num = null;
		String maxid = null;
		st = (Statement) conn.createStatement();
		rs = st.executeQuery(sqlMaxId);
		while(rs.next())
			{
			 
			 num =rs.getString("id");
			 int numid = Integer.parseInt(num.substring(2, 9))+1;
			 maxid = "nm"+String.valueOf(numid);
			  
			}
		String id = maxid; 
		String sql = "insert into stars(id,name,birthYear) value(?,?,?);";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, stars);
		pstmt.setInt(3, year);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteGoods(String gid) throws Exception {
		String sql = "delete from goods where gid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, gid);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}
	@Override
	public int queryNumberById(String gid) throws Exception {
		ResultSet rs = null;
		String sql = "select number from goods where gid =?";
		int number = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, gid);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			number = rs.getInt("number");
		}
		pstmt.close();
		rs.close();
		return number;
	}

	@Override
	public List<Goods> getAllGoods() throws Exception {
		String sql = "select * from movies order by id asc limit 8";
		Statement st = null;
		ResultSet rs = null;
		st = (Statement) conn.createStatement();
		rs = st.executeQuery(sql);
		List<Goods> goodsList = new ArrayList<Goods>();
		Goods goods;
		while (rs.next()) {
	
			String mId = rs.getString("id");
			String title = rs.getString("title");
			String director = rs.getString("director");
			goods = new Goods(mId,title,director);
			goodsList.add(goods);
		}
		return goodsList;
	}

	@Override
	public String[] queryTypes() throws Exception {
		String sql = "select distinct types from goods";
		Statement st = null;
		ResultSet rs = null;
		st = (Statement) conn.createStatement();
		rs = st.executeQuery(sql);
		String[] types = new String[10];
		int i = 0;
		while (rs.next()) {
			types[i] = rs.getString("types");
			i++;
		}
		return types;
	}

	@Override
	public List<Goods> getTypeGoodsList(String type) throws Exception {
		List<Goods> goodsList = new ArrayList<Goods>();
		Goods goods;
		ResultSet rs = null;
		String sql = "select gid,gname from goods where types=? order by gid desc;";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, type);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			goods = new Goods();
			goods.setGid(rs.getInt("gid"));
			goods.setGname(rs.getString("gname"));
			goodsList.add(goods);
		}
		return goodsList;
	}

	@Override
	public List<Goods> getAllGenres() throws Exception {
		String sql = "select name from genres";
		Statement st = null;
		ResultSet rs = null;
		st = (Statement) conn.createStatement();
		rs = st.executeQuery(sql);
		List<Goods> genresList = new ArrayList<Goods>();
		Goods genres;
		while (rs.next()) {
			
			String name = rs.getString("name");
			genres = new Goods(name);
			genresList.add(genres);
		}
		return genresList;
	}

	@Override
	public Goods queryBymId(String mId) throws Exception {
		Goods goods =null;
		ResultSet rs = null;
		String sql = "SELECT id, title, year, director, A.GG, B.GS, rating "
				+ "FROM movies "
				+ "Inner join (SELECT title T, GROUP_CONCAT(genres.name) GG "
				+ "FROM movies "
				+ "INNER JOIN genres_in_movies "
				+ "ON  movies.id= genres_in_movies.movieId "
				+ "INNER JOIN genres "
				+ "ON genres_in_movies.genreId=genres.id "
				+ "GROUP BY movies.id "
				+ ") A ON title = A.T "
				+ "Inner join (SELECT title I, GROUP_CONCAT(stars.name) GS "
				+ "FROM movies "
				+ "INNER JOIN stars_in_movies "
				+ "ON  movies.id= stars_in_movies.movieId "
				+ "INNER JOIN stars "
				+ "ON stars_in_movies.starId = stars.id "
				+ "GROUP BY movies.id "
				+ ") B on title = B.I "
				+ "Inner join ratings "
				+ "on movies.id = ratings.movieId "
				+ "WHERE movies.id=?";			
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, mId);
		rs = pstmt.executeQuery();	
		while (rs.next()) {					
			goods = new Goods();
			goods.setTitle(rs.getString("title"));
			goods.setmId(rs.getString("id"));
			goods.setYear(rs.getInt("year"));
			goods.setGenresname(rs.getString("A.GG"));
			goods.setStarsname(rs.getString("B.GS"));
			goods.setDirector(rs.getString("director"));
			goods.setRating(rs.getFloat("rating"));			
		}
		pstmt.close();
		rs.close();
		return goods;
		
	}
	@Override
	public List<Goods> getAllMoviesByTitleOrGenres(String titles,int ResultNumberPerPage,int pc,int order,String movieGenres) throws Exception {
		List<Goods> moviesList = new ArrayList<Goods>();
		int all=0;
		String sqlCount0 = "select count(*) from movies ";
		String sqlCount1;
		String sqlCount="";
		if(movieGenres.equals("notDecided")) {
			sqlCount1 = "where title LIKE '"+titles+"%"+"'";
			sqlCount = sqlCount0 + sqlCount1;		
		}
		
		if(titles.equals("notDecided")) {
			sqlCount = "SELECT count(A.GG) "
					+"FROM movies "
					+"Inner join (SELECT title T, GROUP_CONCAT(genres.name) GG " 
					+"FROM movies "
					+"INNER JOIN genres_in_movies " 
					+"ON  movies.id= genres_in_movies.movieId " 
					+"INNER JOIN genres " 
					+"ON genres_in_movies.genreId=genres.id	AND genres.name='"+movieGenres+"'"
					+"GROUP BY movies.id " 
					+") A ON title = A.T" ;
		}		
		PreparedStatement CountStatement = (PreparedStatement) conn.prepareStatement(sqlCount);
		try {
			ResultSet rsCount = CountStatement.executeQuery();
			while(rsCount.next()) {
				if(titles.equals("notDecided")) 
				{
				all = Integer.parseInt(rsCount.getString("count(A.GG)"));
				}
				else 
				{
				all = Integer.parseInt(rsCount.getString("count(*)"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int total;
		if(all%ResultNumberPerPage == 0) 
			total = all/ResultNumberPerPage;
		else
			total = all/ResultNumberPerPage+1;
		Goods totalPage = new Goods(total);	
		moviesList.add(totalPage);
		String sql1="ORDER BY title ASC ";
		String sql2="ORDER BY title DESC ";
		String sql3="ORDER BY rating ASC ";
		String sql4="ORDER BY rating DESC ";
		String sql5="where A.GG like '%"+movieGenres+"%'";
		String sql6="where movies.title like '"+titles+"%'";
		String sqlOrder;
		String sqlGenres;
		String sqlTitles;
		if(order==1) {
			sqlOrder = sql1;
		}
		else if(order==2) {
			sqlOrder = sql2;
		}
		else if(order==3) {
			sqlOrder = sql3;
		}
		else if(order==4) {
			sqlOrder = sql4;
		}
		else {
			sqlOrder = "";
		}
		
		if(movieGenres.equals("notDecided")) {
			sqlGenres = "";
		}
		else {
			sqlGenres = sql5;
		}
		
		if(titles.equals("notDecided")) {
			sqlTitles = "";
		}
		else{
			sqlTitles = sql6;
		}
		
		String sql0 =  "SELECT id, title, year, director, A.GG, B.GS, rating, B.SI "
				+ "FROM movies "
				+ "Inner join (SELECT title T, GROUP_CONCAT(genres.name) GG "
				+ "FROM movies "
				+ "INNER JOIN genres_in_movies "
				+ "ON  movies.id= genres_in_movies.movieId "
				+ "INNER JOIN genres "
				+ "ON genres_in_movies.genreId=genres.id "
				+ "GROUP BY movies.id "
				+ ") A ON title = A.T "
				+ "Inner join (SELECT title I, GROUP_CONCAT(stars.name) GS, GROUP_CONCAT(stars.id) SI "
				+ "FROM movies "
				+ "INNER JOIN stars_in_movies "
				+ "ON  movies.id= stars_in_movies.movieId "
				+ "INNER JOIN stars "
				+ "ON stars_in_movies.starId = stars.id "
				+ "GROUP BY movies.id "
				+ ") B on title = B.I "
				+ "Inner join ratings "
				+ "on movies.id = ratings.movieId "
				+ sqlGenres
				+ sqlTitles
				+ sqlOrder
				+ "LIMIT ?,?"; 
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql0);
		preparedStatement.setInt(1, (pc-1)*ResultNumberPerPage);
		preparedStatement.setInt(2, ResultNumberPerPage);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {			
			String title = rs.getString("title");
			int year = rs.getInt("year");
			String mid = rs.getString("id");
			String director = rs.getString("director");
			
			String genres = rs.getString("A.GG");
			String[] gen = genres.split(",");
			List<String> genList = new ArrayList<String>();
			int genresnumber = gen.length;
			
			for(int i=0;i<gen.length;i++) {
				genList.add(gen[i]);
			}
			
			String stars = rs.getString("B.GS");
			String[] st = stars.split(",");
			List<String> stList = new ArrayList<String>();
			int starsnumber = st.length;
			
			for(int i=0;i<st.length;i++) {
				stList.add(st[i]);
			}
			String starsId = rs.getString("B.SI");
			String[] stId = starsId.split(",");
			List<String> stIdList = new ArrayList<String>();
			for(int i=0;i<starsnumber;i++) {
				stIdList.add(stId[i]);
			}
			float rating = rs.getFloat("rating");		
			Goods movieGoods;			
			movieGoods = new Goods(genList, mid, year, title, director, stList, rating,genresnumber,starsnumber,starsId,stIdList);
			moviesList.add(movieGoods);
		}
		return moviesList;
	}

	
	public boolean addMovie(String fid,String t,String year,String dirn) throws Exception {
		pstmt = null;
		String sql = "insert into movies(id,title,year,director) value(?,?,?,?);";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, fid);
		pstmt.setString(2, t);
		pstmt.setString(3, year);
		pstmt.setString(4, dirn);

		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addStars_In_Movies(String a, String f) throws Exception {
		pstmt = null;
		String sql = "insert into stars_in_movies(starId,movieId) value(?,?);";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, a);
		pstmt.setString(2, f);

		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addStars_Xml(String stagename, String nm, String dob) throws Exception {
		pstmt = null;
		String sql = "insert into stars(id,name,birthYear) value(?,?,?);";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, stagename);
		pstmt.setString(2, nm);
		pstmt.setString(3, dob);

		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int getCounts() throws Exception {	
		pstmt = null;
		String sql = "SELECT COUNT(*) from movies;";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		result = pstmt.executeUpdate();
		pstmt.close();
		
		return result;
	}

	@Override
	/**
	 * 1.According to whether the input of title,year... is null and then calculate the total page and finish pagination.
	 * 2.And then set the corresponding sql query
	 * 3.Set stars and genres list with hyperlinked
	 * 4.Display results showMovies.jsp?(not decided)
	 */
	public List<Goods> getAllMoviesByAdvancedSearch(String title, Integer year, String name,String director) throws Exception {
		List<Goods> moviesList = new ArrayList<Goods>();
		int all=0;
		String sqlT="AND movies.title like '%"+title+"%'";
		String sqlY="AND movies.year like '"+year+"%'";
		String sqlD="AND movies.director like '%"+director+"%'";
		String sqlN="AND B.GS like '"+name+"%'";
		String sqlTitle="";
		String sqlYear="";
		String sqlDirector="";
		String sqlName="";
		if(!title.equals("null")||!title.equals(null)||title.length()>0) {
			sqlTitle = sqlT;
		}
		if(year!=null) {
			sqlYear = sqlY;
		}
		if(!director.equals("null")||!director.equals(null)||director.length()>0) {
			sqlDirector = sqlD;
		}
		if(!name.equals("null")||!name.equals(null)||name.length()>0) {
			sqlName = sqlN;
		}
		String sqlCount =  "SELECT COUNT(id) "
				+"FROM movies " 
				+"Inner join (SELECT title T, GROUP_CONCAT(genres.name) GG " 
				+"FROM movies "
				+"INNER JOIN genres_in_movies " 
				+"ON  movies.id= genres_in_movies.movieId " 
				+"INNER JOIN genres "
				+"ON genres_in_movies.genreId=genres.id " 
				+"GROUP BY movies.id "
				+") A ON title = A.T "
				+"Inner join (SELECT title I, GROUP_CONCAT(stars.name) GS "
				+"FROM movies " 
				+"INNER JOIN stars_in_movies "
				+"ON  movies.id= stars_in_movies.movieId "
				+"INNER JOIN stars " 
				+"ON stars_in_movies.starId = stars.id " 
				+"GROUP BY movies.id " 
				+") B on title = B.I "
			    +"join ratings " 
			    +"where movies.id = ratings.movieId "
				+ sqlTitle
				+ sqlYear
				+ sqlDirector
				+ sqlName; 
		PreparedStatement CountStatement = (PreparedStatement) conn.prepareStatement(sqlCount);
		try {
			ResultSet rsCount = CountStatement.executeQuery();
			while(rsCount.next()) {
				all = Integer.parseInt(rsCount.getString("count(id)"));
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int total;
		int ResultNumberPerPage=15;
		if(all%ResultNumberPerPage == 0) 
			total = all/ResultNumberPerPage;
		else
			total = all/ResultNumberPerPage+1;
		Goods totalPage = new Goods(total);	
		moviesList.add(totalPage);
		/*
		String sql1="ORDER BY title ASC ";
		String sql2="ORDER BY title DESC ";
		String sql3="ORDER BY rating ASC ";
		String sql4="ORDER BY rating DESC ";
		*/				
		String sql0 =  "SELECT id, title, year, director, A.GG, B.GS, rating,B.SI "
				+"FROM movies " 
				+"Inner join (SELECT title T, GROUP_CONCAT(genres.name) GG " 
				+"FROM movies "
				+"INNER JOIN genres_in_movies " 
				+"ON  movies.id= genres_in_movies.movieId " 
				+"INNER JOIN genres "
				+"ON genres_in_movies.genreId=genres.id " 
				+"GROUP BY movies.id "
				+") A ON title = A.T "
				+"Inner join (SELECT title I, GROUP_CONCAT(stars.name) GS,GROUP_CONCAT(stars.id) SI "
				+"FROM movies " 
				+"INNER JOIN stars_in_movies "
				+"ON  movies.id= stars_in_movies.movieId "
				+"INNER JOIN stars " 
				+"ON stars_in_movies.starId = stars.id " 
				+"GROUP BY movies.id " 
				+") B on title = B.I "
			    +"join ratings " 
			    +"where movies.id = ratings.movieId "
				+ sqlTitle
				+ sqlYear
				+ sqlDirector
				+ sqlName
				+ "LIMIT ?,?"; 
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql0);
		int pc =1;
		preparedStatement.setInt(1, (pc-1)*ResultNumberPerPage);
		preparedStatement.setInt(2, ResultNumberPerPage);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {			
			String resultT = rs.getString("title");
			int resultY = rs.getInt("year");
			String mid = rs.getString("id");
			String resultD = rs.getString("director");
			
			String genres = rs.getString("A.GG");
			String[] gen = genres.split(",");
			List<String> genList = new ArrayList<String>();
			int genresnumber = gen.length;
			
			for(int i=0;i<gen.length;i++) {
				genList.add(gen[i]);
			}
			
			String stars = rs.getString("B.GS");
			String[] st = stars.split(",");
			List<String> stList = new ArrayList<String>();
			int starsnumber = st.length;
			
			for(int i=0;i<st.length;i++) {
				stList.add(st[i]);
			}
			String starsId = rs.getString("B.SI");
			String[] stId = starsId.split(",");
			List<String> stIdList = new ArrayList<String>();
			for(int i=0;i<starsnumber;i++) {
				stIdList.add(stId[i]);
			}
			float rating = rs.getFloat("rating");		
			Goods movieGoods;			
			movieGoods = new Goods(genList, mid, resultY, resultT, resultD, stList, rating,genresnumber,starsnumber,starsId,stIdList);
			moviesList.add(movieGoods);
		}
		return moviesList;
		
	}

	@Override
	public List<Goods> queryBystarsId(String starsId) throws Exception {
		ResultSet rs = null;
		List<Goods> moviesList = new ArrayList<Goods>();
		String sql = "SELECT stars.id,stars.name, birthYear,A.T, A.I "
				+ "FROM stars "
				+ "Inner join " 
				+ "(SELECT stars.name SN, GROUP_CONCAT(title) T,GROUP_CONCAT(movies.id) I "
				+ "FROM movies " 
				+ "INNER JOIN stars_in_movies "
				+ "ON  movies.id= stars_in_movies.movieId " 
				+ "INNER JOIN stars " 
				+ "ON stars_in_movies.starId = stars.id " 
				+ "GROUP BY stars.id " 
				+ ") A on stars.name = A.SN "
				+ "WHERE stars.id = ?"; 	
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, starsId);
		rs = pstmt.executeQuery();	
		while (rs.next()) {	
			String starsName=rs.getString("stars.name");
			int starsYear=rs.getInt("birthYear");
			String starId=rs.getString("stars.id");	
			
			String actedMovies=rs.getString("A.T");
			String[] acmMovies = actedMovies.split(",");
			List<String> acList = new ArrayList<String>();
			int acLength = acmMovies.length;
			for(int m =0;m<acmMovies.length;m++) {
				acList.add(acmMovies[m]);
			}
			
			String movieId=rs.getString("A.I");
			String[] mId =movieId.split(",");
			List<String> mList = new ArrayList<String>();
			int mIdLength = mId.length;
			for(int m=0;m<mId.length;m++) {
				mList.add(mId[m]);
			}
			
			Goods movieGoods;			
			movieGoods = new Goods(starId,starsName,starsYear,acList,mList,acLength,mIdLength);
			moviesList.add(movieGoods);
			
		}
		pstmt.close();
		rs.close();
		return moviesList;
		
	}
	

}
