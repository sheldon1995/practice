package com.czy.dao;

import java.sql.Date;
import java.util.List;

import com.czy.bean.ShoppingCart;

public interface ShoppingCartDao {

	public boolean addGoods(int uid,String gid, int number) throws Exception;

	public boolean deleteGoods(int uid,String gid, int number) throws Exception;
	
	public boolean clearAllGoods(int uid) throws Exception;
	
	public List<ShoppingCart> getAllGoods(int uid) throws Exception;

	public String getDesignateGoodsMs(int uid, String mId) throws Exception;
	
	public boolean creditCards(String id,String firstname,String lastname,Date expiration) throws Exception;
	
	public boolean sales(int uid, List<String> movieId) throws Exception;

}
