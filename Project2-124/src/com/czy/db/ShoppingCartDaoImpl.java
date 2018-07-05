package com.czy.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.czy.bean.ShoppingCart;
import com.czy.dao.ShoppingCartDao;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public ShoppingCartDaoImpl(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public boolean addGoods(int uid, String gid, int number) throws Exception {
		pstmt = null;
		int result = 0;
		String message = this.getDesignateGoodsMs(uid, gid);
		if (!"".equals(message)) {
			String gId = message.split("&")[0];
			//int goodsCount = Integer.valueOf(message.split("&")[1]);
			String sql = "update shoppingcart set number=?,sdate=now() where gid=?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, gId);
		} else {
			String sql = "insert into shoppingcart(uid,gid,number,sdate)value(?,?,?,now());";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setString(2, gid);
			pstmt.setInt(3, number);
		}
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}
	

	@Override
	public List<ShoppingCart> getAllGoods(int uid) throws Exception {
		pstmt = null;
		ResultSet rs = null;
		List<ShoppingCart> scList = null;
		String sql = "select * from shoppingcart where uid=?";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		rs = pstmt.executeQuery();
		ShoppingCart sc;
		scList = new ArrayList<ShoppingCart>();
		while (rs.next()) {
			sc = new ShoppingCart();
			sc.setUid(rs.getInt("uid"));
			sc.setGid(rs.getString("gid"));
			sc.setNumber(rs.getInt("number"));
			String date = rs.getDate("sdate").toString();
			String time = rs.getTime("sdate").toString();
			sc.setSdate(date + " " + time);
			scList.add(sc);
		}
		return scList;
	}
	


	public String getDesignateGoodsMs(int uid, String gid) throws Exception {
		ResultSet rs = null;
		String sql = "select * from shoppingcart where uid=? and gid=? ";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		pstmt.setString(2, gid);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getString("gid") + "&" + rs.getInt("number");
		}
		return "";
	}

	

	@Override
	public boolean deleteGoods(int uid, String gid, int number) throws Exception {
		String message = this.getDesignateGoodsMs(uid, gid);
		int result = 0;
		if (!"".equals(message)) {
			String gId = message.split("&")[0];
			int goodsCount = Integer.valueOf(message.split("&")[1]);
			if (goodsCount < number) {
				return false;
			} 
			else if (goodsCount == number) {
				String sql = "delete from shoppingcart where gid=?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, gId);
			} 
			else {
				String sql = "update shoppingcart set number=? where gid=?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, goodsCount - number);
				pstmt.setString(2, gId);
			}
			result = pstmt.executeUpdate();
			pstmt.close();
		}
		if (result == 1) {
			return true;
		}
		return false;
	}


	@Override
	public boolean creditCards(String id, String firstname, String lastname, Date expiration) throws Exception {
			String addCreditCard = "insert into creditcards value(?,?,?,?) ";
			pstmt = this.conn.prepareStatement(addCreditCard);
			pstmt.setString(1, id);
			pstmt.setString(2, firstname);
			pstmt.setString(3, lastname);
			pstmt.setDate(4, expiration);
			int result =0;
			result = pstmt.executeUpdate();
			if(result==1) {
				return true;
			}
			else {
				return false;
			}
		
	}


	@Override
	public boolean clearAllGoods(int uid) throws Exception {
		String str = "delete from shoppingcart where Uid=? ";
		pstmt = this.conn.prepareStatement(str);
		pstmt.setInt(1, uid);
		int result =0;
		result = pstmt.executeUpdate();
		if(result==1) {
			return true;
		}
		else {
			return false;
		}
	}


	@Override
	public boolean sales(int uid, List<String> movieId) throws Exception {
		pstmt = null;
		int result = 0;
		int i=0;
		while(i<movieId.size()) {
			String mId = movieId.get(i);
			i++;
			String sql = "insert into sales(customerId,movieId,saleDate) value(?,?,now())";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
			pstmt.close();
			if (result == 1) {
				return true;
			}
			return false;
			}
		return true;
	}
	


}
