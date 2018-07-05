package com.czy.dao;

import java.util.List;

import com.czy.bean.AlreadyBuy;

public interface AlreadyBuyDao {

	
	public boolean addBuyGoods(int uid, String gid, int number) throws Exception;

	
	public List<AlreadyBuy> getAllBuyGoods(int uid) throws Exception;

}
