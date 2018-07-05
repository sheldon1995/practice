package com.czy.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.czy.bean.ShoppingCart;
import com.czy.dao.ShoppingCartDao;
import com.czy.db.DBConnection;
import com.czy.db.ShoppingCartDaoImpl;

public class ShoppingCartService implements ShoppingCartDao {

	private DBConnection dbconn = null;

	private ShoppingCartDao dao = null;

	public ShoppingCartService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao = new ShoppingCartDaoImpl(this.dbconn.getConnection());
	}

	@Override
	public boolean addGoods(int uid, String gid, int number) throws Exception {
		if ( isInt(uid) && gid!=null && isInt(number)) {
			return this.dao.addGoods(uid, gid, number);
		}
		return false;
	}

	@Override
	public boolean deleteGoods(int uid,String gid, int number) throws Exception {
		if (isInt(uid) && gid!=null  && isInt(number)) {
			return this.dao.deleteGoods(uid, gid, number);
		}
		return false;
	}

	@Override
	public List<ShoppingCart> getAllGoods(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.getAllGoods(uid);
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
	public String getDesignateGoodsMs(int uid, String mId) throws Exception {
		if ( isInt(uid) && mId !=null) {
			return this.dao.getDesignateGoodsMs(uid, mId);
		}
		return "";
	}

	@Override
	public boolean creditCards(String id, String firstname, String lastname, Date expiration) throws Exception {
		if (id!=null) {
			return this.dao.creditCards(id, firstname, lastname, expiration);
		}
		return false;
	}

	@Override
	public boolean clearAllGoods(int uid) throws Exception {
		if (isInt(uid)) {
			return this.dao.clearAllGoods(uid);
		}
		return false;
	}

	@Override
	public boolean sales(int uid, List<String>  movieId) throws Exception {
		if ( isInt(uid) && movieId!=null) {
			return this.dao.sales(uid, movieId);
		}
		return false;
	}


}
