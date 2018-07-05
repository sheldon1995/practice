package com.czy.dao;

import java.util.List;

import com.czy.bean.Goods;

public interface GoodsDao {

	public int getCounts() throws Exception;
	

	public boolean addMovie(String fid,String t,String year,String dirn) throws Exception;
	
	public boolean addStars_In_Movies(String a,String f) throws Exception;
	
	public boolean addStars_Xml(String stagename,String nm,String dob) throws Exception;
	
	public boolean addStars(String stars, Integer year) throws Exception;

	
	public List<Goods> getAllGoods() throws Exception;
	
	public List<Goods> getAllGenres() throws Exception;
	
	
	public List<Goods> getAllMoviesByTitleOrGenres(String title,int ResultNumberPerPage,int pc,int order,String movieGenres)throws Exception;
	
	public List<Goods> getAllMoviesByAdvancedSearch(String title, Integer year, String name, String director)throws Exception;
	


	public boolean deleteGoods(String gid) throws Exception;

	
	public Goods queryBymId(String mId) throws Exception;
	
	public List<Goods> queryBystarsId(String starsId) throws Exception;

	public int queryNumberById(String gid) throws Exception;

	public String[] queryTypes() throws Exception;

	public List<Goods> getTypeGoodsList(String type) throws Exception;
	
	
}
