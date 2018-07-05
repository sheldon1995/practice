package com.czy.service;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.czy.bean.Goods;
import com.czy.bean.PageBean;
import com.czy.dao.GoodsDao;
import com.czy.db.DBConnection;
import com.czy.db.GoodsDaoImpl;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class GoodsService implements GoodsDao {

	private DBConnection dbconn = null;

	private GoodsDao dao = null;

	public GoodsService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new GoodsDaoImpl(this.dbconn.getConnection());
	}



	@Override
	public boolean addStars(String stars, Integer year) throws Exception {
		if(stars !=null) {
			return this.dao.addStars(stars, year);
		}
		
		return false;
	}

	@Override
	public int queryNumberById(String gid) throws Exception {
		if (gid!=null) {
			return this.dao.queryNumberById(gid);
		}
		return 0;
	}

	@Override
	public List<Goods> getAllGoods() throws Exception {
		return this.dao.getAllGoods();
	}

	@Override
	public String[] queryTypes() throws Exception {
		return this.dao.queryTypes();
	}

	@Override
	public List<Goods> getTypeGoodsList(String type) throws Exception {
		if (type != null) {
			return this.dao.getTypeGoodsList(type);
		}
		return null;
	}

	public boolean isInt(int index) {
		if (index == 0) {
			return false;
		}
		String str = String.valueOf(index);
		return str.matches("[0-9]+$");
	}

	@Override
	public List<Goods> getAllGenres() throws Exception {
		
		return this.dao.getAllGenres();
	}

	
	@Override
	public Goods queryBymId(String mId) throws Exception {
		
		return this.dao.queryBymId(mId);
	}

	@Override
	public List<Goods> getAllMoviesByTitleOrGenres(String title,int ResultNumberPerPage,int pc,int order,String movieGenres) throws Exception {
		// TODO Auto-generated method stub
		return this.dao.getAllMoviesByTitleOrGenres(title,ResultNumberPerPage,pc,order,movieGenres);
	}

	@Override
	public List<Goods> getAllMoviesByAdvancedSearch(String title, Integer year, String name, String director)
			throws Exception {
		// TODO Auto-generated method stub
		return this.dao.getAllMoviesByAdvancedSearch(title, year,name, director);
	}

	@Override
	public boolean addMovie(String fid,String t,String year,String dirn) throws Exception {
		// TODO Auto-generated method stub
		if(fid !=null) {
			return this.dao.addMovie(fid,t,year,dirn);
		}
		return false;
	}

	@Override
	public boolean addStars_In_Movies(String a, String f) throws Exception {
		if(a !=null) {
			return this.dao.addStars_In_Movies(a,f);
		}
		return false;
	}

	@Override
	public boolean addStars_Xml(String stagename, String nm, String dob) throws Exception {
		if(stagename !=null) {
			return this.dao.addStars_Xml(stagename, nm, dob);
		}
		return false;
	}

	@Override
	public int getCounts() throws Exception {
		return this.dao.getCounts();
	}



	@Override
	public boolean deleteGoods(String gid) throws Exception {
		return this.dao.deleteGoods(gid);
	}



	@Override
	public List<Goods> queryBystarsId(String starsId) throws Exception {
		return this.dao.queryBystarsId(starsId);
	}


}
