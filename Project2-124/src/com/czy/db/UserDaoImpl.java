package com.czy.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.czy.bean.User;
import com.czy.dao.UserDao;

public class UserDaoImpl implements UserDao {

	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean addUser(User user) throws Exception {
		pstmt = null;
		String sql = "insert into users(uname,passwd,email,lastLogin)value(?,?,?,sysdate());";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, user.getUname());
		pstmt.setString(2, user.getPasswd());
		pstmt.setString(3, user.getEmail());
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean editEmail(int uid, String email) throws Exception {
		User user = queryByEmail(email);
		if (user != null && user.getUid() != uid) {
			return false;
		}
		pstmt = null;
		String sql = "update users set email=? where uid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setInt(2, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean editLoginTime(int uid) throws Exception {
		pstmt = null;
		String sql = "update users set lastLogin=sysdate() where uid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean editPasswd(int uid, String passwd) throws Exception {
		String sql = "update users set passwd=? where uid=" + uid;
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, passwd);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(int uid) throws Exception {
		String sql = "delete from Users where uid=?";
		int result = 0;
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, uid);
		result = pstmt.executeUpdate();
		pstmt.close();
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public User queryByName(String email,String identity) throws Exception {
		User user = null;
		ResultSet rs = null;
		if(identity.equals("customers")) {
			String sql = "select * from customers where email =?";
			pstmt = this.conn.prepareStatement(sql);
		    //pstmt.setString(1, identity);
		    pstmt.setString(1, email);
		    rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				int uid = rs.getInt("id");
				String uname = rs.getString("firstName");
				String passwd = rs.getString("password");
				String mail = rs.getString("email");
				user.setUname(uname);
				user.setUid(uid);
				user.setPasswd(passwd);
				user.setEmail(mail);
				String date = rs.getDate("lastlogin").toString();
				String time = rs.getTime("lastlogin").toString();
				user.setLastLogin(date + "||" + time);
			}
		}
		else {
			String sql = "select * from employees  where email =?";
			pstmt = this.conn.prepareStatement(sql);
			//pstmt.setString(1, "employees");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User();
				String passwd = rs.getString("password");
				String mail = rs.getString("email");
				user.setUname("NULL");
				user.setUid(0);
				user.setPasswd(passwd);
				user.setEmail(mail);
				user.setLastLogin("0000-00-00" + "||" + "00:00:00");
			}
		}
		pstmt.close();
		rs.close();
		return user;
	}

	@Override
	public User queryByEmail(String email) throws Exception {
		User user = null;
		ResultSet rs = null;
		String sql = "select * from users where email=?";
		pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, email);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			user = new User();
			int uid = rs.getInt("uid");
			String uname = rs.getString("uname");
			String passwd = rs.getString("passwd");
			user.setUid(uid);
			user.setUname(uname);
			user.setPasswd(passwd);
			user.setEmail(email);
			String date = rs.getDate("lastlogin").toString();
			String time = rs.getTime("lastlogin").toString();
			user.setLastLogin(date + "��" + time);
		}
		pstmt.close();
		rs.close();
		return user;
	}

}
